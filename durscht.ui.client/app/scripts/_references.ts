/// <reference path="../../bower_components/typings/angular/angular.d.ts"/>
/// <reference path="../../bower_components/typings/jquery/jquery.d.ts"/>

interface Beer {
	brand: string;
	type: string;
	description: string;
}

interface Bar {
	id:number;
	name: string;
	distance: string;
	beers: Array<Beer>;		
}

interface BarService {
	getBars: () => Array<Bar>;
	getBar: (id:number) => Bar;
	getNearBars;	
}

interface Posting {
	bar: Bar;
	beer: Beer;
	remark: string;
	price: number;
	rating: number;
	
	reset: () => void;
}