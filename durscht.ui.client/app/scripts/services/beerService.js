/// <reference path="../_references.ts"/>
(function (app) {
    app.factory('beerService', ['serviceHost', '$http', function (serviceHost, $http) {
        var createBeer = function (brand, type, description) {
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
//# sourceMappingURL=beerService.js.map