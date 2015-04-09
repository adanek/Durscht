/// <reference path="../_references.ts"/>

(function (app){
	var controller = function (posting:Posting, $scope){
		this.title = "Aha!";
		this.posting = posting;		
	};
	
	app.controller("ShareDetailsController", controller);
	
})(angular.module('durscht-core'));