/// <reference path="../_references.ts"/>
'use strict';
/**
 * @ngdoc service
 * @name durschtApp.authentication
 * @description
 * # authentication
 * Provides the service to authentify the user.
 */
(function (app) {
    app.service('authentication', function ($http, authenticationUrl) {
        this.login = function (username, password) {
            return $http.post(authenticationUrl + '/login', {
                user: username,
                pwd: password
            });
        };
    });
})(angular.module('durschtApp'));
//# sourceMappingURL=authentication.js.map