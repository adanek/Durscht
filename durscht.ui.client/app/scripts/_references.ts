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
	latitude: number;
	longitude: number;
	beers: Array<Beer>;
	url: string;
}

interface BarService {

	getNearBars:() => ng.IHttpPromise<Array<Bar>>;
	createBar: (name:String, url:String, remark:String) => ng.IHttpPromise<Bar>;
	getBeersFromBar: (barId:number) => ng.IHttpPromise<Array<Beer>>;
	getAllBeers: () => ng.IHttpPromise<Array<Beer>>;
    addBeerToBar: (bar: Bar, beer: Beer) => ng.IPromise<void>;
	getBarsWithFavoriteBeers: () => ng.IHttpPromise<Array<Bar>>;
	getBarDetails: (barId:number) => ng.IHttpPromise<Bar>;
	getPosts: (barId: number) => ng.IHttpPromise<Array<Posting>>;

	compareByName: (a: Bar, b:Bar) => number;
	compareByDistance: (a: Bar, b:Bar) => number;
}

interface BeerService {
	createBeer: (brand:string, type:string, description:string) => ng.IHttpPromise<string>;
	getAll: () => ng.IHttpPromise<Array<Beer>>;
	getUsed: () => ng.IHttpPromise<Array<Beer>>;
    setFavorites: (beers:Array<Beer>) => void;
    getFavorites: () => Array<Beer>;
	getFavoritesIds: () => Array<number>;

	compareByName: (a:Beer, b:Beer) => number;
}

interface Posting {
	id: number;
    userId: number;
    username: string;
	bar: Bar;
	beer: Beer;
	description: string;
	price: number;
	rating: number;
	longitude: number;
    latitude: number;
    date: number;

	reset: () => void;
    save: () => ng.IHttpPromise<void>;
	compareByDateDsc: (a:Posting, b:Posting) => number;
}

interface SearchService {
    bars: Array<Bar>;
	chosenBar: Bar;
	clear: () => void;
}

interface AuthenticationService {
	isAuthenticated: () => boolean;
    setAuthenticated: (val : boolean) => void;
	login: (username:String, password:String) => ng.IHttpPromise<void>;
	setReferrer: (url:string) => void;
	getReferrer: () => string;
	logout: () => ng.IHttpPromise<void>;
    getId: () => ng.IHttpPromise<string>;
	register: (username:string, email:string, password:string) => ng.IHttpPromise<void>;
}

interface LocationService {
	latitude: number;
	longitude: number;
	locate: ()=> ng.IPromise<void>;
}

interface Marker {
	id: number;
	coords: Coords;
}

interface Coords {
	latitude: number;
	longitude: number;
}

interface Achievement {

	name:string;
	progress:number;
	url: string;
}

interface AchievementService {
	getAll: () => ng.IHttpPromise<Array<Achievement>>;
}