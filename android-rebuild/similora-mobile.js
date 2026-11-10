(function(){
  const menu=document.getElementById('menuSettings');
  if(menu&&window.openrepertoryNative){const button=document.createElement('button');button.className='menu-item';button.type='button';button.textContent='Mobile security';button.onclick=()=>{OR.dismissMenu();openrepertoryNative.openPrivacy()};menu.append(button)}
})();
