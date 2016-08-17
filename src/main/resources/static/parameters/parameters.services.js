(function () {

    angular.module('parametersModule')
        .factory('parametersServices', parametersServices);
    
    //$q is temporary to test json queries
    function parametersServices($resource, $q, webServices, $http)
    {
        var serviceURL = webServices.serviceHost;
        
        var service = {
            getParameters: getParameters,
            updateParameters: updateParameters
        };

        return service;

        function getParameters(params) {
            var resource = $resource(serviceURL + webServices.parametersListEndpoint, {}, {
                query: {
                    method: 'GET',
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
            return resource.query().$promise;
        }

        function updateParameters(sysParameters, params){
            var updateParams = {paramList:[]};
            updateParams.paramList = sysParameters;
            console.log(serviceURL + webServices.parametersUpdateEndpoint);
            var resource = $resource(serviceURL + webServices.parametersUpdateEndpoint, {}, {
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
            return resource.save(updateParams).$promise;
        }
    }

})();