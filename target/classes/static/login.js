
    function admin(){
        window.location.href='adminLogin.html';
    }

    function user(){
         window.location.href='userLogin.html';
    }

    
    function registrarUsuario(){
        window.location.href='userRegister.html';
    }
    
    
    function validarUsuario(){
        var usuario = $("#userName").val();
        var psw = $("#userPsw").val();
        $.get("/api/persona/"+usuario+"/"+psw, function(data){
            if(!data){
               console.log(usuario);
               sessionStorage.usuario = usuario;
               window.location.href='userItems.html'; 
            }
        });
    }

     function  validarAdmin(){
        usuario = $("#username").val();
        psw = $("#passwd").val();
        $.get("/api/admin/"+usuario+"/"+psw, function(data){
            if(!data){
               window.location.href='adminItems.html'; 
            }
        });
    }

