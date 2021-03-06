/// <reference path="../_references.ts"/>
(function (app) {
    var service = function ($rootScope, $http, serviceHost) {
        var srv = this;
        var authenticated = false;
        var referrer = undefined;
        srv.isAuthenticated = function () {
            return authenticated;
        };
        srv.setAuthenticated = function (val) {
            if ($rootScope.$root.$$phase != '$apply' && $rootScope.$root.$$phase != '$digest') {
                $rootScope.$apply(function () {
                    authenticated = val;
                });
            }
            else {
                authenticated = val;
            }
        };
        srv.login = function (username, passwd) {
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
        srv.register = function (username, email, password) {
            return $http.post(serviceHost + '/user/register', {
                user: username,
                email: email,
                passwd: password
            });
        };
        srv.setReferrer = function (val) {
            referrer = val;
        };
        srv.getReferrer = function () {
            return referrer;
        };
        return srv;
    };
    app.factory('authService', ['$rootScope', '$http', 'serviceHost', service]);
})(angular.module('durschtApp'));
//# sourceMappingURL=authService.js.map