(function () {

    angular.module('mainModule').config(routes);

    routes.$inject = ['$stateProvider', '$httpProvider'];

    function routes($stateProvider, $httpProvider) {
        $stateProvider
            .state('main', {
                url: '/',
                templateUrl: 'main_screen/main.html',
                controller: 'mainController',
                controllerAs: 'main'
            });
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.headers.get = {'Content-Type': 'application/json, text/html', 'If-Modified-Since': 0};
    }

})();