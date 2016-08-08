(function () {

    angular.module('questionModule')
            .controller('questionController', [
                '$scope',
                '$state',
                '$stateParams',
                'questionServices',
                questionController
            ]);
            
    function questionController($scope, $state, $stateParams, questionServices) {
        var questions = this;
        
        questions.questionList = [];
        questions.lastNo = 1;
        
        questionServices.getQuestions($stateParams)
            .then(function (data) {
                questions.questionList = data;
                questions.lastNo = questions.questionList.length + 1;
            });
            
        questions.newQuestion = function(){
            console.log('New Question');
            var params = $stateParams;
            params.editMode = 'new';
            params.priority = questions.lastNo;
            $state.go('main.question_edit', params);
        };
        
        questions.editQuestion = function(question){
            console.log('Edit question ' + question);
            var params = $stateParams;
            params.editMode = 'edit';
            params.id = question;
            $state.go('main.question_edit', params);
        };
        
        questions.deleteQuestion = function(question){
            questionServices.deleteQuestion(question, $stateParams)
                .then(function () {
                });

            for (i = 0; i < questions.questionList.length; i++){
                if (questionList[i].id === question.id){
                    questionList.splice(i,1);
                    questions.lastNo = questions.questionList.length + 1;
                    break;
                }
            }
        };
        
        questions.getAnswerTypes = function(answerTypes){
            switch (answerTypes) {
                case 1:
                    return "Multiple Choice";
                    break;
                case 2:
                    return "Yes or No";
                    break;
                case 3:
                    return "True or False";
                    break;
                case 4:
                    return "Free Text";
                    break;
            }
        };
    }
    
}());