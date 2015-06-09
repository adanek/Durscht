/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, posting, authService, $location) {
        posting.reset();
        $scope.caption = "Kann ich mal deinen Ausweis sehen?";
        authService.getId().success(function (data) {
            posting.userId = parseInt(data);
            authService.setAuthenticated(true);
            $location.path('/share/location').replace();
        }).error(function (data, status) {
            if (status == 401) {
                authService.setReferrer('/share/user');
                $location.path('/login').replace();
            }
        });
    };
    app.controller('ShareUserCtrl', ['$scope', 'posting', 'authService', '$location', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=ShareUser.js.map