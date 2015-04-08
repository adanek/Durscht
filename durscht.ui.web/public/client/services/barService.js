/// <reference path="../_references.ts"/>
(function (app) {
    app.factory('barService', function () {
        var getBars = function () {
            return [
                { id: 1, name: "Testbar", distance: "0.0 km", beers: [
                    { brand: "Zipfer", type: "Märzen", description: "" },
                    { brand: "Stiegl", type: "Goldbräu", description: "" },
                    { brand: "Corona", type: "extra", description: "" }
                ] },
                { id: 2, name: "Testbar 2", distance: "0.3 km", beers: [] }
            ];
        };
        var getBar = function (id) {
            var bar = { id: id, name: "Testbar", distance: "0.0 km", beers: [
                { brand: "Zipfer", type: "Märzen", description: "" },
                { brand: "Stiegl", type: "Goldbräu", description: "" }
            ] };
            return bar;
        };
        return {
            getBars: getBars,
            getBar: getBar
        };
    });
})(angular.module('durscht-core'));
