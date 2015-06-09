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

        var getFavoritesIds = function() {
            var ids:Array<number> = [];

            favorites.forEach(function(beer:Beer){
                ids.push(beer.id);
            })

            return ids;
        }

        var compareByBrand =  function (a:Beer, b:Beer) {
            if (a.brand < b.brand) {
                return -1
            }
            else if (a.brand > b.brand) {
                return 1;
            }
            else {
                if (a.type < b.type) {
                    return -1;
                } else if (a.type > b.type) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        return {
            createBeer: createBeer,
            getAll: getAll,
            getUsed: getUsed,
            setFavorites: setFavorites,
            getFavorites: getFavorites,
            getFavoritesIds: getFavoritesIds,
            compareByName: compareByBrand
        };
    }]);

})(angular.module('durschtApp'));
