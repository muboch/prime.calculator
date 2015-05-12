primeApp.controller('perController', function($scope, $http) {
    $http.get("http://localhost:8080/rest/prime/users")
    .success(function (response) {
            $scope.users = response;
        });
});

//create the controller and inject Angular's $scope
primeApp.controller('mainController', function($scope) {
    // create a message to display in our view
});


primeApp.controller('regleController', function($scope) {
	
});

prime.controller('navigation', function($rootScope, $scope, $http, $location, $route) {

			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.name + ":"
									+ credentials.pw)
				} : {};

				$http.get('http://localhost:8080/user', {
					headers : headers
				}).success(function(data) {
					if (data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback($rootScope.authenticated);
				}).error(function() {
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			}

			authenticate();

			$scope.credentials = {};
			$scope.login = function() {
				authenticate($scope.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						$location.path("/");
						$scope.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed")
						$location.path("/login");
						$scope.error = true;
						$rootScope.authenticated = false;
					}
				})
			};

			$scope.logout = function() {
				$http.post('logout', {}).success(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				}).error(function(data) {
					console.log("Logout failed")
					$rootScope.authenticated = false;
				});
			}

		});