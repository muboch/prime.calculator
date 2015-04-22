var primeApp = angular.module('primeApp', [ 'ngResource', 'ngRoute' ]);

primeApp.config(function($routeProvider) {
	$routeProvider.
		// route for the home page
		when('/home', {
			templateUrl : 'home.html',
			controller : 'mainController'
	})
		//route for personnel page
		.when('/personnel', {
			templateUrl : 'personnel.html',
			controller : 'perController'
	})
		//route for regle page
		.when('/regle', {
		templateUrl : 'regle.html',
		controller : 'regleController'
	})
});

// create the controller and inject Angular's $scope
primeApp.controller('mainController', function($scope) {
    // create a message to display in our view
});

primeApp.controller('perController', function($scope) {

});

primeApp.controller('regleController', function($scope) {
	
});