/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService, searchService) {
        var caption = "In diesen Bars wirst du fündig:";
        var bars = [];
        $scope.caption = caption;
        $scope.bars = bars;
        // Load bars from service
        barService.getBarsWithFavoriteBeers().success(function (data) {
            var bars = data;
            bars.sort(barService.compareByDistance);
            $scope.bars = bars;
        });
        $scope.showDetails = function (barId) {
            searchService.barId = barId;
            $location.path('/find/bar/details').replace();
            $scope.$apply();
        };
    };
    app.controller('FindBarListCtrl', ['$scope', '$location', 'barService', 'searchService', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarsList.js.map