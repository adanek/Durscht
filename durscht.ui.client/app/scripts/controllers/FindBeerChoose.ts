/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, beerService:BeerService) {

        $scope.caption = 'Welches Bier hättesch denn gern?';
        $scope.beer = {};

        var beers : Array<Beer> = [];
        beerService.getUsed().success(function(data){
            beers = data;
        });

        var beers:Array<Beer> = [
            {brand: 'Zipfer', type: 'Märzen', id: 0, description: ''},
            {brand: 'Corona', type: 'Extra', id: 1, description: ''}];

        function compareBeers(a:Beer, b:Beer) {
            if (a < b) {
                return -1
            }
            else if (b < a) {
                return 1;
            }
            else {
                if (a.type < b.type) {
                    return -1;
                } else if (b.type < a.type) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        $scope.beers = beers.sort(compareBeers);
        $scope.selectedBeers = [];
        $scope.addBeer = function () {
            if ($scope.beer.selected) {

                var beer = $scope.beer.selected;

                // Remove beer from source list
                var ndx = beers.indexOf(beer);
                $scope.beers.splice(ndx, 1);

                // Add beer to selected list
                $scope.selectedBeers.push(beer);
                $scope.beer.selected = undefined;
            }
        };

        $scope.removeBeer = function (beer:Beer) {

            // Remove beer from selected list
            var ndx = $scope.selectedBeers.indexOf(beer);
            $scope.selectedBeers.splice(ndx, 1);

            // Add beer to source list
            $scope.beers.push(beer);
            $scope.beers.sort(compareBeers);
        }

        $scope.searchBars = function () {

            beerService.setFavorites($scope.selectedBeers);
            $location.path('/find/bars').replace();
        };
    };

    app.controller('FindBeerChooseCtrl', ['$scope', '$location', 'beerService', ctrl]);

})(angular.module('durschtApp'));