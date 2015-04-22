'use strict';

describe('Controller: LoginCtrl', function () {

    // load the controller's module
    beforeEach(module('durschtApp'));

    var LoginCtrl,
        scope, authMock;

    beforeEach(function(){
        authMock = {
            login: function(username, passwd){

            }
        };

        spyOn(authMock, 'login');
    });

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope) {
        scope = $rootScope.$new();
        LoginCtrl = $controller('LoginCtrl', {
            $scope: scope,
            authentication: authMock
        });
    }));

    it('should have a caption', function () {
        expect(scope.caption).toBeDefined();
    });

    it('should have a username', function(){
        expect(scope.username).toBeDefined();
    });

    it('should have a password', function(){
        expect(scope.password).toBeDefined();
    });

    it('should have a method login', function(){
        expect(scope.login).toBeDefined();
    });

    it('should call the authentication service on login', function(){

        scope.username = 'user1';
        scope.password = 'top secret';
        scope.login();

        expect(authMock.login).toHaveBeenCalledWith(scope.username, scope.password);
    });
});
