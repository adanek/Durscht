/// <reference path="../_references.ts"/>
(function (app) {
    app.factory('barService', ['barApiUrl', '$http', 'posting', function (barApiUrl, $http, posting) {
        var getBars = function () {
            return [
                { id: 1, name: "Testbar", distance: "0.0 km", beers: [
                    { id: 0, brand: "Zipfer", type: "Märzen", description: "" },
                    { id: 1, brand: "Stiegl", type: "Goldbräu", description: "" },
                    { id: 2, brand: "Corona", type: "extra", description: "" }
                ] },
                { id: 2, name: "Testbar 2", distance: "0.3 km", beers: [] }
            ];
        };
        var getBar = function (id) {
            var bar = { id: id, name: "Testbar", distance: "0.0 km", beers: [
                { id: 0, brand: "Zipfer", type: "Märzen", description: "" },
                { id: 1, brand: "Stiegl", type: "Goldbräu", description: "" }
            ] };
            return bar;
        };
        var getNearBars = function () {
            return $http.post(barApiUrl + "nearBars", { latitude: posting.latitude, longitude: posting.longitude });
        };
        var createBar = function (name, website, remark) {
            return $http.post(barApiUrl + "createBar", {
                name: name,
                web: website,
                remark: remark,
                longitude: posting.longitude,
                latitude: posting.latitude
            });
        };
        var getBeersFromBar = function (bar) {
            return $http.get(barApiUrl + "beersFromBar/" + bar.id);
        };
        var getAllBeers = function () {
            return $http.get(barApiUrl + "getAllBeers");
        };
        var addBeerToBar = function (bar, beer) {
            return $http.post(barApiUrl + 'bar/beers/add', {
                bar: bar.id,
                beer: beer.id
            });
        };
        return {
            getBars: getBars,
            getBar: getBar,
            getNearBars: getNearBars,
            createBar: createBar,
            getBeersFromBar: getBeersFromBar,
            getAllBeers: getAllBeers,
            addBeerToBar: addBeerToBar
        };
    }]);
})(angular.module('durschtApp'));
//# sourceMappingURL=barService.js.map