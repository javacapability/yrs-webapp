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

        assessments.getAssessmentList = function(filterType){
            assessmentServices.getAssessmentList(filterType, $stateParams)
                .then(function (data) {
                    assessments.assessmentList = data;
                }, function (error) {
                    assessments.assessmentList = [];
                });
        };

        assessments.getAssessmentList(assessments.filterType);
        $scope.$watch('assessments.filterType', function() {
            assessments.getAssessmentList(assessments.filterType);
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