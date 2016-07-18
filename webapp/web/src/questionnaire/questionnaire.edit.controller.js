(function () {

    angular.module('questionModule')
            .controller('questionEditController', [
                '$scope',
                '$state',
                '$stateParams',
                'questionServices',
                questionEditController
            ]);
            
    function questionEditController($scope, $state, $stateParams, questionServices) {
        var questions = this;
        
        questions.answerTypes = [{value: 1, name: 'Multiple Choice'},
            {value: 2, name: 'Yes or No'},
            {value: 3, name: 'True or False'},
            {value: 4, name: 'Freetext'}];
        
        console.log('test - ' + $stateParams.editMode);
        questions.editMode = $stateParams.editMode;
        
        questions.editQuestion = {};
        
        if (questions.editMode === 'edit'){
            var questionId = $stateParams.id;
            questions.editModeTitle = 'Edit';
            questionServices.getEditQuestion(questionId)
                .then(function (data) {
                    questions.editQuestion = data;
                });
        } else {
            questions.editModeTitle = 'Create new';
        }
        
        questions.back = function(){
            $state.go('main.question_main');
        };
        
        questions.reset = function(){
            questions.editQuestion = {};
        };
        
        questions.save = function(){
            questionServices.saveQuestion(questions.editQuestion)
                .then(function () {
                });
            questions.back();
        };
        
        questions.update = function(){
            questionServices.updateQuestion(questions.editQuestion)
                .then(function () {
                });
        };
        
        questions.delete = function(){
            questionServices.deleteQuestion(questions.editQuestion.questionId)
                .then(function () {
                });
            questions.back();
        };
        
    }   
    
}());