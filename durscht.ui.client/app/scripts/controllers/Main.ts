/// <reference path="../_references.ts"/>

(function (app){
    var controller = function ($scope, authService : AuthenticationService, $location){

        var setAuthBtnText = function (authenticated){
            if(authenticated == true){
                $scope.authbtn.text = "Logout";
            } else {
                $scope.authbtn.text = "Login";
            }
        }

        $scope.authbtn = {};
        setAuthBtnText(authService.isAuthenticated());

        $scope.$watch(function(){
            return authService.isAuthenticated();
        }, function(newVal){
            setAuthBtnText(newVal);
        });

        $scope.onAuthBtnClick = function () {
            if(authService.isAuthenticated()){
                authService.logout().success(function (){
                    authService.setAuthenticated(false);
                    $location.path('/').replace();
                    $scope.$apply();
                });
            } else {
                $location.path('/login').replace();
            }
        }
    };

    app.controller("MainCtrl", ['$scope', 'authService', '$location', controller]);

})(angular.module('durschtApp'));