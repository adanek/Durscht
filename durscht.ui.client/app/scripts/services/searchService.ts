/// <reference path="../_references.ts"/>

'use strict';

(function (app) {

    app.service('searchService', [function () {
        var srv:SearchService = this;
        var bars:Array<Bar> = [];
        var choosenBar:Bar = undefined;
        srv.barId = barId;

        srv.bars = bars;
        srv.choosenBar = choosenBar;

        srv.clear = function () {
          srv.bars = [];
            srv.choosenBar = undefined;
        };
    }])


})(angular.module('durschtApp'));

