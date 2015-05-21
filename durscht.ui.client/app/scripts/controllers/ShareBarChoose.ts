/// <reference path="../_references.ts"/>

(function (app) {

    var ctrl = function ($scope, barService:BarService, posting, $location) {

        $scope.caption = "Ich schau kurz nach, was ich in deiner Nähe schon kenne ...";

        function sortBarsByDistance(a:Bar, b:Bar) {
            return a.distance - b.distance;
        }

        //$scope.bars = []; //barService.getBars();
        barService.getNearBars()
            .success(function (data) {
                $scope.bars = data.sort(sortBarsByDistance);

                if ($scope.bars.length == 0) {
                    $location.path('/share/bar/create').replace();
                } else {

                    $scope.caption = "Bist du in einer dieser Bars?"
                    $scope.createBarText = "Nö, ganz wo anders";
                }

                $scope.$apply();
            })
            .error(function (data, status, headers, config) {

                switch (status) {
                    case 401:
                        $location.path("/login").replace();
                        $scope.$apply();
                }
            });


        $scope.setBar = function (bar) {
            posting.bar = bar;
            $location.path("/share/beer/choose").replace();
        };

        $scope.createBar = function () {
            $location.path("/share/bar/create").replace();
        };
    };

    app.controller('ShareBarChooseCtrl', ['$scope', 'barService', 'posting', '$location', ctrl]);

})(angular.module('durschtApp'));