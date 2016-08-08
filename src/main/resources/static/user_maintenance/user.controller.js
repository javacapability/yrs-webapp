(function () {

    angular.module('userModule')
            .controller('userController', [
                '$scope',
                '$state',
                '$stateParams',
                'userServices',
                userController
            ]);
            
    function userController($scope, $state, $stateParams, userServices) {
        var users = this;
        
        users.userList = [];
        
        userServices.getUsers()
            .then(function (data) {
                users.userList = data;
            });
            
        users.newUser = function(){
            console.log('New User');
            var params = $stateParams;
            params.editMode = 'new';
            $state.go('main.user_edit', params);
        };
        
        users.editUser = function(user){
            console.log('Edit user ' + user);
            var params = $stateParams;
            params.editMode = 'edit';
            params.userId = user;
            $state.go('main.user_edit', params);
        };
        
        users.deleteUser = function(user){
            userServices.deleteUser(user, $stateParams)
                .then(function () {
                });
            $state.go('main.user_main',$stateParams);
        };
        
    }
    
}());