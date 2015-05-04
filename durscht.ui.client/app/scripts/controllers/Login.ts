/// <reference path="../_references.ts"/>

(function (app){
    var controller = function ($scope, authService: AuthenticationService, $location){

        $scope.caption = "Dich kenn ich doch!";
        $scope.username = "";
        $scope.password = "";

        $scope.login = function(){
            authService.login($scope.username, $scope.password)
                .success(function(){
                    $location.path('/share/user').replace();
                })
                .error(function(data, status, headers, config){
                    var msg;
                    switch(status){
                        case 401:
                            msg = "Das passt so nicht, denk noch einmal nach";
                            break;
                        default:
                            msg = "Ups, da ging was schief!";
                    }

                    $scope.$apply(function(){
                        $scope.errorMessage = msg;
                    });
                });
        }
    };

    app.controller("LoginCtrl", ['$scope', 'authService', '$location', controller]);

})(angular.module('durschtApp'));