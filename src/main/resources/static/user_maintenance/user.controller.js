(function () {

    angular.module('userModule')
            .controller('userController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                '$mdToast',
                'userServices',
                userController
            ]);
            
    function userController($scope, $state, $stateParams, $timeout, $mdDialog, $mdToast, userServices) {
        var users = this;
        
        users.userList = [];

        if ($stateParams.status !== ''){
            var msg = '';
            switch ($stateParams.status) {
                case '0':
                    msg = 'User saved successfully'; break;
                case '1':
                    msg = 'User updated successfully'; break;
                case '2':
                    msg = 'User deleted successfully'; break;
            }
            $mdToast.show($mdToast.simple()
                .textContent(msg)
                .position('top right' )
                .parent('#mainBody')
                .hideDelay(4000)
            );
            $stateParams.status = '';
        }

        userServices.getUsers($stateParams)
            .then(function (data) {
                users.userList = data;
            }, function (error) {
                $mdToast.show($mdToast.simple()
                    .textContent('Error retrieving User list')
                    .position('top right' )
                    .parent('#mainBody')
                    .hideDelay(4000)
                );
            });
            
        users.newUser = function(){
            console.log('New User');
            var params = $stateParams;
            params.editMode = 'new';
            $state.go('main.user_edit', params);
        };
        
        users.editUser = function(id){
            console.log('Edit user ' + id);
            var params = $stateParams;
            params.editMode = 'edit';
            params.id = id;
            $state.go('main.user_edit', params);
        };
        
        users.deleteUser = function(user){
            var confirm = $mdDialog.confirm()
                .title('Warning')
                .textContent('Are you sure you want to delete the User?')
                .ariaLabel('Are you sure you want to delete the User?')
                .ok('Yes')
                .cancel('No');
            $mdDialog.show(confirm).then(function() {
                userServices.deleteUser(user, $stateParams)
                    .then(function () {
                        $timeout(function () {
                            $stateParams.status = '2';
                            $state.go('main.user_main',$stateParams,{reload : 'main.user_main'});
                        }, 200);
                    }, function (error) {
                        $mdToast.show($mdToast.simple()
                            .textContent('Error deleting user')
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