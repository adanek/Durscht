/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService) {

        var caption:string = "In diesen Bars wirst du fündig:";
        var bars:Array<Bar> = [];

        barService.getBarsWithFavoriteBeers().success(function (data) {
            bars = data;
        });

        $scope.caption = caption;
        $scope.bars = bars;
    }

    app.controller('FindBarListCtrl', ['$scope', '$location', 'barService', ctrl]);

})(angular.module('durschtApp'));