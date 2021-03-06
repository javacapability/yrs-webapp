(function () {

    angular.module('assessmentModule')
        .factory('assessmentServices', assessmentServices);

    function assessmentServices($resource, $q, webServices, $http)
    {
        var serviceURL = webServices.serviceHost;
        
        var service = {
            getAssessmentList: getAssessmentList,
            getAssessment: getAssessment
        };

        return service;

        function getAssessmentList(listType, params) {
            var search = {};
            search.listType = listType;
            search.filter = '';
            var resource = $resource(serviceURL + webServices.assessmentListEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'userId': params.userId,
                        'tokenId': params.tokenid
                    },
                    interceptor : {
                        responseError: function(response) {}
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

        function getAssessment(id, params) {
            var search = {};
            search.id = id;
            var resource = $resource(serviceURL + webServices.assessmentGetEndpoint, {}, {
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
    }

})();