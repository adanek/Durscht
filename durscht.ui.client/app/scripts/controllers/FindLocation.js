/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, $location, locationService) {
        $scope.caption = "Mal schaun, ob ich dich finden kann...";
        locationService.locate().then(
        // Change the view after the client is located
        function (test) {
            $location.path('/find/beers').replace();
        }, 
        // or show the error message to the client
        function (error) {
            $scope.errorMessage = error;
            console.info("Can't locate client!");
        });
    };
    app.controller('FindLocationCtrl', ['$scope', '$location', 'locationService', ctrl]);
})(angular.module('durschtApp'));
//# sourceMappingURL=FindLocation.js.map