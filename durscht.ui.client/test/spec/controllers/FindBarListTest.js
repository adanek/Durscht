'use strict';

describe('Controller: FindBarlListCtrl', function () {

  // load the controller's module
  beforeEach(module('durschtApp'));

  var FindBarListCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FindBarListCtrl = $controller('FindBarListCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
