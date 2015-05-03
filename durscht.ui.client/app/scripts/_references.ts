/// <reference path="../../bower_components/typings/jquery/jquery.d.ts"/>
/// <reference path="../../bower_components/typings/angularjs/angular.d.ts"/>

interface Beer {
    id: number;
	brand: string;
	type: string;
	description: string;
}

interface Bar {
	id:number;
	name: string;
	distance: number;
	beers: Array<Beer>;		
}

interface BarService {
	getBars: () => Array<Bar>;
	getBar: (id:number) => Bar;
	getNearBars;
	createBar: (name:String, url:String, remark:String) => ng.IHttpPromise<Bar>;
	getBeersFromBar: (bar:Bar) => ng.IHttpPromise<Array<Beer>>;
	getAllBeers: () => ng.IHttpPromise<Array<Beer>>;
    addBeerToBar: (bar: Bar, beer: Beer) => ng.IPromise<void>;
}

interface BeerService {
	createBeer: (brand:string, type:string, description:string) => ng.IHttpPromise<number>;
}

interface Posting {
	user: number;
	bar: Bar;
	beer: Beer;
	remark: string;
	price: number;
	rating: number;
	longitude: number;
    latitude: number;

	reset: () => void;
    save: () => ng.IHttpPromise<void>;
}

interface AuthenticationSevice {
	login: (username:String, passwd:String) => ng.IHttpPromise<void>;
    getId: () => ng.IHttpPromise<number>;
}
