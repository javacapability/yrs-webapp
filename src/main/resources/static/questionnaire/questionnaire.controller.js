(function () {

    angular.module('questionModule')
            .controller('questionController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                '$mdToast',
                'questionServices',
                questionController
            ]);
            
    function questionController($scope, $state, $stateParams, $timeout, $mdDialog, $mdToast, questionServices) {
        var questions = this;
        
        questions.questionList = [];
        questions.lastNo = 1;

        if ($stateParams.status !== ''){
            var msg = '';
            switch ($stateParams.status) {
                case '0':
                    msg = 'Question saved successfully'; break;
                case '1':
                    msg = 'Question updated successfully'; break;
                case '2':
                    msg = 'Question deleted successfully'; break;
            }
            $mdToast.show($mdToast.simple()
                .textContent(msg)
                .position('top right' )
                .parent('#mainBody')
                .hideDelay(4000)
            );
            $stateParams.status = '';
        }

        questionServices.getQuestions($stateParams)
            .then(function (data) {
                questions.questionList = data;
                questions.lastNo = questions.questionList.length + 1;
            }, function (error) {
                $mdToast.show($mdToast.simple()
                    .textContent('Error retrieving questions')
                    .position('top right' )
                    .parent('#mainBody')
                    .hideDelay(4000)
                );
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
                            $stateParams.status = '2';
                            $state.go('main.question_main',$stateParams,{reload : 'main.question_main'});
                        }, 200);
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Error deleting question')
                            .position('top right' )
                            .parent('#mainBody')
                            .hideDelay(4000)
                        );
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