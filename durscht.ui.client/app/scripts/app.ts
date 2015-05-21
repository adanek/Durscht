/// <reference path="_references.ts"/>
'use strict';

(function (){
    var app = angular.module('durschtApp', ['ngRoute', 'ui.bootstrap', 'ui.bootstrap.showErrors', 'ngSanitize', 'ui.select']);

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
            .when('/find/location', {
                templateUrl: 'views/share-location.html',
                controller: 'FindLocationCtrl'
            })
            .when('/find/beers', {
                templateUrl: 'views/find-beer-choose.html',
                controller: 'FindBeerChooseCtrl'
            })
            .when('/find/bars', {
                templateUrl: 'views/find-bar-list.html',
                controller: 'FindBarListCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });

    // heroku
    //app.constant('serviceHost', 'https://durscht-service.herokuapp.com');

    // local
    app.constant('serviceHost', 'http://192.168.1.11:9000');

    app.filter('propsFilter', function() {
        return function(items, props) {
            var out = [];

            if (angular.isArray(items)) {
                items.forEach(function(item) {
                    var itemMatches = false;

                    var keys = Object.keys(props);
                    for (var i = 0; i < keys.length; i++) {
                        var prop = keys[i];
                        var text = props[prop].toLowerCase();
                        if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                            itemMatches = true;
                            break;
                        }
                    }

                    if (itemMatches) {
                        out.push(item);
                    }
                });
            } else {
                // Let the output be the input untouched
                out = items;
            }

            return out;
        }
    });

})();




