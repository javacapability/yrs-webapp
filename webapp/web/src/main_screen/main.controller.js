(function () {

    angular.module('mainModule')
            .controller('mainController', [
                '$scope',
                '$state',
                'mainUserServices',
                mainController
            ]);
            
    function mainController($scope, $state, mainUserServices) {
        var main = this;
        
        main.currentDate = moment().format('MMMM D, YYYY');        
        
        mainUserServices.getCurrentUser("")
            .then(function (data) {
                main.currentUser  = data;
            });
        
        $scope.selectedIndex = 0;

        $scope.$watch('selectedIndex', function(current, old) {
            switch (current) {
                case 0:
                    $state.go('main.user_main');
                    break;
                case 1:
                    $state.go('main.question_main');
                    break;
                case 2:
                    $state.go('main.question_main');
                    break;
                case 3:
                    $state.go('main.question_main');
                    break;
            }
        });

    }

}());