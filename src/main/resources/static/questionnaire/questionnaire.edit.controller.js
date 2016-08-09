(function () {

    angular.module('questionModule')
            .controller('questionEditController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                'questionServices',
                questionEditController
            ]);
            
    function questionEditController($scope, $state, $stateParams, $timeout, $mdDialog, questionServices) {
        var questions = this;
        
        questions.answerTypes = [
            {value: 1, name: 'Multiple Choice'},
            {value: 2, name: 'Yes or No'},
            {value: 3, name: 'True or False'},
            {value: 4, name: 'Freetext'}];

        var defaultQuestion = {
            'priorityNumber':$stateParams.priority,
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
            questionServices.getQuestions($stateParams)
                .then(function (data) {
                    questions.questionList = data;
                    questions.lastNo = questions.questionList.length + 1;
                });
            $timeout(function () {
                $state.go('main.question_main',$stateParams);
            }, 200);

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
            questions.back();
        };

        questions.delete = function(){
            var confirm = $mdDialog.confirm()
                .title('Warning')
                .textContent('Are you sure you want to delete the Question?')
                .ariaLabel('Are you sure you want to delete the Question?')
                .ok('Yes')
                .cancel('No');
            $mdDialog.show(confirm).then(function() {
                questionServices.deleteQuestion(questions.editQuestion.id, $stateParams)
                    .then(function () {
                        questions.back();
                    });
            }, function() {
            });
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