/// <reference path="../_references.ts"/>
(function (app) {
    app.service("login", function () {
        var login = this;
        login.user = { username: "", password: "" };
        login.reset = function () {
            login.user = { username: "", password: "" };
        };
    });
})(angular.module('durscht-core'));
