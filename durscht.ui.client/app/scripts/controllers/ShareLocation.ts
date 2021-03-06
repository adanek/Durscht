/// <reference path="../_references.ts"/>

'use strict';

/**
 * @ngdoc function
 * @name durschtApp.controller:ShareCtrl
 * @description
 * # ShareCtrl
 * Controller of the durschtApp
 */
(function (app) {

    var ctrl = function (posting:Posting, $window, $scope, $location) {
        $scope.caption = "Mal schaun, ob ich dich finden kann...";

        // Check if geolocation is supported
        if ($window.navigator.geolocation) {
            // Get the current position
            $window.navigator.geolocation.getCurrentPosition(function (position) {

                // Save the location the the posting
                var latitude = position.coords.latitude;
                var longitude = position.coords.longitude;

                // Save the location to the posting
                posting.latitude = latitude;
                posting.longitude = longitude;

                $scope.$apply(function () {
                    $scope.lat = latitude;
                    $scope.longitude = longitude;
                });

                // redirect to choose bar
                $location.path("/share/bar/choose").replace();
                $scope.$apply();

            }, function (error) {

                var msg;
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                        msg = "So wird des nichts mit deinem Post! Keine Position, kein Posting"
                        break;
                    case error.POSITION_UNAVAILABLE:
                        msg = "Kaff da a gescheits Handy!"
                        break;
                    case error.TIMEOUT:
                        msg = "Ich kann ja nicht ewig auf dich warten...."
                        break;
                    case error.UNKNOWN_ERROR:
                        msg = "Mit dem hab i jetzt nicht grechnet."
                        break;
                }

                $scope.$apply(function () {
                    $scope.errorMessage = msg;
                });
            });
        } else {

            // Display message that geo location is not supported
            $scope.errorMessage = "Tut leid, aber ohne deine aktulle Position kommst du hier nicht rein";
        }
    };

    app.controller('ShareLocationCtrl', ['posting', '$window', '$scope', '$location', ctrl]);

})(angular.module('durschtApp'));

