/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    app.factory('beerService', ['serviceHost', '$http', function (serviceHost, $http) {
        var favorites = undefined;
        var createBeer = function (brand, type, description) {
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
        var setFavorites = function (beers) {
            favorites = beers;
        };
        var getFavorites = function () {
            return favorites;
        };
        var compareByBrand = function (a, b) {
            if (a.brand < b.brand) {
                return -1;
            }
            else if (a.brand > b.brand) {
                return 1;
            }
            else {
                if (a.type < b.type) {
                    return -1;
                }
                else if (a.type > b.type) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        };
        return {
            createBeer: createBeer,
            getAll: getAll,
            getUsed: getUsed,
            setFavorites: setFavorites,
            getFavorites: getFavorites,
            compareByName: compareByBrand
        };
    }]);
})(angular.module('durschtApp'));
//# sourceMappingURL=beerService.js.map