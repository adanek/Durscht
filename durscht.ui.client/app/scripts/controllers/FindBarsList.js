/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService) {
        var caption = "In diesen Bars wirst du fündig:";
        var bars = [];
        barService.getBarsWithFavoriteBeers().success(function (data) {
            bars = data;
        });
        $scope.caption = caption;
        $scope.bars = bars;
    };
    app.controller('FindBarListCtrl', ['$scope', '$location', 'barService', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarsList.js.map