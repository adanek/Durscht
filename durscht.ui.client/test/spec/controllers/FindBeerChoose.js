'use strict';

describe('Controller: FindchoosebeersCtrl', function () {

  // load the controller's module
  beforeEach(module('durschtApp'));

  var FindBeerChooseCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FindBeerChooseCtrl = $controller('FindBeerChooseCtrl', {
      $scope: scope
    });
  }));

  it('should have a caption', function(){
    expect(scope.caption).toBeDefined();
  });


});
