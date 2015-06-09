/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, searchService:SearchService) {

        var bar : Bar;
        var beers : Array<Beer>;

        $scope.caption = searchService.choosenBar.name;
        $scope.distance = searchService.choosenBar.distance;
        $scope.weblink = "www.tobeimplemented.age"

        barService.getBeersFromBar(searchService.choosenBar.id).success(function (data) {
            beers = data;
            $scope.beers = beers;
        });

        $scope.showPosts = function(){
            $location.path('/find/bar/posts');
        }
    }

    app.controller('FindBarDetailsCtrl', ['$scope', '$location', 'barService', 'searchService', ctrl]);

})(angular.module('durschtApp'));