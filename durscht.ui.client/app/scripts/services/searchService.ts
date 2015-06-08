/// <reference path="../_references.ts"/>

'use strict';

(function (app) {

    app.service('searchService', [function () {
        var srv:SearchService = this;
        var barId:number = 0;
        srv.barId = barId;
    }])


})(angular.module('durschtApp'));

