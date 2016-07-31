(function () {

    angular.module('userModule')
        .factory('userServices', userServices);
    
    //$q is temporary to test json queries
    function userServices($resource, $q, webServices)
    {
        var serviceURL = webServices.serviceHost;
        
        var service = {
            getUsers: getUsers,
            saveUser: saveUser,
            getEditUser: getEditUser,
            updateUser: updateUser,
            deleteUser: deleteUser
        };

        return service;

        function getUsers() {
            var resource = $resource(serviceURL + webServices.userListEndpoint, {}, {
                query: {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json'
                    },
                    isArray: true
                }
            });
            var result = resource.query().$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                return deferred.resolve(data);
            });
            return deferred.promise;
        }
        
        function saveUser(newUser){
            var resource = $resource(serviceURL + webServices.userSaveEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            });
            return resource.save(newUser).$promise;
        }
        
        function getEditUser(userId){
            var resource = $resource(serviceURL + webServices.userListEndpoint, {}, {
                query: {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json'
                    },
                    isArray: true
                }
            });
            var result = resource.query().$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                console.log('For editing - ' + userId)
                data = _.filter(data, { 'userId': userId });
                return deferred.resolve(data[0]);
            });
            return deferred.promise;
        }
        
        function updateUser(updateUser){
            var resource = $resource(serviceURL + webServices.userEditEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            });
            return resource.save(updateUser).$promise;
        }
        
        function deleteUser(userIdDelete){
            var resource = $resource(serviceURL + webServices.userDeleteEndpoint, {}, {
                delete: {
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            });
            var user = {userId : userIdDelete}
            return resource.delete(user).$promise;
        }
    }

})();