/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, barService, $routeParams) {
        var caption;
        var barId = $routeParams.barId;
        var bar;
        var beers;
        barService.getBarDetails(barId).success(function (data) {
            bar = data;
            $scope.caption = bar.name;
        });
        barService.getBeersFromBar(barId).success(function (data) {
            beers = data;
        });
    };
    app.controller('FindBarDetailsCtrl', ['$scope', '$location', 'barService', '$routeParams', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindBarDetails.js.map