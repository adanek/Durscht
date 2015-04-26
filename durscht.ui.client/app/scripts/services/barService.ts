/// <reference path="../_references.ts"/>

(function(app){
	
	app.factory('barService', ['barApiUrl', '$http', 'posting', function(barApiUrl, $http, posting) : BarService {
	
		var getBars : () => Array<Bar> = function(){
			return [
				{id: 1, name:"Testbar", distance:"0.0 km", beers:[
					{id: 0, brand: "Zipfer", type:"Märzen", description:""},
					{id: 1, brand: "Stiegl", type:"Goldbräu", description:""},
					{id: 2, brand: "Corona", type:"extra", description:""}
					]},
				{id: 2, name:"Testbar 2", distance:"0.3 km", beers:[]}
			];
		};
		
		var getBar : (id : number) => Bar = function(id){
			
			var bar: Bar = {id:id, name:"Testbar", distance:"0.0 km", beers:[
					{id: 0, brand: "Zipfer", type:"Märzen", description:""},
					{id: 1, brand: "Stiegl", type:"Goldbräu", description:""}
					]};
			
			return bar;
		}
		
      	var getNearBars = function(){
        	return $http.post(barApiUrl + "nearBars", {latitude: posting.latitude, longitude: posting.longitude});
        };

		var createBar = function(name:string, website:string, remark:string){

			return $http.post(barApiUrl + "createBar", {
				name: name,
				web: website,
				remark: remark,
				longitude: posting.longitude,
				latitude: posting.latitude,
			});
		}

        var getAllBeers = function(){

            return $http.get(barApiUrl + "getAllBeers");
        }

        var addBeerToBar = function(bar : Bar, beer: Beer){
            return $http.post(barApiUrl + 'bar/beers/add', {
                bar: bar.id,
                beer: beer.id
            });
        }
        
        return {
            getBars: getBars,
            getBar: getBar,
            getNearBars: getNearBars,
			createBar: createBar,
            getAllBeers: getAllBeers,
            addBeerToBar: addBeerToBar
        };
	}]);
	
})(angular.module('durschtApp'));
