/// <reference path="../_references.ts"/>
(function (app) {
    function service(serviceHost, $http, posting) {
        var srv = this;
        srv.getNearBars = function () {
            return $http.post(serviceHost + "/share/nearBars", {
                latitude: posting.latitude,
                longitude: posting.longitude
            });
        };
        srv.createBar = function (name, website, remark) {
            return $http.post(serviceHost + "/share/createBar", {
                name: name,
                web: website,
                remark: remark,
                longitude: posting.longitude,
                latitude: posting.latitude
            });
        };
        srv.getBeersFromBar = function (bar) {
            return $http.get(serviceHost + "/share/beersFromBar/" + bar.id);
        };
        srv.getAllBeers = function () {
            return $http.get(serviceHost + "/share/getAllBeers");
        };
        return srv;
    }
    app.factory('barService', ['serviceHost', '$http', 'posting', service]);
})(angular.module('durschtApp'));
//# sourceMappingURL=barService.js.map