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

    it('should have a function create', function () {
        expect(scope.create).toBeDefined();
    });

    it('should have a property name', function () {
        expect(scope.name).toBeDefined();
    });

    it('should have a property website', function () {
        expect(scope.website).toBeDefined();
    });

    it('should have a property remark', function () {
        expect(scope.remark).toBeDefined();
    });
});
