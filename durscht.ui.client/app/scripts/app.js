/// <reference path="_references.ts"/>
(function () {
    var app = angular.module("durschtApp", ["ngRoute", "ui.bootstrap"]);
    app.config(function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'views/main.html',
            controller: 'MainCtrl'
        }).when('/about', {
            templateUrl: 'views/about.html',
            controller: 'AboutCtrl'
        }).when('/share/location', {
            templateUrl: 'views/share-location.html',
            controller: 'ShareLocationCtrl'
        }).when('/share/bar/choose', {
            templateUrl: '/views/share-bar-choose.html',
            controller: 'ShareBarChooseCtrl'
        }).when('/share/beer/choose', {
            templateUrl: 'views/share-beer-choose.html',
            controller: 'ShareBeerChooseCtrl'
        }).otherwise({
            redirectTo: '/'
        });
    });
    app.constant("barApiUrl", "http://localhost:9000/bars/");
})();
//# sourceMappingURL=app.js.map