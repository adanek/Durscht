/*global describe, it */
'use strict';

(function() {

	describe('Unit: ShareChooseBarController', function() {

		var ctrl, scope;

		// Load the module
		beforeEach(module('durscht-core'));

		// Initialize variables
		beforeEach(inject(function($controller, $rootScope) {

			scope = $rootScope.$new();

			ctrl = $controller('ShareChooseBarController', {
				$scope : scope
			});

		}));

		it('suite is running correct', function() {
			expect(true).toBe(true);
		});

		it('should have caption equal to "Wo gesch denn um?"', function() {

			expect(ctrl.caption).toEqual("Wo gesch denn um?");
		});

		it("should have a property bars", function() {
			expect(ctrl.bars).toBeDefined();
		});
	});
})();