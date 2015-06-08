/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, barService:BarService, searchService:SearchService, posting:Posting) {


        var bar:Bar = searchService.choosenBar;
        var posts:Array<Posting> = [];

        $scope.caption = 'Meinungen über ' + bar.name;
        $scope.posts = posts;

        // Load posts form server
        barService.getPosts(searchService.choosenBar.id)
            .success(function (data) {
                posts = data;
                posts.sort(posting.compareByDateDsc);

                $scope.posts = posts;
            });


    }

    app.controller('FindBarPostsCtrl', ['$scope', '$location', 'barService', 'searchService', 'posting', ctrl]);

})(angular.module('durschtApp'));