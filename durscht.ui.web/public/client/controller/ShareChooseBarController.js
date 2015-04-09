/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function (barService, posting, $location) {
        var self = this;
        this.caption = "Wo gesch denn um?";
        self.bars = [];
        barService.getNearBars().success(function (data) {
            self.bars = data;
        });
        var createBarText = "";
        if (this.bars.length <= 0) {
            createBarText = "Du bist der erste hier!";
        }
        else {
            createBarText = "Nö, ganz wo anders";
        }
        this.createBarText = createBarText;
        this.setBar = function (bar) {
            posting.bar = bar;
            $location.path("/share/beer/choose").replace();
        };
        this.createBar = function () {
            $location.path("/share/bar/create").replace();
        };
    };
    app.controller("ShareChooseBarController", controller);
})(angular.module('durscht-core'));
