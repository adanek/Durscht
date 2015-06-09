/// <reference path="../_references.ts"/>
'use strict';

(function (app) {

    var ctrl = function ($scope, $location, achievementService:AchievementService, authService:AuthenticationService) {

        var achievements:Array<Achievement> = [{
            name: '3 Posts in einer Woche',
            progress: 10,
            url: ''
        }, {name: 'Einen Post erstellt', progress: 100, url: ''}];

        $scope.caption = "Deine Errungenschaften";
        $scope.achievements = achievements;
        $scope.progress = 100;

        achievementService.getAll()
            .success(function (data) {
                achievements = data;
                $scope.achievements = achievements;
            }).error(function (data, status) {
                if (status == 401) {
                    authService.setReferrer('/achievements/all');
                    $location.path('/login');
                }
            });
    }

    app.controller('AchievementsCtrl', ['$scope', '$location', 'achievementService', 'authService', ctrl]);

})(angular.module('durschtApp'));