/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, $routeParams) {

        var caption:string ;

        var barId : number = $routeParams.barId;
        var bar : Bar;
        var beers : Array<Beer>;

        barService.getBarDetails(barId).success(function (data) {
            bar = data;
            $scope.caption = bar.name;
        });

        barService.getBeersFromBar(barId).success(function (data) {
            beers = data;
        })
    }

    app.controller('FindBarDetailsCtrl', ['$scope', '$location', 'barService', '$routeParams', ctrl]);

})(angular.module('durschtApp'));