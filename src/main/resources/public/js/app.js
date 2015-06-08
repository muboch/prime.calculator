var primeApp = angular.module('primeApp', [ 'ngResource', 'ngRoute','ngCookies' ]);

primeApp.config(function($routeProvider, $httpProvider) {
	$routeProvider.
		// route for the home page
		when('/login', {
			templateUrl : 'views/login.html',
			controller : 'loginController'
	})
		// route for the home page
		.when('/home', {
			templateUrl : 'views/home.html',
			controller : 'mainController'
		})
		//route for personnel page
		.when('/personnel', {
			templateUrl : 'views/personnel.html',
			controller : 'perController'
	})
		//route for regle page
		.when('/regle', {
		templateUrl : 'views/regle.html',
		controller : 'regleController'
	})
		// Login site
	.otherwise({ redirectTo: '/login' });
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});

