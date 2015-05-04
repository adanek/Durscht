/// <reference path="../_references.ts"/>

(function (app){
    var controller = function ($scope, authService : AuthenticationService, $location){

        $scope.authBtnText = "";
        setAuthBtnText(authService.isAuthenticated);

        function setAuthBtnText (authenticated){

            if(authenticated == true){

                $scope.authBtnText = "Logout";
            } else {

                $scope.authBtnText = "Login";
            }
        }

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