/// <reference path="../lib/typings/angular/angular.d.ts"/>
(function () {
    var app = angular.module("durscht", ["ngRoute"]);
    var config = function ($routeProvider) {
        $routeProvider.when("/", { templateUrl: "/assets/client/views/index.html" }).when("/bar/choose", { templateUrl: "/assets/client/views/bar-choose.html" }).when("/bar/create", { templateUrl: "/assets/client/views/bar-create.html" }).when("/bar/:id", { templateUrl: "/assets/client/views/bar-details.html" }).otherwise({ redirectTo: "/" });
    };
    app.config(config);
    var titleController = function () {
        this.title = "Durscht App V0.1";
    };
    app.controller("TitleController", titleController);
    app.controller("BarChooserController", function ($routeParams) {
        this.caption = "Bist du da?";
        var bars = [
            { id: 1, name: "GigaBar", distance: "1.2km" },
            { id: 2, name: "SegaBar", distance: "0.8km" }
        ];
        this.bars = bars;
    });
    app.controller("BarDetailsController", function ($routeParams) {
        var id = $routeParams.id;
        var b = { id: id, name: "Selected Bar", distance: "0.0km" };
        this.bar = b;
    });
})();
