(function () {

    angular.module('mainModule')
        .factory('mainUserServices', mainUserServices);
    
    function mainUserServices($resource, $q, webServices)
    {
        var serviceURL = webServices.serviceHost;

        var service = {
            changePassword: changePassword,
            logout: logout
        };

        return service;

        function changePassword(user, params){
            var resource = $resource(serviceURL + webServices.resetEndpoint, {}, {
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
            return resource.save(user).$promise;
        }

        function logout(params){
            var resource = $resource(serviceURL + webServices.logoutEndpoint, {}, {
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
            return resource.save().$promise;
        }
    }

})();