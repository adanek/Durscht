/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, beerService:BeerService, searchService:SearchService, posting:Posting) {

        var bar : Bar = searchService.chosenBar;
        var beers : Array<Beer> = [];
        var posts:Array<Posting> = [];

        $scope.caption = bar.name;
        $scope.distance = bar.distance;
        $scope.weblink = "www.tobeimplemented.age"
        $scope.beers = beers;
        $scope.posts = posts;

        // Load available beers from server
        barService.getBeersFromBar(searchService.chosenBar.id).success(function (data) {
            beers = data;
            beers.sort(beerService.compareByName);
            $scope.beers = beers;
        });

        // Load posts form server
        barService.getPosts(bar.id)
            .success(function (data) {
                posts = data;
                posts.sort(posting.compareByDateDsc);
                $scope.posts = posts;
            });
    }

    app.controller('FindBarDetailsCtrl', ['$scope', '$location', 'barService', 'beerService', 'searchService', 'posting', ctrl]);

})(angular.module('durschtApp'));