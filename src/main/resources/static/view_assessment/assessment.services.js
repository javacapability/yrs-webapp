(function () {

    angular.module('assessmentModule')
        .factory('assessmentServices', assessmentServices);
    
    //$q is temporary to test json queries
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
    }

})();