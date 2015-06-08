/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService, $routeParams) {
        var barId = $routeParams.barId;
        var bar;
        var beers;
        barService.getBarDetails(barId).success(function (data) {
            bar = data;
            $scope.caption = bar.name;
            $scope.distance = bar.distance;
            $scope.map = { center: { latitude: bar.latitude, longitude: bar.longitude }, zoom: 12 };
            $scope.marker = {
                id: 0,
                coords: {
                    latitude: bar.latitude,
                    longitude: bar.longitude
                }
            };
        });
        barService.getBeersFromBar(barId).success(function (data) {
            beers = data;
            $scope.beers = beers;
        });
    };
    app.controller('FindBarDetailsCtrl', ['$scope', '$location', 'barService', '$routeParams', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarDetails.js.map