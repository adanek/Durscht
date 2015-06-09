/// <reference path="../_references.ts"/>

'use strict';

(function (app) {

    app.service('searchService', [function () {
        var srv:SearchService = this;
        var bars:Array<Bar> = [];
        var chosenBar:Bar = undefined;

        srv.bars = bars;
        srv.chosenBar = chosenBar;

        srv.clear = function () {
            srv.bars = [];
            srv.chosenBar = undefined;
        };
    }])


})(angular.module('durschtApp'));

