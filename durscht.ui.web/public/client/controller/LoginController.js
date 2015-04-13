/// <reference path="../_references.ts"/>
(function (app) {
	
    var controller = function (barService, posting, $location) {
        this.title = "Title";
    };
    
    app.controller("LoginController", controller);
    
})(angular.module('durscht-core'));