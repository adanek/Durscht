'use strict';

/**
 * @ngdoc function
 * @name durschtApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the durschtApp
 */
angular.module('durschtApp')
  .controller('MainCtrl', ['$scope', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }]);
