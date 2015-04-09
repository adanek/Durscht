/*global describe, it */
'use strict';

(function() {

	describe('Unit: MainController', function() {

		beforeEach(function (){
			module('durscht-core');
		});
				

		var ctrl, scope;

		beforeEach(inject(function($controller, $rootScope) {

			
			scope = $rootScope.$new();

			ctrl = $controller('ShareChooseBarController', {
				$scope : scope
			});
		}));

		it('should work', function() {
			expect(true).toBe(true);
		});

		it('should create $scope.greeting when calling sayHello', function() {
			
			expect(ctrl.caption).toEqual("Wo gesch denn um?");
		});

	});
})();