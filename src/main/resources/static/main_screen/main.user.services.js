(function () {

    angular.module('mainModule')
        .factory('mainUserServices', mainUserServices);
    
    function mainUserServices($resource, $q)
    {
        var service = {
            getCurrentUser: getCurrentUser
        };

        return service;

        function getCurrentUser(user) {
            var resource = $resource('_test/testUser.json');
            var result = resource.query().$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                console.log(data[0].fullName);
                return deferred.resolve(data[0]);
                
            });
            return deferred.promise;
        }
    }

})();