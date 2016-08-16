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

        function getUsers(params) {
            var search = {};
            search.filter = '';
            var resource = $resource(serviceURL + webServices.userListEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
                    },
                    isArray: true
                }
            });
            var result = resource.save(search).$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                return deferred.resolve(data);
            });
            return deferred.promise;
        }
        
        function saveUser(newUser, params){
            newUser.birthday = String(moment(newUser.birthday).format('YYYY-MM-DD'));
            newUser.upDt = '';
            newUser.lastLogin = '';
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

        function getEditUser(userId, params){
            var search = {};
            search.id = userId;
            var resource = $resource(serviceURL + webServices.userEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
                    },
                    isArray: false
                }
            });
            var result = resource.save(search).$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                return deferred.resolve(data);
            });
            return deferred.promise;
        }

        function updateUser(updateUser, params){
            updateUser.birthday = String(moment(updateUser.birthday).format('YYYY-MM-DD'));
            updateUser.upDt = '';
            updateUser.lastLogin = '';
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