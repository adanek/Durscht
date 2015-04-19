/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function (posting, $location) {
        this.bar = posting.bar;
        this.createBeerText = "Nö, ganz a anders";
        if (this.bar.beers.length <= 0) {
            this.createBeerText = "Öha! Du bist der erste hier!";
        }
        this.setBeer = function (beer) {
            posting.beer = beer;
            $location.path("/share/details");
        };
        this.createBeer = function () {
            $location.path("/share/beer/create").replace();
        };
    };
    app.controller("ShareChooseBeerController", controller);
})(angular.module("durscht-core"));
//# sourceMappingURL=ShareChooseBeerController.js.map