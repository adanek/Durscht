/// <reference path="../_references.ts"/>

(function(app){

    var service = function($http, serviceHost ){

        var login = function(username:String, passwd:String){
            return $http.post(serviceHost + '/login', {
                name: username,
                pw: passwd
            });
        }

        var getId = function(){
            return $http.get(serviceHost + '/user/id');
        };

        return {
            login: login,
            getId: getId
        }
    };
    
    app.factory('authentication', ['$http', 'serviceHost', service]);

})(angular.module('durschtApp'));
