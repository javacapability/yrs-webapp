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
                    isArray: true,
                    interceptor : {
                        response: function(response) {
                            return response.resource;
                        }
                    }
                }
            });
            return resource.save(search).$promise;
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
                    },
                    interceptor : {
                        response: function(response) {
                            return response.resource;
                        }
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
                    isArray: false,
                    interceptor : {
                        response: function(response) {
                            return response.resource;
                        }
                    }
                }
            });
            return resource.save(search).$promise;
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
                    },
                    interceptor : {
                        response: function(response) {
                            return response.resource;
                        }
                    }
                }
            });
            return resource.save(updateUser).$promise;
        }
        
        function deleteUser(userIdDelete, params){
            var user = {userId : userIdDelete};
            var resource = $resource(serviceURL + webServices.userDeleteEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
                    },
                    interceptor : {
                        response: function(response) {
                            return response.resource;
                        }
                    }
                }
            });
            return resource.save(user).$promise;
        }

        function updateUserGroupForUpdate(updateUser){
            var groupID = updateUser.userGroup.id;
            updateUser.answerTypes = serviceURL + '/userGroup/' + groupID;
            return updateUser;
        }
    }

})();