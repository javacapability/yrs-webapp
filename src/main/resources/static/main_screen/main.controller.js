(function () {

    angular.module('mainModule')
            .controller('mainController', [
                '$scope',
                '$state',
                '$stateParams',
                'mainUserServices',
                mainController
            ]);
            
    function mainController($scope, $state, $stateParams, mainUserServices) {
        var main = this;

        console.log($stateParams);

        main.currentDate = moment().format('MMMM D, YYYY');        
        
        mainUserServices.getCurrentUser("")
            .then(function (data) {
                main.currentUser  = data;
            });
        
        $scope.selectedIndex = 0;

        $scope.$watch('selectedIndex', function(current, old) {
            switch (current) {
                case 0:
                    $state.go('main.user_main',$stateParams);
                    break;
                case 1:
                    $state.go('main.question_main',$stateParams);
                    break;
                case 2:
                    $state.go('main.assessment_list',$stateParams);
                    break;
                case 3:
                    $state.go('main.question_main');
                    break;
            }
        });

    }

}());