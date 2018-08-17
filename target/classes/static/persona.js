
var app = angular.module('itemsFusepong', []);

app.controller('myCtrl', function($scope,$http,$q) {
    function Persona(){
        this.nombre = "";
        this.correo = "";
        this.usuario = "";
        this.pw = "";
    }
    
    function savePersonaService(persona){
        var deferred = $q.defer();
        var url = "/api/persona";
        $http.post(url,persona).then(function success(){
            deferred.resolve();
        },function fail(){
            deferred.reject();
        });
        return deferred.promise;
    }
    
    $scope.addUser = function(){
        nombre = $("#nombre").val();
        correo = $("#correo").val();
        usuario = $("#usuario").val();
        pw = $("#psw").val();
        persona = new Persona();
        persona.nombre = nombre;
        persona.correo = correo;
        persona.usuario = usuario;
        persona.pw = pw;
        savePersonaService(persona).then(function(){
           console.log("Usuario registrado"); 
        });
        window.location.href='userLogin.html';
    };
});

