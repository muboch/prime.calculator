'use strict';

primeApp.controller('loginController', function($rootScope, $scope, $http, $location, $route, $q) {
 
	$scope.credentials = {};
	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	$scope.login = function(credentials) {
		var deferred = $q.defer();
		var loginUrl = 'login';
		$http(
				{
					method : 'POST',
					url : loginUrl,
					headers : {
						'content-type' : 'application/x-www-form-urlencoded'
					},
					transformRequest : function(obj) {
						var str = [];
						for ( var p in obj) {
							if (obj.hasOwnProperty(p)) {
								str.push(encodeURIComponent(p) + '='
										+ encodeURIComponent(obj[p]));
							}
						}
						return str.join('&');
					},
					data : {
						username : credentials.username,
						password : credentials.password
					}
				}).success(function() {
			authenticate(deferred);
		}).error(function() {
			deferred.reject();
		});
//		deferred.resolve();
		return deferred.promise;
	};
	
	var authenticate = function (deferred) {
        var userUrl = 'users/current';
        $http.get(userUrl).success(function (data) {
            // console.info('getUser success');
            if (data.name) {
            	$location.path("/home");
            	$scope.error = false;
                // console.info('getUser contains name');
                // TODO hier koennte auch der komplete user und die rollen gespeichert werden falls man das will
                $rootScope.authenticated = true;
                deferred.resolve();
            }
            else {
            	$scope.error = true;
            	$location.path("/login");
                // console.info('getUser wrong data');
                $rootScope.authenticated = false;
                deferred.reject();
            }
        }).error(function () {
        	$scope.error = true;
        	$location.path("/login");
            // console.info('getUser failed');
            $rootScope.authenticated = false;
            deferred.reject();
        });
    };

	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$rootScope.authenticated = false;
			$location.path("/login");
		}).error(function(data) {
			console.log("Logout failed")
			$rootScope.authenticated = false;
		});
	}

});