/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService, locationService) {
        var caption = 'In diesen Bars wirst du fündig:';
        var bars = [];
        var markers = [];
        $scope.caption = caption;
        $scope.bars = bars;
        $scope.markers = markers;
        $scope.map = { center: { latitude: locationService.latitude, longitude: locationService.longitude }, zoom: 8 };
        // Load bars from service
        barService.getBarsWithFavoriteBeers().success(function (data) {
            var bars = data;
            bars.sort(barService.compareByDistance);
            // Add markers to the map
            bars.forEach(function (bar) {
                markers.push({ id: bar.id, coords: { longitude: bar.longitude, latitude: bar.latitude } });
            });
            $scope.markers = markers;
            $scope.bars = bars;
        });
        $scope.showDetails = function (barId) {
            $location.path('/find/bar/details/' + barId);
            $scope.$apply();
        };
    };
    app.controller('FindBarListCtrl', ['$scope', '$location', 'barService', 'locationService', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarsList.js.map