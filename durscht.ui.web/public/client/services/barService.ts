/// <reference path="../_references.ts"/>

(function(app){
	
	app.factory('barService', function() : BarService {		
	
		var getBars : () => Array<Bar> = function(){
			return [
				{id: 1, name:"Testbar", distance:"0.0 km", beers:[
					{brand: "Zipfer", type:"Märzen", description:""}
					]},
				{id: 2, name:"Testbar 2", distance:"0.3 km", beers:[]}
			];
		};
		
		var getBar : (id : number) => Bar = function(id){
			
			var bar: Bar = {id:id, name:"Testbar", distance:"0.0 km", beers:[
					{brand: "Zipfer", type:"Märzen", description:""},
					{brand: "Stiegl", type:"Goldbräu", description:""}
					]};
			
			return bar;
		}
		
		return {
			getBars: getBars,
			getBar: getBar
		};
	});
	
})(angular.module('durscht-core'));
