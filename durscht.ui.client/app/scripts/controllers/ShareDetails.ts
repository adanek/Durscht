/// <reference path="../_references.ts"/>

(function (app){
    var controller = function ($scope, $location, posting:Posting){
        $scope.caption = "Aha!";
        $scope.remark = "";
        $scope.price = 0.0;


        $scope.sentPost = function(){
            $location.path("/").replace();
        }
    };

    app.controller("ShareDetailsCtrl", controller);

})(angular.module('durschtApp'));