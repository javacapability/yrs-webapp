(function () {

    angular.module('assessmentModule')
            .controller('assessmentViewController', [
                '$scope',
                '$state',
                '$stateParams',
                '$mdToast',
                'assessmentServices',
                assessmentViewController
            ]);
            
    function assessmentViewController($scope, $state, $stateParams, $mdToast, assessmentServices) {
        var assessments = this;

        assessments.assessmentView = {};

        assessmentServices.getAssessment($stateParams.id, $stateParams)
            .then(function (data) {
                assessments.assessmentView = data;
            }, function (error) {
                $mdToast.show($mdToast.simple()
                    .textContent('Failed retrieving the assessment')
                    .position('top right' )
                    .parent('#mainBody')
                    .hideDelay(4000)
                );
            });

        assessments.back = function(){
            $state.go('main.assessment_list',$stateParams);
        };

        assessments.formatdate = function(date){
            return moment(date).format('MMMM D, YYYY');
        };
    }   
    
}());