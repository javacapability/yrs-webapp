(function () {

    angular.module('mainModule')
        .factory('mainUserServices', mainUserServices);
    
    function mainUserServices($resource, $q, webServices)
    {
        var serviceURL = webServices.serviceHost;

        var service = {
            changePassword: changePassword,
            logout: logout,
            validatelogin: validatelogin,
            getParams: getParams
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

        function validatelogin(user, pswd) {
            console.log(serviceURL + webServices.loginEndpoint);
            var resource = $resource(serviceURL + webServices.loginEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'userId':'',
                        'tokenId':''
                    },
                    interceptor : {
                        //responseError : resourceErrorHandler,
                        response: function(response) {
                            console.log (response.headers());
                            response.resource.$httpHeaders = response.headers();
                            return response.resource;
                        }
                    }
                }
            });
            return resource.save({userId: user, pswd: pswd}).$promise;
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

        function getParams(params){
            var resource = $resource(serviceURL + webServices.parametersListEndpoint, {}, {
                query: {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
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
    }

})();