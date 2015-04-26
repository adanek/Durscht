'use strict';

/**
 * @ngdoc function
 * @name durschtApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the durschtApp
 */
angular.module('durschtApp')
  .controller('AboutCtrl', ['$scope', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }]);
