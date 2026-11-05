"""Recover packaged app assets locally from a verified prepared release.

This is not a reconstruction of the native Android source or signing keys.
"""
from pathlib import Path, PurePosixPath
import argparse
import hashlib
import json
import shutil
import subprocess
import tempfile
import zipfile

ROOT = Path(__file__).resolve().parents[1]


def extract_assets(apk, destination):
    destination = Path(destination).resolve()
    if destination.exists():
        raise ValueError('Destination must not exist; existing files will not be overwritten')
    destination.parent.mkdir(parents=True, exist_ok=True)
    with tempfile.TemporaryDirectory(dir=destination.parent) as temporary:
        staging = Path(temporary) / 'recovered'
        staging.mkdir()
        files = {}
        with zipfile.ZipFile(apk) as archive:
            for entry in archive.infolist():
                if not entry.filename.startswith('assets/') or entry.is_dir():
                    continue
                path = PurePosixPath(entry.filename)
                if '..' in path.parts or '\\' in entry.filename or path.is_absolute():
                    raise ValueError('Unsafe asset path')
                if entry.filename in files:
                    raise ValueError('Duplicate asset path')
                content = archive.read(entry)
                output = staging.joinpath(*path.parts)
                output.parent.mkdir(parents=True, exist_ok=True)
                output.write_bytes(content)
                files[entry.filename] = hashlib.sha256(content).hexdigest()
        if not files:
            raise ValueError('APK contains no assets')
        report = {'apk_sha256': hashlib.sha256(Path(apk).read_bytes()).hexdigest(),
                  'files': files, 'complete_android_source': False,
                  'signing_keys_recovered': False}
        (staging / 'RECOVERY.json').write_text(json.dumps(report, indent=2) + '\n')
        shutil.move(str(staging), str(destination))
    return len(files)


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('destination', type=Path)
    args = parser.parse_args()
    destination = args.destination.resolve()
    if destination == ROOT or ROOT in destination.parents:
        parser.error('Choose a destination outside this public repository')
    config = json.loads((ROOT / 'release-config.json').read_text())
    version = config['versionName'].removesuffix('-dev')
    tag = 'v' + version
    subprocess.run(['python3', str(ROOT / 'scripts/verify_prepared.py'), tag], check=True)
    apk = ROOT / 'prepared' / tag / ('Open-Repertory-' + version + '.apk')
    count = extract_assets(apk, destination)
    print(f'Recovered {count} packaged assets to {destination}. Native build source and keys are not included.')


if __name__ == '__main__':
    main()
