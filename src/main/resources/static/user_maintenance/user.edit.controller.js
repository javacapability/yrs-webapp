(function () {

    angular.module('userModule')
            .controller('userEditController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                '$mdToast',
                'userServices',
                userEditController
            ]);
            
    function userEditController($scope, $state, $stateParams, $timeout, $mdDialog, $mdToast, userServices) {
        var users = this;
        
        users.userGroups = [
            {value: 1, groupName: 'Admin'},
            {value: 2, groupName: 'Advisor'}];
        
        console.log('test - ' + $stateParams.editMode);
        users.editMode = $stateParams.editMode;
        users.confirmPswd = '';
        users.defaultUser = {userGroup: { id : 1 }};
        users.editUser = users.defaultUser;
        
        if (users.editMode === 'edit'){
            var userId = $stateParams.id;
            users.editModeTitle = 'Edit';
            userServices.getEditUser(userId, $stateParams)
                .then(function (data) {
                    users.editUser = data;
                    users.editUser.birthday = new Date(moment(users.editUser.birthday).format('YYYY-MM-DD'));
                });
        } else {
            users.editModeTitle = 'Create new';
        }
        
        users.back = function(){
            $timeout(function () {
                $state.go('main.user_main',$stateParams);
            }, 200);
        };
        
        users.reset = function(){
            users.editUser = users.defaultUser;
        };
        
        users.save = function(){
            if (users.editUser.pswd === users.confirmPswd) {
                userServices.saveUser(users.editUser, $stateParams)
                    .then(function () {
                        $stateParams.status = '0';
                        users.back();
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Failed saving the user')
                            .position('top right' )
                            .parent('#mainBody')
                            .hideDelay(4000)
                        );
                    });
            } else {
                $mdToast.show($mdToast.simple()
                    .textContent('Failed saving the user: Entered passwords should be the same')
                    .position('top right' )
                    .parent('#mainBody')
                    .hideDelay(4000)
                );
            }
        };
        
        users.update = function(){
            if (users.editUser.pswd === users.confirmPswd) {
                userServices.updateUser(users.editUser, $stateParams)
                    .then(function () {
                        $stateParams.status = '1';
                        users.back();
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Failed updating the user')
                            .position('top right' )
                            .parent('#mainBody')
                            .hideDelay(4000)
                        );
                    });
            } else {
                $mdToast.show($mdToast.simple()
                    .textContent('Failed saving the user: Entered passwords should be the same')
                    .position('top right' )
                    .parent('#mainBody')
                    .hideDelay(4000)
                );
            }
        };
        
        users.delete = function(){
            var confirm = $mdDialog.confirm()
                .title('Warning')
                .textContent('Are you sure you want to delete the User?')
                .ariaLabel('Are you sure you want to delete the User?')
                .ok('Yes')
                .cancel('No');
            $mdDialog.show(confirm).then(function() {
                userServices.deleteUser(users.editUser.userId, $stateParams)
                    .then(function () {
                        $stateParams.status = '2';
                        users.back();
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Failed saving the user')
                            .position('top right' )
                            .parent('#mainBody')
                            .hideDelay(4000)
                        );
                    });
            }, function() {
            });

        };
        
    }   
    
}());