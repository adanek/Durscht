/// <reference path="../_references.ts"/>

(function (app){
    var controller = function ($scope, $location, posting:Posting){
        $scope.caption = "Und wie hat's dir gemundet?";
        $scope.remark = "";
        $scope.price = 0.0;
        $scope.rating = 0;


        $scope.sentPost = function(){
            $location.path("/").replace();
        }
    };

    app.controller("ShareDetailsCtrl", controller);

})(angular.module('durschtApp'));