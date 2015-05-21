'use strict';

describe('Controller: ShareDetailsCtrl', function () {

    // load the controller's module
    beforeEach(module('durschtApp'));

    var ShareCtrl, scope;

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope) {
        scope = $rootScope.$new();
        ShareCtrl = $controller('ShareDetailsCtrl', {
            $scope: scope
        });
    }));

    it('should have a caption', function () {
        expect(scope.caption).toBeDefined();
    });

    it('should have a remark field', function(){
        expect(scope.remark).toBeDefined();
    });

    it('should have a price field', function(){
        expect(scope.price).toBeDefined();
    });

    it('should have a rating field', function(){
        expect(scope.rating).toBeDefined();
    });

    it('should have a function to sent the post', function(){
        expect(scope.sentPost).toBeDefined();
    });
});
