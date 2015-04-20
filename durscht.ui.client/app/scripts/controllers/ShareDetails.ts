/// <reference path="../_references.ts"/>

(function (app){
    var controller = function ($scope, posting:Posting){
        $scope.caption = "Aha!";
        $scope.remark = "";
        $scope.price = 0.0;
    };

    app.controller("ShareDetailsCtrl", controller);

})(angular.module('durschtApp'));