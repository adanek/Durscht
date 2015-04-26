/// <reference path="../_references.ts"/>

(function (app) {

    var ctrl = function($scope, barService : BarService, posting, $location) {

        $scope.caption = "Wie heißt den das Lokal, in dem du bist?";
    };

    app.controller('ShareBarCreateCtrl', ctrl);

})(angular.module('durschtApp'));