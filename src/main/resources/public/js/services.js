primeApp.factory('allPersonels', function($resource){
        return $resource('/rest/prime/personnes',{})
    })
    .value('version','0.1');