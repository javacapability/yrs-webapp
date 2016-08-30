(function () {

    angular.module('loginModule')
            .controller('loginController', [
                '$scope',
                '$state',
                '$mdToast',
                '$cookies',
                'loginServices',
                loginController
            ]);
            
    function loginController($scope, $state, $mdToast, $cookies, loginServices) {
        var login = this;
        
        login.username = "";
        login.password = "";

        var sessionUser = $cookies.get('userId');
        var sessionToken = $cookies.get('tokenid');

        if (sessionUser !== undefined && sessionUser !== ''
            && sessionToken !== undefined && sessionToken !== ''){
            var tokenParam = {};
            tokenParam.userId = sessionUser;
            tokenParam.tokenid = sessionToken;
            loginServices.validateToken(tokenParam)
                .then(function (data) {
                    $state.go('main', tokenParam);
                });
        }

        login.loginUser = function(username, password){
            console.log('Logging in as ' + username);
            
            loginServices.login(username, password)
                .then(function (data) {
                    if (data.userId) {
                        console.log('Success');
                        data.$httpHeaders.userId = data.userId
                        data.$httpHeaders.user = data;
                        $cookies.put('userId', data.$httpHeaders.userId);
                        $cookies.put('tokenid', data.$httpHeaders.tokenid);
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
