(function () {

    angular.module('userModule')
            .controller('userController', [
                '$scope',
                '$state',
                '$stateParams',
                '$timeout',
                '$mdDialog',
                'userServices',
                userController
            ]);
            
    function userController($scope, $state, $stateParams, $timeout, $mdDialog, userServices) {
        var users = this;
        
        users.userList = [];
        
        userServices.getUsers($stateParams)
            .then(function (data) {
                users.userList = data;
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
                            $state.reload('main.user_main');
                        }, 200);
                    });
            }, function() {
            });


        };
        
    }
    
}());