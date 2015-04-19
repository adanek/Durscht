'use strict';

describe('Controller: SharechoosebarCtrl', function () {

  // load the controller's module
  beforeEach(module('durschtApp'));

  var SharechoosebarCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SharechoosebarCtrl = $controller('ShareChooseBarCtrl', {
      $scope: scope
    });
  }));

  it('should have a caption', function () {
    expect(scope.caption).toBeDefined();
  });
});
