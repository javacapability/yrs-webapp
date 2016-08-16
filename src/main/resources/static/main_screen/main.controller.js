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
        main.currentUser  = $stateParams.user;
        main.showMain = $stateParams.showMain;

        main.changePassword = function(){
            main.showMain = false;
        };

        main.back = function(){
            main.showMain = true;
        };

        main.updatePassword = function(pswd, pswd_new, pswd_new_confirm){
            mainUserServices.validatelogin(main.currentUser.userId, pswd)
                .then(function (data) {
                    if (data.userId) {
                        $stateParams.userId = data.userId;
                        $stateParams.tokenid = data.$httpHeaders.tokenid;
                        $stateParams.user = data;
                        if (pswd_new !== pswd_new_confirm) {
                            $scope.mainForm.$setValidity("login", false);
                        } else {
                            var user = {};
                            user.userId = $stateParams.user.userId;
                            user.pswd = pswd_new;
                            mainUserServices.changePassword(user, $stateParams)
                                .then(function () {
                                    mainUserServices.validatelogin(main.currentUser.userId, pswd_new)
                                        .then(function (data) {
                                            if (data.userId) {
                                                $stateParams.userId = data.userId;
                                                $stateParams.tokenid = data.$httpHeaders.tokenid;
                                                $stateParams.user = data;
                                                main.showMain = true;
                                            }
                                        }, function() {
                                            $scope.mainForm.$setValidity("login", false);
                                        })
                                });
                        }
                    }
                }, function() {
                    $scope.mainForm.$setValidity("login", false);
                });
        };

        main.logout = function(){
            mainUserServices.logout($stateParams)
                .then(function () {
                    $state.go('login');
                });
        };

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
                    $state.go('main.parameters_main',$stateParams);
                    break;
            }
        });

    }

}());