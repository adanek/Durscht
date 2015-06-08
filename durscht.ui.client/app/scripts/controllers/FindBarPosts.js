/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService, searchService, posting) {
        var bar = searchService.choosenBar;
        var posts = [];
        $scope.caption = 'Meinungen über ' + bar.name;
        $scope.posts = posts;
        // Load posts form server
        barService.getPosts(searchService.choosenBar.id).success(function (data) {
            posts = data;
            posts.sort(posting.compareByDateDsc);
            $scope.posts = posts;
        });
    };
    app.controller('FindBarPostsCtrl', ['$scope', '$location', 'barService', 'searchService', 'posting', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarPosts.js.map