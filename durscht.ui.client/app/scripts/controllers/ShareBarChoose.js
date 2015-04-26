/// <reference path="../_references.ts"/>
(function (app) {
    var ctrl = function ($scope, barService, posting, $location) {
        $scope.caption = "Wo gesch denn um?";
        //$scope.bars = []; //barService.getBars();
        barService.getNearBars().success(function (data) {
            $scope.bars = data;
            setCreateBarText();
            $scope.$apply();
        });
        var setCreateBarText = function () {
            if ($scope.bars) {
                var createBarText = "";
                if ($scope.bars.length <= 0) {
                    createBarText = "Du bist der erste hier!";
                }
                else {
                    createBarText = "Nö, ganz wo anders";
                }
                $scope.createBarText = createBarText;
            }
        };
        $scope.setBar = function (bar) {
            posting.bar = bar;
            $location.path("/share/beer/choose").replace();
        };
        $scope.createBar = function () {
            $location.path("/share/bar/create").replace();
        };
    };
    app.controller('ShareBarChooseCtrl', ctrl);
})(angular.module('durschtApp'));
//# sourceMappingURL=ShareBarChoose.js.map