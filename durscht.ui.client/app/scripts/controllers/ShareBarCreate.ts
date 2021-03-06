/// <reference path="../_references.ts"/>

(function (app) {

    var ctrl = function ($scope, barService:BarService, posting, $location) {

        $scope.caption = "Du bist der erste hier!";

        // Properties
        var name:String = "";
        var website:String = "";
        var remark:String = "";

        $scope.name = name;
        $scope.website = website;
        $scope.remark = remark;

        $scope.create = function () {

            $scope.$broadcast('show-errors-check-validity');

            if ($scope.createBarForm.$valid) {
                barService.createBar($scope.name, $scope.website, $scope.remark)
                    .success(function (data) {
                        posting.bar = data;
                        $location.path("/share/beer/create").replace();
                        $scope.$apply();
                    }).error(function (data, status, headers, config) {
                        $scope.error = "Das hat nich geklappt!";
                        $scope.$apply();
                    });
            }
        }
    };

    app.controller('ShareBarCreateCtrl', ['$scope', 'barService', 'posting', '$location', ctrl]);

})(angular.module('durschtApp'));