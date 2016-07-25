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
        
        questions.answerTypes = [
            {value: 1, name: 'Multiple Choice'},
            {value: 2, name: 'Yes or No'},
            {value: 3, name: 'True or False'},
            {value: 4, name: 'Freetext'}];

        var defaultQuestion = {
            'priorityNumber':null,
            'questionTxt':'',
            'answerTypes':{'id':1},
            'yesWeight':0,
            'noWeight':0,
            'trueWeight':0,
            'falseWeight':0,
            'isActive':1,
            'answers':[]};

        console.log('test - ' + $stateParams.editMode);
        questions.editMode = $stateParams.editMode;
        
        questions.editQuestion = defaultQuestion;
        
        if (questions.editMode === 'edit'){
            var questionId = $stateParams.id;
            questions.editModeTitle = 'Edit';
            questionServices.getEditQuestion(questionId, $stateParams)
                .then(function (data) {
                    questions.editQuestion = data;
                });
        } else {
            questions.editModeTitle = 'Create new';
        }
        
        questions.back = function(){
            $state.go('main.question_main',$stateParams);
        };
        
        questions.reset = function(){
            questions.editQuestion = defaultQuestion;
        };
        
        questions.save = function(){
            questionServices.saveQuestion(questions.editQuestion, $stateParams)
                .then(function () {
                });
            questions.back();
        };
        
        questions.update = function(){
            questionServices.updateQuestion(questions.editQuestion, $stateParams)
                .then(function () {
                });
        };

        questions.delete = function(){
            questionServices.deleteQuestion(questions.editQuestion.id, $stateParams)
                .then(function () {
                });
            questions.back();
        };


        questions.addNewAnswer = function(){
            var answers = questions.editQuestion.answers;
            var newAnswer = {};
            newAnswer.answerNumber = answers.length + 1;
            newAnswer.answerTxt = '';
            newAnswer.weight = 0;
            questions.editQuestion.answers.push(newAnswer);
        }

        questions.delAnswer = function(){
            questions.editQuestion.answers
                .splice(questions.editQuestion.answers.length - 1 ,1);
        }
    }   
    
}());