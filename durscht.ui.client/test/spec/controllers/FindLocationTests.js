'use strict';

describe('Controller: FindLocationCtrl', function () {

    // load the controller's module
    beforeEach(module('durschtApp'));

    var FindLocationCtrl,
        scope;

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope) {
        scope = $rootScope.$new();
        FindLocationCtrl = $controller('FindLocationCtrl', {
            $scope: scope
        });
    }));

    it('should attach a list of awesomeThings to the scope', function () {
        expect(true).toBe(true);
    });
});
