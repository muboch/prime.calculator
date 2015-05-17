var primeApp = angular.module('primeApp', [ 'ngResource', 'ngRoute' ]);

primeApp.config(function($routeProvider, $httpProvider) {
	$routeProvider.
		// route for the home page
		when('/home', {
			templateUrl : 'home.html',
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
	.when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	})
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});


