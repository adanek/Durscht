/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function ($scope, authService, $location) {
        $scope.authBtnText = "";
        setAuthBtnText(authService.isAuthenticated);
        function setAuthBtnText(authenticated) {
            if (authenticated == true) {
                $scope.logedIn = true;
                $scope.authBtnText = "Logout";
            }
            else {
                $scope.logedIn = false;
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
            //console.info("adjust body height");
            setTimeout(adjustBodySize, 100);
        });
        function adjustBodySize() {
            var wh = $(window).height();
            var ph = $('body > header').height();
            var pf = $('body > footer').outerHeight(true);
            var bh; //body height
            // Reset all heights
            $('body').height('auto');
            $('body > article').height('auto');
            bh = $('body').height();
            if (bh < wh) {
                bh = wh - pf;
                $('body').height(bh);
            }
            // Set the article header height
            $('body > article > header').css({ lineHeight: (Math.round(20 * wh / 100)) + 'px' });
            // Calculate the heights of the article parts
            var ahh = $('body > article > header').outerHeight(true); // height of the article header
            var ash = $('body > article > section').outerHeight(true); // height of the article section
            var afh = $('article > footer').outerHeight(true); // height of the article section
            var contentHeight = "";
            // set the bootom margin for the article
            $('body > article').css('margin-bottom', afh);
            var ah = ahh + ash;
            var ahMin = bh - ph;
            $('body > article').height(ahMin > ah ? ahMin : ah);
            console.info('vals: ' + ahh + ' ' + ash + ' ' + afh + ' ' + ah);
        }
    };
    app.controller("MainCtrl", ['$scope', 'authService', '$location', controller]);
})(angular.module('durschtApp'));
//# sourceMappingURL=Main.js.map