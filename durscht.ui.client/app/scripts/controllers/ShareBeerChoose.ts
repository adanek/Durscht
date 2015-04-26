/// <reference path="../_references.ts"/>

(function(app){

    var controller = function($scope, $location, posting:Posting ){

        $scope.name = posting.bar.name || "TestBar";
        $scope.createBeerText = "Nö, ganz a anders";
        $scope.beers = posting.bar.beers || [];

        if(posting.bar.beers.length <= 0){
            $scope.createBeerText = "Öha! Du bist der erste hier!";
        }

        $scope.setBeer = function(beer:Beer){
            posting.beer = beer;
            $location.path("/share/details");
        }

        $scope.createBeer = function (){
            $location.path("/share/beer/create").replace();
        }
    };

    app.controller("ShareBeerChooseCtrl", ['$scope', '$location', 'posting', controller]);

})(angular.module("durschtApp"));