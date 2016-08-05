(function () {

    angular.module('loginModule').config(routes);

    routes.$inject = ['$stateProvider', '$httpProvider'];

    function routes($stateProvider, $httpProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: 'login/login.html',
                controller: 'loginController',
                controllerAs: 'login'
            }).state('reset', {
                url: '/reset',
                templateUrl: 'login/reset.html',
                controller: 'loginController',
                controllerAs: 'login',
                params: { userId: '' }
            }).state('reset_new', {
                url: '/reset',
                templateUrl: 'login/reset_new.html',
                controller: 'loginController',
                controllerAs: 'login',
                params: { userId: '' }
            });
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.headers.get = {'Content-Type': 'application/json, text/html', 'If-Modified-Since': 0};
    }

})();