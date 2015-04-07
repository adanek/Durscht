/// <reference path="../_references.ts"/>
	
(function(app){
	
	var controller = function($routeParams, barService:BarService){	
		
		var id = $routeParams.id;
		var bar = barService.getBar(id);
		
		this.bar = bar;
	};	
	
	app.controller("BarDetailsController", controller);
	
})(angular.module("durscht-core"));