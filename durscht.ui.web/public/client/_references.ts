/// <reference path="./lib/typings/angular/angular.d.ts"/>
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
}
