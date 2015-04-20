/// <reference path="../_references.ts"/>
(function (app) {
	
    var controller = function ($scope, authentication) {


        $scope.title = "Login";

        $scope.username = "";
        $scope.password = "";

        $scope.login = function(){
            authentication.login($scope.username, $scope.password);
        }
    };
    
    app.controller("LoginController", controller);
    
})(angular.module('durscht-core'));