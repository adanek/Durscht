/// <reference path="../_references.ts"/>
'use strict';
(function (app) {
    var ctrl = function ($scope, posting, authentication, $location) {
        posting.reset();
        $scope.caption = "Kann ich mal deinen Ausweis sehen?";
        authentication.getId().success(function (data) {
            posting.user = data;
            $location.path('/share/location').replace();
        }).error(function (data, status, headers, config) {
            if (status == 401) {
                $location.path('/login').replace();
            }
        });
    };
    app.controller('ShareUserCtrl', ctrl);
})(angular.module('durschtApp'));
//# sourceMappingURL=ShareUser.js.map