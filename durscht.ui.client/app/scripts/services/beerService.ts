/// <reference path="../_references.ts"/>

(function(app){

    app.factory('beerService', ['serviceHost', '$http', function(serviceHost, $http) : BeerService {

        var createBeer = function(brand:string, type:string, description:string){
            return $http.post(serviceHost + '/beer/create', {
                brand: brand,
                type: type,
                description: description
            });
        };

        return {
            createBeer: createBeer
        };
    }]);

})(angular.module('durschtApp'));
