(function () {

    angular.module('userModule')
        .factory('userServices', userServices);

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
        
        function saveUser(newUser, params){
            var resource = $resource(serviceURL + webServices.userSaveEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
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
        
        function updateUser(updateUser, params){
            var resource = $resource(serviceURL + webServices.userEditEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
                    }
                }
            });
            return resource.save(updateUser).$promise;
        }
        
        function deleteUser(userIdDelete, params){
            var resource = $resource(serviceURL + webServices.userDeleteEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
                    }
                }
            });
            var user = {userId : userIdDelete};
            return resource.save(user).$promise;
        }

        function updateUserGroupForUpdate(updateUser){
            var groupID = updateUser.userGroup.id;
            updateUser.answerTypes = serviceURL + '/userGroup/' + groupID;
            return updateUser;
        }
    }

})();