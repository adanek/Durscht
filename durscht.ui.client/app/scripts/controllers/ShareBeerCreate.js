/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, barService, beerService, posting, $location) {
        $scope.caption = "Welches Bier konnte denn deinen Durscht stillen?";
        $scope.beer = undefined;
        $scope.beers = [];
        // Fields for new beer
        $scope.showBeerForm = false;
        $scope.brand = "";
        $scope.type = "";
        $scope.description = "";
        barService.getAllBeers().success(function (data) {
            $scope.beers = data.sort(function (a, b) {
                return a.brand.localeCompare(b.brand);
            });
            $scope.$apply();
        });
        $scope.create = function () {
            if ($scope.showBeerForm) {
                beerService.createBeer($scope.brand, $scope.type, $scope.description).success(function (data) {
                    posting.beer = data;
                    $location.path('/share/details').replace();
                    $scope.$apply();
                });
            }
            else {
                posting.beer = $scope.beer;
                $location.path('/share/details').replace();
            }

        };
        $scope.newBeer = function () {
            $scope.showBeerForm = true;
        };
    };
    app.controller('ShareBeerCreateCtrl', ['$scope', 'barService', 'beerService', 'posting', '$location', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=ShareBeerCreate.js.map