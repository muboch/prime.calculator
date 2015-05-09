primeApp.controller('perController', function($scope, $http) {
    $http.get("http://localhost:8080/rest/prime/users")
    .success(function (response) {
            $scope.users = response;
        });
});