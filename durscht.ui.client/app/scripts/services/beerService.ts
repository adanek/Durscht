/// <reference path="../_references.ts"/>
'use strict';


(function (app) {

    app.factory('beerService', ['serviceHost', '$http', function (serviceHost, $http):BeerService {

        var favorites:Array<Beer> = undefined;

        var createBeer = function (brand:string, type:string, description:string) {
            return $http.post(serviceHost + '/beer/create', {
                brand: brand,
                type: type,
                description: description
            });
        };

        var getAll = function () {
            return $http.get(serviceHost + '/beer/all');
        };

        var getUsed = function () {
            return $http.get(serviceHost + '/beer/used');
        };

        var setFavorites = function (beers:Array<Beer>) {
            favorites = beers;
        };

        var getFavorites = function () {
            return favorites;
        }

        return {
            createBeer: createBeer,
            getAll: getAll,
            getUsed: getUsed,
            setFavorites: setFavorites,
            getFavorites: getFavorites
        };
    }]);

})(angular.module('durschtApp'));
