(function () {

    angular.module('questionModule')
        .factory('questionServices', questionServices);

    function questionServices($resource, $q, webServices)
    {
        var serviceURL = webServices.serviceHost;
        
        var service = {
            getQuestions: getQuestions,
            saveQuestion: saveQuestion,
            getEditQuestion: getEditQuestion,
            updateQuestion: updateQuestion,
            deleteQuestion: deleteQuestion
        };

        return service;

        function getQuestions(params) {
            var resource = $resource(serviceURL + webServices.questionListEndpoint, {}, {
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
        
        function saveQuestion(newQuestion, params){
            newQuestion = updateAnswerTypeForUpdate(newQuestion);
            var resource = $resource(serviceURL + webServices.questionSaveEndpoint, {}, {
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
            return resource.save(newQuestion).$promise;
        }

        function getEditQuestion(questionId, params) {
            var search = {};
            search.id = questionId;
            var resource = $resource(serviceURL + webServices.questionEndpoint, {}, {
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
        
        function updateQuestion(updateQuestion, params){
            updateQuestion = updateAnswerTypeForUpdate(updateQuestion);
            var resource = $resource(serviceURL + webServices.questionEditEndpoint, {}, {
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
            return resource.save(updateQuestion).$promise;
        }

        function updateAnswerTypeForUpdate(updateQuestion){
            var answerId = updateQuestion.answerTypes.id;
            updateQuestion.answerTypes = serviceURL + '/answertypes/' + answerId;
            updateQuestion.isActive = 1;
            return updateQuestion;
        }
        
        function deleteQuestion(questionIdDelete, params){
            var resource = $resource(serviceURL + webServices.questionDeleteEndpoint, {}, {
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
            var question = {id : questionIdDelete}
            return resource.save(question).$promise;
        }
    }

})();