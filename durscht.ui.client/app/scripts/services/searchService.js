/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    app.service('searchService', [function () {
        var srv = this;
        var barId = 0;
        var bars = [];
        var choosenBar = undefined;
        srv.barId = barId;
        srv.bars = bars;
        srv.choosenBar = choosenBar;
        srv.clear = function () {
            srv.bars = [];
            srv.choosenBar = undefined;
            srv.barId = -1;
        };
    }]);
})(angular.module('durschtApp'));
//# sourceMappingURL=searchService.js.map