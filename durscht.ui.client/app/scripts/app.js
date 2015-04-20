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
        }).when('/settings', {
            templateUrl: 'views/settings.html',
            controller: 'SettingsCtrl'
        }).when('/ShareChooseBarCtrl', {
            templateUrl: '/views/share-choose-bar.html',
            controller: ''
        }).when('/share/position', {
            templateUrl: 'views/share.html',
            controller: 'ShareCtrl'
        }).when('/share/chooseBar', {
            templateUrl: 'views/share-choose-bar.html',
            controller: 'ShareChooseBarCtrl'
        }).otherwise({
            redirectTo: '/'
        });
    });
    app.constant("barApiUrl", "/bars/");
})();
//# sourceMappingURL=app.js.map