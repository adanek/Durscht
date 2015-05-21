/// <reference path="../_references.ts"/>
(function (app) {
    var service = function ($window, $q) {
        var srv = this;
        srv.latitude = undefined;
        srv.longitude = undefined;
        srv.locate = function () {
            var deferred = $q.defer();
            var msg;
            if ($window.navigator.geolocation) {
                $window.navigator.geolocation.getCurrentPosition(function (position) {
                    srv.latitude = position.coords.latitude;
                    srv.longitude = position.coords.longitude;
                    deferred.resolve();
                }, function (error) {
                    switch (error.code) {
                        case error.PERMISSION_DENIED:
                            msg = "So wird des nichts mit deiner Anfrage! Keine Position, kein Service";
                            break;
                        case error.POSITION_UNAVAILABLE:
                            msg = "Dein Handy weigert sich mir deine aktuelle Position zu verraten!";
                            break;
                        case error.TIMEOUT:
                            msg = "Ich kann ja nicht ewig auf dich warten....";
                            break;
                        case error.UNKNOWN_ERROR:
                            msg = "Mit dem hab i jetzt nicht grechnet.";
                            break;
                    }
                    return deferred.reject(msg);
                });
            }
            else {
                msg = "Dein Handy kann seine aktuelle Position nicht feststellen.";
            }
            return deferred.promise;
        };
        return srv;
    };
    app.factory('locationService', ['$window', '$q', service]);
})(angular.module('durschtApp'));
//# sourceMappingURL=locationService.js.map