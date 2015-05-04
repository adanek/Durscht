/// <reference path="../_references.ts"/>

'use strict';

(function (app) {

    var ctrl = function ($scope, posting:Posting, authService:AuthenticationService, $location) {
        posting.reset();
        $scope.caption = "Kann ich mal deinen Ausweis sehen?";

        authService.getId()
            .success(function(data){
                posting.user = parseInt(data);
                authService.setAuthenticated(true);
                $location.path('/share/location').replace();
            })
            .error(function(data, status, headers, config){
            if(status == 401){
                var h = headers;
                $location.path('/login').replace();
            }
        });
    };

    app.controller('ShareUserCtrl', ['$scope', 'posting', 'authService', '$location', ctrl]);

})(angular.module('durschtApp'));

