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
        
        users.userGroups = [
            {value: 1, groupName: 'Admin'},
            {value: 2, groupName: 'Advisor'}];
        
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
            $state.go('main.user_main',$stateParams);
        };
        
        users.reset = function(){
            users.editUser = {};
        };
        
        users.save = function(){
            userServices.saveUser(users.editUser, $stateParams)
                .then(function () {
                });
            users.back();
        };
        
        users.update = function(){
            userServices.updateUser(users.editUser, $stateParams)
                .then(function () {
                });
        };
        
        users.delete = function(){
            userServices.deleteUser(users.editUser.userId, $stateParams)
                .then(function () {
                });
            users.back();
        };
        
    }   
    
}());