/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, searchService:SearchService) {

        var caption:string ;
        var bar:Bar;


        var barId = searchService.barId;
        barService.getBarDetails(barId).success(function (data) {
            bar = data;
            $scope.caption = bar.name;
        });





    }

    app.controller('FindBarDetailsCtrl', ['$scope', '$location', 'barService', 'searchService', ctrl]);

})(angular.module('durschtApp'));