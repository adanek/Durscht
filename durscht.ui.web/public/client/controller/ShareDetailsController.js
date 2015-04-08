/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function (posting, $scope) {
        this.title = "Aha!";
        this.posting = posting;
        $scope.rate = 7;
        $scope.max = 10;
        $scope.isReadonly = false;
        $scope.hoveringOver = function (value) {
            $scope.overStar = value;
            $scope.percent = 100 * (value / $scope.max);
        };
    };
    app.controller("ShareDetailsController", controller);
})(angular.module('durscht-core'));
