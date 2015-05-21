'use strict';

describe('Controller: ShareBarCreateCtrl', function () {

    // load the controller's module
    beforeEach(module('durschtApp'));

    var ShareBarCreateCtrl,
        scope;

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope) {
        scope = $rootScope.$new();
        ShareBarCreateCtrl = $controller('ShareBarCreateCtrl', {
            $scope: scope
        });
    }));

    it('should have a caption', function () {
        expect(scope.caption).toBeDefined();
    });

});
