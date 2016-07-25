(function () {

    angular.module('loginModule')
        .factory('loginServices', loginServices);
    
    //$q is temporary to test json queries
    function loginServices($resource, $q, webServices)
    {   
        var serviceURL = webServices.serviceHost + webServices.loginServicePath;
        
        var service = {
            login: login
        };

        return service;

//        function getCurrentUser(user) {
//            var resource = $resource('/login');
//            var result = resource.query().$promise;
//            var deferred = $q.defer();
//            result.then(function (data) {
//                console.log(data[0].username);
//                return deferred.resolve(data[0]);
//                
//            });
//            return deferred.promise;
//        }
        
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
                    interceptor : {responseError : resourceErrorHandler,
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
        
        function resourceErrorHandler(response) {
            console.log(response);
            console.log('Error logging-in');
        }
    }

})();