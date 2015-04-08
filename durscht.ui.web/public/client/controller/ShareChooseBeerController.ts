/// <reference path="../_references.ts"/>
	
(function(app){
	
	var controller = function(posting, $location){			
		
		this.bar = posting.bar;
		
		this.setBeer = function(beer:Beer){
			posting.beer = beer;
			$location.path("/share/details");
		}
	};	
	
	app.controller("ShareChooseBeerController", controller);
	
})(angular.module("durscht-core"));