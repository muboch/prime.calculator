var primeApp = angular.module('primeApp', [ 'ngResource', 'ngRoute' ]);

primeApp.config(function($routeProvider) {
	$routeProvider.
		// route for the home page
		when('/', {
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
    $scope.message = 'Everyone come and see how good I look!';
});

primeApp.controller('perController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});

primeApp.controller('regleController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});