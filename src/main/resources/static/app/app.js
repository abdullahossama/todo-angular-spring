'use strict';
var myApp = myApp || {};
 
(function(){
    myApp.factories = angular.module('myAppFactories', []),
    myApp.controllers = angular.module('myAppControllers', [ 'myAppFactories', 'ngRoute' ]),
    myApp.app = angular.module('myApp', [ 'ngRoute', 'myAppControllers']).config(function($routeProvider, $locationProvider, $httpProvider, $logProvider) {
		//Enable logs - debug level
		$logProvider.debugEnabled(true);

		//$httpProvider.interceptors.push('httpInterceptor');
		$httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
	    $httpProvider.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
    });
})();