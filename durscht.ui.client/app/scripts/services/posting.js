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
        posting.userId = -1;
        posting.userName = "";
        posting.bar = null;
        posting.beer = null;
        posting.remark = null;
        posting.price = 0.0;
        posting.rating = 0;
        posting.reset = function () {
            posting.userId = -1;
            posting.userName = "";
            posting.bar = null;
            posting.beer = null;
            posting.remark = null;
            posting.price = 0.0;
            posting.rating = 0;
        };
        posting.save = function () {
            return $http.post(serviceHost + '/share/createPost', {
                user: posting.userId,
                bar: posting.bar.id,
                beer: posting.beer.id,
                price: posting.price,
                rating: posting.rating,
                remark: posting.remark
            });
        };
    }]);
})(angular.module('durschtApp'));
//# sourceMappingURL=posting.js.map