/// <reference path="_references.ts"/>

(function (){
    var app = angular.module("durschtApp", ["ngRoute", "ui.bootstrap", 'ui.bootstrap.showErrors']);

    app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }]);

    app.config(['showErrorsConfigProvider', function(showErrorsConfigProvider) {
        showErrorsConfigProvider.showSuccess(true);
    }]);

    app.config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl'
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl'
            })
            .when('/register', {
                templateUrl: 'views/register.html',
                controller: 'RegisterCtrl'
            })
            .when('/share/user', {
                templateUrl: 'views/share-user.html',
                controller: 'ShareUserCtrl'
            })
            .when('/share/location', {
                templateUrl: 'views/share-location.html',
                controller: 'ShareLocationCtrl'
            })
            .when('/share/bar/choose', {
                templateUrl: '/views/share-bar-choose.html',
                controller: 'ShareBarChooseCtrl'
            })
            .when('/share/bar/create', {
                templateUrl: '/views/share-bar-create.html',
                controller: 'ShareBarCreateCtrl'
            })
            .when('/share/beer/choose', {
                templateUrl: 'views/share-beer-choose.html',
                controller: 'ShareBeerChooseCtrl'
            })
            .when('/share/beer/create', {
                templateUrl: 'views/share-beer-create.html',
                controller: 'ShareBeerCreateCtrl'
            })
            .when('/share/details', {
                templateUrl: 'views/share-details.html',
                controller: 'ShareDetailsCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });

    // heroku
    app.constant('serviceHost', 'https://durscht-service.herokuapp.com');

    // local
    //app.constant('serviceHost', 'http://127.0.0.1:9000');

})();




