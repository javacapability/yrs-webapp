(function () {

    angular.module('questionModule')
            .controller('questionController', [
                '$scope',
                '$state',
                'questionServices',
                questionController
            ]);
            
    function questionController($scope, $state, questionServices) {
        var questions = this;
        
        questions.questionList = [];
        
        questionServices.getQuestions()
            .then(function (data) {
                questions.questionList = data;
            });
            
        questions.newQuestion = function(){
            console.log('New Question');
            var params = {};
            params.editMode = 'new';
            $state.go('main.question_edit', params);
        };
        
        questions.editQuestion = function(question){
            console.log('Edit question ' + question);
            var params = {};
            params.editMode = 'edit';
            params.id = question;
            $state.go('main.question_edit', params);
        };
        
        questions.deleteQuestion = function(question){
            questionServices.deleteQuestion(question)
                .then(function () {
                });
            $state.go('main.question_main');
        };
        
        questions.getAnswerTypes = function(answerTypes){
            switch (answerTypes) {
                case '1':
                    return "Multiple Choice";
                    break;
                case '2':
                    return "Yes or No";
                    break;
                case '3':
                    return "True or False";
                    break;
                case '4':
                    return "Free Text";
                    break;
            }
        };
    }
    
}());