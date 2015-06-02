/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var controller = function ($scope, authService, $location) {
        $scope.caption = 'Dich kenn ich doch!';
        $scope.username = '';
        $scope.password = '';
        $scope.login = function () {
            $scope.$broadcast('show-errors-check-validity');
            if ($scope.loginForm.$valid) {
                authService.login($scope.username, $scope.password).success(function () {
                    $location.path('/share/user').replace();
                }).error(function (data, status, headers, config) {
                    var msg;
                    switch (status) {
                        case 401:
                            msg = 'Das passt so nicht, denk noch einmal nach';
                            break;
                        default:
                            msg = 'Ups, da ging was schief!';
                    }
                    $scope.errorMessage = msg;
                });
            }
        };
        $scope.register = function () {
            $location.path('/register').replace();
        };
    };
    app.controller('LoginCtrl', ['$scope', 'authService', '$location', controller]);
})(angular.module('durschtApp'));
//# sourceMappingURL=Login.js.map