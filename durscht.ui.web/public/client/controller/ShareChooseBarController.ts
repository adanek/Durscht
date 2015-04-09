/// <reference path="../_references.ts"/>

(function(app){
	
	var controller = function(barService : BarService, posting, $location) {
		
		this.caption = "Wo gesch denn um?"
		this.bars = barService.getBars();
		
		var createBarText : string = "";
		if(this.bars.length <= 0){
			createBarText = "Du bist der erste hier!";
		}
		else {
			createBarText = "Nö, ganz wo anders";
		}
		this.createBarText = createBarText;
		
		this.setBar = function(bar){
			posting.bar = bar;
			$location.path("/share/beer/choose").replace();
		};
		
		this.createBar = function (){
			$location.path("/share/bar/create").replace();
		};		
	};
	
	app.controller("ShareChooseBarController", controller);	

})(angular.module('durscht-core'));