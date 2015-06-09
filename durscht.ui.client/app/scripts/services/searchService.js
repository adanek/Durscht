/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    app.service('searchService', [function () {
        var srv = this;
        var bars = [];
        var chosenBar = undefined;
        srv.bars = bars;
        srv.chosenBar = chosenBar;
        srv.clear = function () {
            srv.bars = [];
            srv.chosenBar = undefined;
        };
    }]);
})(angular.module('durschtApp'));
//# sourceMappingURL=searchService.js.map