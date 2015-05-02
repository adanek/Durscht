/// <reference path="../_references.ts"/>

(function (app) {

    var ctrl = function($scope, barService : BarService, posting, $location) {

        $scope.caption = "Ich schau kurz nach, was ich hier kenne...";

        //$scope.bars = []; //barService.getBars();
        barService.getNearBars().success(function (data){
            $scope.bars = data.sort(function(a:Bar, b:Bar){
                return a.distance - b.distance;
            });
            setCreateBarText();
            $scope.$apply();
        }).error(function (data, status, headers, config){

            console.warn("received " + status);
            switch(status){
                case 401:
                    $location.path("/login").replace();
                    $scope.$apply();
            }
        });

        var setCreateBarText = function() {
            if ($scope.bars) {
                var createBarText:string = "";
                if ($scope.bars.length <= 0) {
                    $scope.caption = "Du bist der erste hier!"
                    createBarText = "Neue Bar anlegen";
                }
                else {
                    $scope.caption = "Bist du in einer dieser Bars?"
                    createBarText = "Nö, ganz wo anders";
                }
                $scope.createBarText = createBarText;
            }
        }

        $scope.setBar = function(bar){
            posting.bar = bar;
            $location.path("/share/beer/choose").replace();
        };

        $scope.createBar = function (){
            $location.path("/share/bar/create").replace();
        };
    };

    app.controller('ShareBarChooseCtrl', ['$scope', 'barService', 'posting', '$location', ctrl]);

})(angular.module('durschtApp'));