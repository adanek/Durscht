/// <reference path="./lib/typings/angular/angular.d.ts"/>
/// <reference path="./lib/typings/jquery/jquery.d.ts"/>

/// <reference path="./services/barService.ts"/>


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

interface AuthenticationService {

	login: (user:string, passwd:string) => void;
}