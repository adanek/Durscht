/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function ($scope, $location, posting, barService) {
        $scope.name = posting.bar.name || "TestBar";
        $scope.createBeerText = "Nö, ganz a anders";
        $scope.caption = "Soso, " + $scope.name + " also";
        barService.getBeersFromBar(posting.bar.id).success(function (data) {
            $scope.beers = data;
            posting.bar.beers = data;
            $scope.$apply();
        });
        $scope.setBeer = function (beer) {
            posting.beer = beer;
            $location.path("/share/details");
        };
        $scope.createBeer = function () {
            $location.path("/share/beer/create").replace();
        };
    };
    app.controller("ShareBeerChooseCtrl", ['$scope', '$location', 'posting', 'barService', controller]);
})(angular.module("durschtApp"));
//# sourceMappingURL=ShareBeerChoose.js.map