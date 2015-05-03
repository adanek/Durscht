/// <reference path="../_references.ts"/>
(function (app) {
    var controller = function ($scope, $location, posting) {
        $scope.caption = "Und wie hat's dir gemundet?";
        $scope.remark = "";
        $scope.price = 0.0;
        $scope.rating = 0;
        $scope.sentPost = function () {
            posting.remark = $scope.remark;
            posting.price = $scope.price;
            posting.rating = $scope.rating;
            posting.save().success(function () {
                posting.reset();
                $location.path("/").replace();
            }).error(function () {
                $scope.error = "Das hat nicht geklappt!";
                $scope.$apply();
            });
        };
    };
    app.controller("ShareDetailsCtrl", ['$scope', '$location', 'posting', controller]);
})(angular.module('durschtApp'));
//# sourceMappingURL=ShareDetails.js.map