/// <reference path="../_references.ts"/>
'use strict';
/**
 * @ngdoc service
 * @name durschtApp.posting
 * @description
 * # posting
 * Provides the service to hold the required data for a post.
 */
(function (app) {
    app.service('posting', ['$http', 'serviceHost', function ($http, serviceHost) {
        var posting = this;
        posting.id = -1;
        posting.userId = -1;
        posting.username = "";
        posting.bar = null;
        posting.beer = null;
        posting.description = null;
        posting.price = 0.0;
        posting.rating = 0;
        posting.date = 0;
        posting.reset = function () {
            posting.id = -1;
            posting.userId = -1;
            posting.username = "";
            posting.bar = null;
            posting.beer = null;
            posting.description = null;
            posting.price = 0.0;
            posting.rating = 0;
            posting.date = 0;
        };
        posting.save = function () {
            return $http.post(serviceHost + '/share/createPost', {
                user: posting.userId,
                bar: posting.bar.id,
                beer: posting.beer.id,
                price: posting.price,
                rating: posting.rating,
                remark: posting.description
            });
        };
        posting.compareByDateDsc = function (a, b) {
            return b.date - a.date;
        };
    }]);
})(angular.module('durschtApp'));
//# sourceMappingURL=posting.js.map