/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function($scope, barService : BarService, posting, $location) {

        $scope.caption = "Welches Bier konnte denn deinen Durscht stillen?";
        $scope.beer = undefined;
        $scope.beers = [];

        barService.getAllBeers().success(function(data){
            $scope.beers = data.sort(function(a:Beer,b:Beer){
                return a.brand.localeCompare(b.brand);
            });
            $scope.$apply();
        })

        $scope.create = function(){

            posting.beer = $scope.beer;
            $location.path('/share/details').replace();
        }

    };

    app.controller('ShareBeerCreateCtrl',['$scope', 'barService', 'posting', '$location', ctrl]);

})(angular.module('durschtApp'));