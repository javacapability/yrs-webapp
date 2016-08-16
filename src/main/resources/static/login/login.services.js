(function () {

    angular.module('loginModule')
        .factory('loginServices', loginServices);
    
    //$q is temporary to test json queries
    function loginServices($resource, $q, webServices)
    {   
        var serviceURL = webServices.serviceHost;
        
        var service = {
            login: login,
            reset: reset,
            resetNew: resetNew
        };

        return service;
        
        function login(user, pswd) {
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

        function reset(user, email, birthday) {
            console.log('TODO');
        }

        function resetNew(user, pswd) {
            console.log('TODO');
        }

        function resourceErrorHandler(response) {
            console.log(response);
            console.log('Error logging-in');
            return response;
        }
    }

})();