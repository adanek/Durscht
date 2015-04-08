/// <reference path="../_references.ts"/>
	
	
(function (app){
				
	app.service("posting", function (){
		
		var posting : Posting = this;
				
		posting.bar = { id:-1, name:"", distance:"0.0 km", beers:[]};
		posting.beer = {brand:"", type:"", description:""};
		posting.remark = "";
		posting.price = 0.0;	
			
		posting.reset = function () {
			posting.bar = { id:-1, name:"", distance:"0.0 km", beers:[]};
			posting.beer = {brand:"", type:"", description:""};	
			posting.remark = "";
			posting.price = 0.0;				
		};	
	});
	
})(angular.module('durscht-core'));
