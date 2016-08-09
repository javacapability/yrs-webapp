(function () {

    angular.module('questionModule')
            .controller('questionController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                'questionServices',
                questionController
            ]);
            
    function questionController($scope, $state, $stateParams, $timeout, $mdDialog, questionServices) {
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
            var confirm = $mdDialog.confirm()
                .title('Warning')
                .textContent('Are you sure you want to delete the Question?')
                .ariaLabel('Are you sure you want to delete the Question?')
                .ok('Yes')
                .cancel('No');
            $mdDialog.show(confirm).then(function() {
                questionServices.deleteQuestion(question, $stateParams)
                    .then(function () {
                        $timeout(function () {
                            $state.reload('main.question_main');
                        }, 200);
                    });
            }, function() {
            });

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