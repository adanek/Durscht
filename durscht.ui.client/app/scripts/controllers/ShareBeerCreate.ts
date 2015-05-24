/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, barService:BarService, beerService:BeerService, posting, $location) {

        $scope.caption = "Welches Bier konnte denn deinen Durscht stillen?";

        // Fields for add beer
        $scope.beer = undefined;
        $scope.beers = [];

        // Fields for create beer
        $scope.showBeerForm = false;
        $scope.brand = "";
        $scope.type = "";
        $scope.description = "";

        barService.getAllBeers().success(function (data) {
            $scope.beers = data.sort(function (a:Beer, b:Beer) {
                return a.brand.localeCompare(b.brand);
            });
            $scope.$apply();
        });


        $scope.add = function () {

            $scope.$broadcast('show-errors-check-validity');

            if ($scope.addBeerForm.$valid) {
                posting.beer = $scope.beer;
                $location.path('/share/details').replace();
            }
            ;
        }


        $scope.create = function () {

            $scope.$broadcast('show-errors-check-validity');

            if ($scope.createBeerForm.$valid) {

                beerService.createBeer($scope.brand, $scope.type, $scope.description)
                    .success(function (data) {
                        posting.beer = data;
                        $location.path('/share/details').replace();
                        $scope.$apply();
                    })
                .error(function(){

                        $scope.error = "Das hat nicht geklappt!"
                    });

            }
            ;


        };

        $scope.newBeer = function () {

            $scope.caption = "Na dann lass es uns anlegen!"
            $scope.$broadcast('show-errors-reset');
            $scope.showBeerForm = true;
        };

    };

    app.controller('ShareBeerCreateCtrl', ['$scope', 'barService', 'beerService', 'posting', '$location', ctrl]);

})(angular.module('durschtApp'));