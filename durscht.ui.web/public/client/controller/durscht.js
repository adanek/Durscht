/// <reference path="../_references.ts"/>
(function () {
    var app = angular.module("durscht-core", ["ngRoute", "ui.bootstrap"]);
    var config = function ($routeProvider) {
        $routeProvider.when("/", { templateUrl: "/assets/client/views/index.html" }).when("/share/bar/choose", { templateUrl: "/assets/client/views/share-choose-bar.html" }).when("/share/beer/choose", { templateUrl: "/assets/client/views/share-choose-beer.html" }).when("/share/details", { templateUrl: "/assets/client/views/share-details.html" }).when("/share/bar/create", { templateUrl: "/assets/client/views/bar-create.html" }).when("/share/beer/create", { templateUrl: "/assets/client/views/share-create-beer.html" }).otherwise({ redirectTo: "/" });
    };
    app.config(config);
})();
