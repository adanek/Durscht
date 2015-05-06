/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function ($scope, $location, authService) {
        $scope.username = "";
        $scope.email = "";
        $scope.password = "";
        $scope.register = function () {
            $scope.$broadcast('show-errors-check-validity');
            if ($scope.registerForm.$valid) {
                authService.register($scope.username, $scope.email, $scope.password).success(function () {
                    authService.setAuthenticated(true);
                    $location.path('/').replace();
                });
            }
            ;
        };
    };
    app.controller("RegisterCtrl", ['$scope', '$location', 'authService', controller]);
})(angular.module('durschtApp'));
//# sourceMappingURL=Register.js.map