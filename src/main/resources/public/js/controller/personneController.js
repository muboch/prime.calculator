'use strict';

primeApp.controller('perController', [ '$scope', 'allPersonels',
		function($scope, allPersonels) {
			// Instantiate an object to store your scope data in (Best Practices)
			//$scope.data = {};

			allPersonels.query(function(response) {
				// Assign the response INSIDE the callback
				$scope.personnes = response;
			});
		} ]);

