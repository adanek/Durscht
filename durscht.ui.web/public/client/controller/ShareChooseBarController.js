/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function (barService, posting, $location) {
        this.caption = "Wo gesch denn um?";
        this.bars = barService.getBars();
        this.setBar = function (bar) {
            posting.bar = bar;
            $location.path("/share/beer/choose");
        };
        this.createBar = function () {
            $location.path("/share/bar/create");
        };
    };
    app.controller("ShareChooseBarController", controller);
})(angular.module('durscht-core'));
