/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function ($scope, authentication, $location) {
        $scope.caption = "Dich kenn ich doch!";
        $scope.username = "Hans";
        $scope.password = "Geheim";
        $scope.login = function () {
            authentication.login($scope.username, $scope.password).success(function () {
                $location.path('/share/position').replace();
            }).error(function (data, status, headers, config) {
                var msg;
                switch (status) {
                    case 401:
                        msg = "Das passt so nicht, denk noch einmal nach";
                        break;
                    default:
                        msg = "Ups, da ging was schief!";
                }
                $scope.$apply(function () {
                    $scope.errorMessage = msg;
                });
            });
        };
    };
    app.controller("LoginCtrl", ['$scope', 'authentication', '$location', controller]);
})(angular.module('durschtApp'));
//# sourceMappingURL=Login.js.map