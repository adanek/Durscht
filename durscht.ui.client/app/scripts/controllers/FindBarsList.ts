/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, locationService:LocationService, searchService:SearchService) {

        var caption:string = 'Ich mach mich auf die Suche...';
        var bars:Array<Bar> = [];
        var markers:Array<Marker> = [];

        $scope.caption = caption;
        $scope.bars = bars;
        $scope.markers = markers;

        $scope.map = {center: {latitude: locationService.latitude, longitude: locationService.longitude}, zoom: 8};

        if (searchService.bars.length == 0) {
            // Load bars from service
            barService.getBarsWithFavoriteBeers().success(function (data) {

                bars = data;
                searchService.bars = bars;
                showBars();
            });
        } else {

            bars = searchService.bars;
            showBars();
        }

        function showBars() {

            bars.sort(barService.compareByDistance);

            // Add markers to the map
            bars.forEach(function (bar:Bar) {
                markers.push({id: bar.id, coords: {longitude: bar.longitude, latitude: bar.latitude}});
            });

            $scope.markers = markers;
            $scope.caption = 'In diesen Bars wirst du fündig:';
            $scope.bars = bars;
        }

        $scope.showDetails = function (bar:Bar) {
            searchService.choosenBar = bar;
            $location.path('/find/bar/details');
        }
    }

    app.controller('FindBarListCtrl', ['$scope', '$location', 'barService', 'locationService', 'searchService', ctrl]);

})(angular.module('durschtApp'));