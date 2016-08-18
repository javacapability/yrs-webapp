(function () {

    angular.module('loginModule')
            .controller('loginController', [
                '$scope',
                '$state',
                '$mdToast',
                'loginServices',
                loginController
            ]);
            
    function loginController($scope, $state, $mdToast, loginServices) {
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
                    }
                }, function (error) {
                    console.log('Error logging-in');
                    $mdToast.show($mdToast.simple()
                        .textContent('Error Logging-in, please check your username or password')
                        .position('top right' )
                        .parent('#mainTitle')
                        .hideDelay(4000)
                    );
                    //$scope.loginForm.$setValidity("login",false);
                });
            
        }

        login.gotoReset = function(){
            $state.go('reset', { 'userId': login.username });
        }

        login.reset = function(){
            console.log('No service yet');
            login.back();
        }

        login.resetNew = function(pswd, pwsd_confirm){
            if (pswd !== pwsd_confirm) {
                $scope.loginForm.$setValidity("login", false);
            } else {
                console.log('No service yet');
                login.back();
            }
        }

        login.back = function(){
            $state.go('login');
        }
    }

}());
