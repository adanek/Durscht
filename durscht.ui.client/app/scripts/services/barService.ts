/// <reference path="../_references.ts"/>
'use strict';


(function (app) {

    function service(serviceHost, $http, posting, locationService:LocationService, beerService:BeerService) {

        var srv:BarService = this;


        srv.getNearBars = function () {
            return $http.post(serviceHost + '/bar/near', {
                latitude: posting.latitude,
                longitude: posting.longitude
            });
        };

        srv.createBar = function (name:string, website:string, remark:string) {

            return $http.post(serviceHost + '/bar/create', {
                name: name,
                web: website,
                remark: remark,
                longitude: posting.longitude,
                latitude: posting.latitude,
            });
        }

        srv.getBeersFromBar = function (barId: number) {
            return $http.get(serviceHost + '/bar/getBeers/' + barId);
        }

        srv.getAllBeers = function () {

            return $http.get(serviceHost + '/beer/getAll');
        }

        srv.getBarsWithFavoriteBeers = function () {
            return $http.post(serviceHost + '/bar/withBeers', {
                latitude: locationService.latitude,
                longitude: locationService.longitude,
                beers: beerService.getFavorites()
            });
        }

        srv.compareByName = function(a: Bar, b:Bar){

            if(a.name < b.name){
                return -1;
            } else if( a.name > b.name){
                return 1;
            } else {
                return 0;
            }
        }

        srv.compareByDistance = function(a:Bar, b:Bar){
            return a.distance - b.distance;
        }

        srv.getBarDetails = function(barid:number){
            return $http.get(serviceHost + '/bar/details/' + barid);
        }

        srv.getPosts = function(barId:number){
            return $http.get(serviceHost + '/bar/posts/' + barId);
        }

        return srv;
    }

    app.factory('barService', ['serviceHost', '$http', 'posting', 'locationService', 'beerService', service]);

})(angular.module('durschtApp'));
