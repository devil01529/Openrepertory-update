package com.openrepertory.app;

import android.content.*;
import android.database.*;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import java.io.*;

/** Shares only the already verified update APK, read-only, with Android's installer. */
public final class UpdateFileProvider extends ContentProvider {
    public boolean onCreate(){return true;}
    private File file(Uri uri)throws FileNotFoundException {
        if(!"content".equals(uri.getScheme())||!(getContext().getPackageName()+".updates").equals(uri.getAuthority())
            ||!"/verified.apk".equals(uri.getEncodedPath())||uri.getQuery()!=null||uri.getFragment()!=null)throw new FileNotFoundException("Unknown update");
        return new File(getContext().getFilesDir(),"updates/update.apk");
    }
    @Override public ParcelFileDescriptor openFile(Uri uri,String mode)throws FileNotFoundException {
        if(!"r".equals(mode))throw new FileNotFoundException("Read only");
        return ParcelFileDescriptor.open(file(uri),ParcelFileDescriptor.MODE_READ_ONLY);
    }
    public String getType(Uri uri){try{file(uri);return "application/vnd.android.package-archive";}catch(Exception e){throw new IllegalArgumentException("Unknown update");}}
    public Cursor query(Uri uri,String[] projection,String selection,String[] args,String order){
        try{
            File f=file(uri);String[] columns=projection==null?new String[]{OpenableColumns.DISPLAY_NAME,OpenableColumns.SIZE}:projection;
            MatrixCursor cursor=new MatrixCursor(columns);Object[] row=new Object[columns.length];
            for(int i=0;i<columns.length;i++)row[i]=OpenableColumns.DISPLAY_NAME.equals(columns[i])?"Open-Repertory-update.apk":OpenableColumns.SIZE.equals(columns[i])?f.length():null;
            cursor.addRow(row);return cursor;
        }catch(Exception e){throw new IllegalArgumentException("Unknown update");}
    }
    public Uri insert(Uri u,ContentValues v){throw new UnsupportedOperationException();}
    public int update(Uri u,ContentValues v,String s,String[] a){throw new UnsupportedOperationException();}
    public int delete(Uri u,String s,String[] a){throw new UnsupportedOperationException();}
}
