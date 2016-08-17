(function () {

    angular.module('questionModule')
            .controller('questionEditController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                '$mdToast',
                'questionServices',
                questionEditController
            ]);
            
    function questionEditController($scope, $state, $stateParams, $timeout, $mdDialog, $mdToast, questionServices) {
        var questions = this;

        questions.allowothers = false;
        questions.others = {
            'answerNumber':1,
            'answerTxt':'Others',
            'weight':0
        };

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
                    if (questions.editQuestion.answers
                        && questions.editQuestion.answers.length > 0){
                        var ansLen = questions.editQuestion.answers.length;
                        for (var i = 0; i < ansLen; i++){
                            if (questions.editQuestion.answers[i].answerTxt === 'Others'){
                                questions.others = questions.editQuestion.answers[i];
                                questions.allowothers = true;
                                questions.editQuestion.answers.splice(i, 1);
                                break;
                            }
                        }
                        console.log(questions.editQuestion.answers);
                    }
                }, function (error) {
                    $mdToast.show($mdToast.simple()
                        .textContent('Error retrieving question')
                        .position('top right' )
                        .parent('#mainBody')
                        .hideDelay(4000)
                    );
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
            questions.allowothers = false;
        };

        questions.updateOtherAnswer = function(){
            if (questions.allowothers === true){
                questions.others.answerNumber = questions.editQuestion.answers.length + 1;
                questions.editQuestion.answers.push(questions.others);
            }
            console.log(questions.editQuestion.answers);
        };
        
        questions.save = function(){
            questions.updateOtherAnswer();
            questionServices.saveQuestion(questions.editQuestion, $stateParams)
                .then(function () {
                    $stateParams.status = '0';
                    questions.back();
                }, function (error) {
                    $mdToast.show($mdToast.simple()
                        .textContent('Failed saving the question')
                        .position('top right' )
                        .parent('#mainBody')
                        .hideDelay(4000)
                    );
                });

        };

        questions.update = function(){
            questions.updateOtherAnswer();
            questionServices.updateQuestion(questions.editQuestion, $stateParams)
                .then(function () {
                    $stateParams.status = '1';
                    questions.back();
                }, function (error) {
                    $mdToast.show($mdToast.simple()
                        .textContent('Failed updating the question')
                        .position('top right' )
                        .parent('#mainBody')
                        .hideDelay(4000)
                    );
                });

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
                        $stateParams.status = '2';
                        questions.back();
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Failed deleting the question')
                            .position('top right' )
                            .parent('#mainBody')
                            .hideDelay(4000)
                        );
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
            console.log(questions.editQuestion.answers);
        }

        questions.delAnswer = function(){
            questions.editQuestion.answers
                .splice(questions.editQuestion.answers.length - 1 ,1);
            console.log(questions.editQuestion.answers);
        }
    }   
    
}());