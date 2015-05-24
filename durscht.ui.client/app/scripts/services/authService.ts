/// <reference path="../_references.ts"/>

(function (app) {

    var service = function ($rootScope, $http, serviceHost) {

        var srv:AuthenticationService = this;

        var authenticated = false;

        srv.isAuthenticated = function () {
            return authenticated;
        }

        srv.setAuthenticated = function (val) {

            if ($rootScope.$root.$$phase != '$apply' && $rootScope.$root.$$phase != '$digest') {
                $rootScope.$apply(function () {
                    authenticated = val;
                });
            }
            else {
                authenticated = val;
            }
        }

        srv.login = function (username:String, passwd:String) {
            return $http.post(serviceHost + '/user/login', {
                name: username,
                pw: passwd
            });
        };

        srv.logout = function () {
            return $http.post(serviceHost + '/user/logout');
        };

        srv.getId = function () {
            return $http.get(serviceHost + '/user/id');
        };

        srv.register = function(username:string, email:string, password:string){
            return $http.post(serviceHost + '/user/register', {
                user: username,
                email: email,
                passwd: password
            });
        };

        return srv;
    };

    app.factory('authService', ['$rootScope', '$http', 'serviceHost', service]);

})(angular.module('durschtApp'));
