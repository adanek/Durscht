/// <reference path="../_references.ts"/>
(function (app) {
    var srv = function ($http) {
        this.login = function (username, passwd) {
            $http.post();
        };
    };
    app.factory('authentication', srv);
})(angular.module('durschtApp'));
//# sourceMappingURL=authentication.js.map