/// <reference path="../_references.ts"/>

'use strict';

(function (app) {

    var service = function(serviceHost, $http) {

        var srv:AchievementService = this;

        srv.getAll = function(){
            return $http.get(serviceHost + '/user/achievements/all');
        }

        return {
            getAll: srv.getAll
        };
    };

    app.factory('achievementService', ['serviceHost', '$http', service]);


})(angular.module('durschtApp'));

