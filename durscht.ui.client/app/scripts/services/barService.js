/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    function service(serviceHost, $http, posting, locationService, beerService) {
        var srv = this;
        srv.getNearBars = function () {
            return $http.post(serviceHost + '/bar/near', {
                latitude: posting.latitude,
                longitude: posting.longitude
            });
        };
        srv.createBar = function (name, website, remark) {
            return $http.post(serviceHost + '/bar/create', {
                name: name,
                web: website,
                remark: remark,
                longitude: posting.longitude,
                latitude: posting.latitude
            });
        };
        srv.getBeersFromBar = function (barId) {
            return $http.get(serviceHost + '/bar/getBeers/' + barId);
        };
        srv.getAllBeers = function () {
            return $http.get(serviceHost + '/beer/getAll');
        };
        srv.getBarsWithFavoriteBeers = function () {
            return $http.post(serviceHost + '/bar/withBeers', {
                latitude: locationService.latitude,
                longitude: locationService.longitude,
                beers: beerService.getFavoritesIds()
            });
        };
        srv.compareByName = function (a, b) {
            if (a.name < b.name) {
                return -1;
            }
            else if (a.name > b.name) {
                return 1;
            }
            else {
                return 0;
            }
        };
        srv.compareByDistance = function (a, b) {
            return a.distance - b.distance;
        };
        srv.getBarDetails = function (barid) {
            return $http.get(serviceHost + '/bar/details/' + barid);
        };
        srv.getPosts = function (barId) {
            return $http.get(serviceHost + '/bar/posts/' + barId);
        };
        return srv;
    }
    app.factory('barService', ['serviceHost', '$http', 'posting', 'locationService', 'beerService', service]);
})(angular.module('durschtApp'));
//# sourceMappingURL=barService.js.map