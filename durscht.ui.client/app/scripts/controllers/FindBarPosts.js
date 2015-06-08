/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService, $routeParams) {
        var barId = $routeParams.barId;
        var bar;
        var posts;
        barService.getBarDetails(barId).success(function (data) {
            bar = data;
            $scope.caption = bar.name;
        });
        barService.getPosts(barId).success(function (data) {
            posts = data;
            $scope.posts = posts;
        });
    };
    app.controller('FindBarPostsCtrl', ['$scope', '$location', 'barService', '$routeParams', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarPosts.js.map