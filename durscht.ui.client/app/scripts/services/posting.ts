/// <reference path="../_references.ts"/>

'use strict';

/**
 * @ngdoc service
 * @name durschtApp.posting
 * @description
 * # posting
 * Provides the service to hold the required data for a post.
 */

(function(app){

    app.service('posting', function(){
        var posting : Posting = this;

        posting.bar = null;
        posting.beer = null;
        posting.remark = null;
        posting.price = 0.0;
        posting.rating = 0;


        posting.reset = function(){
            posting.bar = null;
            posting.beer = null;
            posting.remark = null;
            posting.price = 0.0;
            posting.rating = 0;
        };
    })


})(angular.module('durschtApp'));

