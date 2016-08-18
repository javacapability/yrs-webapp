(function () {

    angular.module('mainModule')
            .controller('mainController', [
                '$scope',
                '$state',
                '$stateParams',
                '$mdDialog',
                '$mdToast',
                'mainUserServices',
                mainController
            ]);
            
    function mainController($scope, $state, $stateParams, $mdDialog, $mdToast, mainUserServices) {
        var main = this;

        console.log($stateParams);

        main.currentDate = moment().format('MMMM D, YYYY');
        main.currentUser  = $stateParams.user;
        main.showMain = $stateParams.showMain;

        main.changePassword = function(){
            main.pswd = '';
            main.pswd_new = '';
            main.pswd_new_confirm = '';
            main.showMain = false;
        };

        main.back = function(){
            main.showMain = true;
        };

        main.updatePassword = function(pswd, pswd_new, pswd_new_confirm){
            if (pswd_new === pswd_new_confirm) {
                mainUserServices.validatelogin(main.currentUser.userId, pswd)
                    .then(function (data) {
                        if (data.userId) {
                            $stateParams.userId = data.userId;
                            $stateParams.tokenid = data.$httpHeaders.tokenid;
                            $stateParams.user = data;
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
                                        }, function (error) {
                                            $mdToast.show($mdToast.simple()
                                                .textContent('Relogging-on of user failed, please try to log-off and log-in again')
                                                .position('top right')
                                                .parent('#mainPass')
                                                .hideDelay(4000)
                                            );
                                        })
                                }, function (error) {
                                    $mdToast.show($mdToast.simple()
                                        .textContent('Password change failed, please try again')
                                        .position('top right')
                                        .parent('#mainPass')
                                        .hideDelay(4000)
                                    );
                                });

                        }
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Password change failed: Please check if current password is correct')
                            .position('top right')
                            .parent('#mainPass')
                            .hideDelay(4000)
                        );
                    });
            } else {
                $mdToast.show($mdToast.simple()
                    .textContent('Password change failed: Please check re-typed new password')
                    .position('top right')
                    .parent('#mainPass')
                    .hideDelay(4000)
                );
            }
        };

        main.logout = function(){
            var confirm = $mdDialog.confirm()
                .title('Warning')
                .textContent('Are you sure you want to log-off?')
                .ariaLabel('Are you sure you want to log-off?')
                .ok('Yes')
                .cancel('No');
            $mdDialog.show(confirm).then(function() {
                mainUserServices.logout($stateParams)
                    .then(function () {
                    });
                $state.go('login');
            },function(){});
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