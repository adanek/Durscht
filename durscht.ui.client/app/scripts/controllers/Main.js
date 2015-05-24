/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function ($scope, authService, $location) {
        $scope.authBtnText = "";
        setAuthBtnText(authService.isAuthenticated);
        function setAuthBtnText(authenticated) {
            if (authenticated == true) {
                $scope.authBtnText = "Logout";
            }
            else {
                $scope.authBtnText = "Login";
            }
        }
        $scope.$watch(function () {
            return authService.isAuthenticated();
        }, function (newVal) {
            setAuthBtnText(newVal);
        });
        $scope.onAuthBtnClick = function () {
            if (authService.isAuthenticated()) {
                authService.logout().success(function () {
                    authService.setAuthenticated(false);
                    $location.path('/').replace();
                    $scope.$apply();
                });
            }
            else {
                $location.path('/login').replace();
            }
        };
        $scope.$on('$viewContentLoaded', function () {
            console.info("Now");
            adjustBodySize();
        });
        function adjustBodySize() {
            var wh = $(window).height();
            var fh = $('body > footer').outerHeight(true);
            var hh = $('body > header').height();
            // Reset heights
            $('body').height('auto');
            $('body > article').height('auto');
            $('body > article > header').css({ lineHeight: 'normal' });
            $('body > article > section').css({ lineHeight: 'normal' });
            var bh = $('body').height();
            var ah = $('body > article').height();
            var bh = (wh - fh) > bh ? (wh - fh) : bh;
            var ah = (wh - hh - fh) > ah ? (wh - hh - fh) : ah;
            var af = $('article > footer').outerHeight(true);
            af = af > 0 ? af : 0;
            $('body').height(bh);
            $('body > article').height(ah);
            $('body > article > header').css({ lineHeight: (Math.round(20 * ah / 100)) + 'px' });
            $('body > article > header').css({ lineHeight: (Math.round(20 * ah / 100)) + 'px' });
            $('body > article > section').css({ lineHeight: (Math.round(80 * ah / 100) - af) + 'px' });
        }
    };
    app.controller("MainCtrl", ['$scope', 'authService', '$location', controller]);
})(angular.module('durschtApp'));
//# sourceMappingURL=Main.js.map