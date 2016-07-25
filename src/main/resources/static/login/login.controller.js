(function () {

    angular.module('loginModule')
            .controller('loginController', [
                '$scope',
                '$state',
                'loginServices',
                loginController
            ]);
            
    function loginController($scope, $state, loginServices) {
        var login = this;
        
        login.username = "";
        login.password = "";
        
        login.loginUser = function(username, password){
            console.log('Logging in as ' + username);
            
            loginServices.login(username, password)
                .then(function (data) {
                    data.$httpHeaders.userId = data.userId;
                    console.log (data.$httpHeaders);
                    $state.go('main', data.$httpHeaders);
                });
            
            //$state.go('main'); //remove this if service is working
            
        }
    }

}());
