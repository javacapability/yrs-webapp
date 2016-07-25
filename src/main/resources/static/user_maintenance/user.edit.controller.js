(function () {

    angular.module('userModule')
            .controller('userEditController', [
                '$scope',
                '$state',
                '$stateParams',
                'userServices',
                userEditController
            ]);
            
    function userEditController($scope, $state, $stateParams, userServices) {
        var users = this;
        
        users.userGroups = ['Admin','Advisor'];
        
        console.log('test - ' + $stateParams.editMode);
        users.editMode = $stateParams.editMode;
        
        users.editUser = {};
        
        if (users.editMode === 'edit'){
            var userId = $stateParams.userId;
            users.editModeTitle = 'Edit';
            userServices.getEditUser(userId)
                .then(function (data) {
                    users.editUser = data;
                });
        } else {
            users.editModeTitle = 'Create new';
        }
        
        users.back = function(){
            $state.go('main.user_main');
        };
        
        users.reset = function(){
            users.editUser = {};
        };
        
        users.save = function(){
            userServices.saveUser(users.editUser)
                .then(function () {
                });
            users.back();
        };
        
        users.update = function(){
            userServices.updateUser(users.editUser)
                .then(function () {
                });
        };
        
        users.delete = function(){
            userServices.deleteUser(users.editUser.userId)
                .then(function () {
                });
            users.back();
        };
        
    }   
    
}());