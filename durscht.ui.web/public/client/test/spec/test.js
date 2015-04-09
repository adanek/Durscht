/*global describe, it */
'use strict';

(function() {

	describe('Unit: MainController', function() {

		beforeEach(module('myApp'));

		var ctrl, scope;

		beforeEach(inject(function($controller, $rootScope) {

			scope = $rootScope.$new();

			ctrl = $controller('MainController', {
				$scope : scope
			});
		}));

		it('should work', function() {
			expect(true).toBe(true);
		});

		it('should create $scope.greeting when calling sayHello', function() {
			expect(scope.greeting).toBeUndefined();
			scope.sayHello();
			expect(scope.greeting).toEqual("Hello Ari");
		});

	});
})();