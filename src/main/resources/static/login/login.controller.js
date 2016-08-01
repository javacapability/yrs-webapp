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
                    if (data.userId) {
                        console.log('Success');
                        data.$httpHeaders.userId = data.userId
                        data.$httpHeaders.user = data;
                        $state.go('main', data.$httpHeaders);
                    } else {
                        console.log('Error logging-in');
                        $scope.$setValidity("login",false);
                    }
                }, function (error) {
                    console.log('Error logging-in');
                    $scope.loginForm.$setValidity("login",false);
                });
            
            //$state.go('main'); //remove this if service is working
            
        }
    }

}());
