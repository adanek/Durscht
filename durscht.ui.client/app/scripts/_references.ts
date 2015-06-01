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

	getNearBars:() => ng.IHttpPromise<Array<Bar>>;
	createBar: (name:String, url:String, remark:String) => ng.IHttpPromise<Bar>;
	getBeersFromBar: (bar:Bar) => ng.IHttpPromise<Array<Beer>>;
	getAllBeers: () => ng.IHttpPromise<Array<Beer>>;
    addBeerToBar: (bar: Bar, beer: Beer) => ng.IPromise<void>;
	getBarsWithFavoriteBeers: () => ng.IHttpPromise<Array<Bar>>;
	getBarDetails: (barId:number) => ng.IHttpPromise<Bar>;

	compareByName: (a: Bar, b:Bar) => number;
	compareByDistance: (a: Bar, b:Bar) => number;
}

interface BeerService {
	createBeer: (brand:string, type:string, description:string) => ng.IHttpPromise<string>;
	getAll: () => ng.IHttpPromise<Array<Beer>>;
	getUsed: () => ng.IHttpPromise<Array<Beer>>;
    setFavorites: (beers:Array<Beer>) => void;
    getFavorites: () => Array<Beer>;

	compareByName: (a:Beer, b:Beer) => number;
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

interface SearchService {
    barId:number;
}

interface AuthenticationService {
	isAuthenticated: () => boolean;
    setAuthenticated: (val : boolean) => void;
	login: (username:String, password:String) => ng.IHttpPromise<void>;
	logout: () => ng.IHttpPromise<void>;
    getId: () => ng.IHttpPromise<string>;
	register: (username:string, email:string, password:string) => ng.IHttpPromise<void>;
}

interface LocationService {
	latitude: number;
	longitude: number;
	locate: ()=> ng.IPromise<void>;
}