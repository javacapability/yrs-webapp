(function () {

    angular.module('assessmentModule')
            .controller('assessmentViewController', [
                '$scope',
                '$state',
                '$stateParams',
                'assessmentServices',
                assessmentViewController
            ]);
            
    function assessmentViewController($scope, $state, $stateParams, assessmentServices) {
        var assessments = this;

        assessments.assessmentView = {};

        assessmentServices.getAssessment($stateParams.id, $stateParams)
            .then(function (data) {
                assessments.assessmentView = data;
            });

        assessments.back = function(){
            $state.go('main.assessment_list',$stateParams);
        };

        assessments.formatdate = function(date){
            return moment(date).format('MMMM D, YYYY');
        };
    }   
    
}());