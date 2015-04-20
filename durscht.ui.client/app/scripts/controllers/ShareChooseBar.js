/// <reference path="../_references.ts"/>
(function (app) {
    var ctrl = function ($scope, barService, posting, $location) {
        $scope.caption = "Wo gesch denn um?";
        $scope.bars = barService.getBars();
        barService.getNearBars().success(function (data) {
            $scope.bars = data;
        });
        var createBarText = "";
        if ($scope.bars.length <= 0) {
            createBarText = "Du bist der erste hier!";
        }
        else {
            createBarText = "N�, ganz wo anders";
        }
        $scope.createBarText = createBarText;
        $scope.setBar = function (bar) {
            posting.bar = bar;
            $location.path("/share/beer/choose").replace();
        };
        $scope.createBar = function () {
            $location.path("/share/bar/create").replace();
        };
    };
    app.controller('ShareChooseBarCtrl', ctrl);
})(angular.module('durschtApp'));
//# sourceMappingURL=ShareChooseBar.js.map