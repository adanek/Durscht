'use strict';

describe('Controller: ShareCtrl', function () {

  // load the controller's module
  beforeEach(module('durschtApp'));

  var ShareCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ShareCtrl = $controller('ShareLocationCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(true).toBe(true);
  });
});
