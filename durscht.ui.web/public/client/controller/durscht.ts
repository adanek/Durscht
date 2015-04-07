/// <reference path="../_references.ts"/>
	
(function(){
	var app = angular.module("durscht-core", ["ngRoute"]);
	
	var config = function($routeProvider){
		
		$routeProvider
			.when("/", {templateUrl:"/assets/client/views/index.html"})			
			.when("/bar/choose", {templateUrl: "/assets/client/views/bar-choose.html"})
			.when("/bar/create",{templateUrl: "/assets/client/views/bar-create.html"})
			.when("/bar/:id", {templateUrl: "/assets/client/views/bar-details.html"})
			.otherwise({redirectTo: "/"});
	};
	
	app.config(config);			

})();
