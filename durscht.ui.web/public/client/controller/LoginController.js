/// <reference path="../_references.ts"/>
(function (app) {
	
    var controller = function (barService, posting, $location) {
        this.title = "Login";
    };
    
    app.controller("LoginController", controller);
    
})(angular.module('durscht-core'));