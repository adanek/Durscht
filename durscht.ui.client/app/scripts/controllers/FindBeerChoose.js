/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, beerService) {
        $scope.caption = 'Welches Bier hättesch denn gern?';
        $scope.beer = {};
        var beers = [];
        beerService.getUsed().success(function (data) {
            beers = data;
            beers.sort(beerService.compareByName);
            $scope.beers = beers;
        });
        $scope.beers = beers;
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
        $scope.removeBeer = function (beer) {
            // Remove beer from selected list
            var ndx = $scope.selectedBeers.indexOf(beer);
            $scope.selectedBeers.splice(ndx, 1);
            // Add beer to source list
            $scope.beers.push(beer);
            $scope.beers.sort(beerService.compareByName);
        };
        $scope.searchBars = function () {
            // Check if at least one beer is selected
            if ($scope.selectedBeers.length == 0) {
                $scope.errorMessage = "Du musst mindestens ein Bier auswählen!";
                return;
            }
            beerService.setFavorites($scope.selectedBeers);
            $location.path('/find/bars').replace();
        };
    };
    app.controller('FindBeerChooseCtrl', ['$scope', '$location', 'beerService', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBeerChoose.js.map