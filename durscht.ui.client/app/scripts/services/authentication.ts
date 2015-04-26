/// <reference path="../_references.ts"/>

(function(app){

    var srv = function($http){

        this.login = function(username:string, passwd:string){
            $http.post()
        }
    };
    
    app.factory('authentication', ['$http', srv]);

})(angular.module('durschtApp'));
