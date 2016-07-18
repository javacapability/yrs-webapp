(function () {

    angular.module('questionModule')
        .factory('questionServices', questionServices);
    
    //$q is temporary to test json queries
    function questionServices($resource, $q, webServices)
    {
        var serviceURL = webServices.serviceHost + webServices.questionServicePath;
        
        var service = {
            getQuestions: getQuestions,
            saveQuestion: saveQuestion,
            getEditQuestion: getEditQuestion,
            updateQuestion: updateQuestion,
            deleteQuestion: deleteQuestion
        };

        return service;

        function getQuestions() {
            var resource = $resource(serviceURL + webServices.questionListEndpoint);
            var result = resource.query().$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                return deferred.resolve(data);
                
            });
            return deferred.promise;
        }
        
        function saveQuestion(newQuestion){
            var resource = $resource(serviceURL + webServices.questionSaveEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            });
            return resource.save(newQuestion).$promise;
        }
        
        function getEditQuestion(questionId){
            var resource = $resource(serviceURL + webServices.questionListEndpoint);
            var result = resource.query().$promise;
            var deferred = $q.defer();
            result.then(function (data) {
                console.log('For editing - ' + questionId)
                data = _.filter(data, { 'id': questionId });
                return deferred.resolve(data[0]);
            });
            return deferred.promise;
        }
        
        function updateQuestion(updateQuestion){
            var resource = $resource(serviceURL + webServices.questionEditEndpoint, {}, {
                save: {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            });
            return resource.save(updateQuestion).$promise;
        }
        
        function deleteQuestion(questionIdDelete){
            var resource = $resource(serviceURL + webServices.questionDeleteEndpoint, {}, {
                delete: {
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            });
            var question = {questionId : questionIdDelete}
            return resource.delete(question).$promise;
        }
    }

})();