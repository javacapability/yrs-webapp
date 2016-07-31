(function () {

    angular.module('assessmentModule')
            .controller('assessmentController', [
                '$scope',
                '$state',
                '$stateParams',
                'assessmentServices',
                assessmentController
            ]);
            
    function assessmentController($scope, $state, $stateParams, assessmentServices) {
        var assessments = this;

        assessments.filterType = 'active';
        assessments.searchTypes = [
            {value: 'active', name: 'Active Assessments'},
            {value: 'archived', name: 'Archived Assessments'}];

        assessments.assessmentList = [];

        $scope.$watch('assessments.filterType', function() {
            assessmentServices.getAssessmentList(assessments.filterType, $stateParams)
                .then(function (data) {
                    assessments.assessmentList = data;
                });
        });

        assessmentServices.getAssessmentList(assessments.filterType, $stateParams)
            .then(function (data) {
                assessments.assessmentList = data;
            });

        assessments.viewAssessment = function(assessmentid){
            var params = $stateParams;
            params.id = assessmentid;
            $state.go('main.assessment_view', params);
        };

        assessments.getAssessmentStatus = function(status){
            switch (status) {
                case 1:
                    return "Pending Assessment";
                    break;
                case 2:
                    return "Recommendation Given";
                    break;
                case 3:
                    return "Archived";
                    break;
            }
        };

        assessments.formatdate = function(assessDate){
            return moment(assessDate).format('MMMM D, YYYY');
        };
    }
    
}());