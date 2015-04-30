/// <reference path="../_references.ts"/>

(function(app){

    var service = function($http, serviceHost ){

        var login = function(username:String, passwd:String){
            return $http.post(serviceHost + '/login', {
                name: username,
                pw: passwd
            });
        }

        return {
            login: login
        }
    };
    
    app.factory('authentication', ['$http', 'serviceHost', service]);

})(angular.module('durschtApp'));
