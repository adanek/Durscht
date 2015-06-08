/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, $routeParams) {

        var barId:number = $routeParams.barId;
        var bar:Bar;
        var posts:Array<Posting>;

        barService.getBarDetails(barId).success(function (data) {
            bar = data;
            $scope.caption = bar.name;

        });

        barService.getPosts(barId).success(function (data) {
            posts = data;
            $scope.posts = posts;
        });
    }

    app.controller('FindBarPostsCtrl', ['$scope', '$location', 'barService', '$routeParams', ctrl]);

})(angular.module('durschtApp'));