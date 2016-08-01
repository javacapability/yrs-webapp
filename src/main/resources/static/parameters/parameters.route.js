(function () {

    angular.module('parametersModule').config(routes);

    routes.$inject = ['$stateProvider', '$httpProvider'];

    function routes($stateProvider, $httpProvider) {
        $stateProvider
                .state('main.parameters_main', {views: {'parameters_view': {
                            templateUrl: 'parameters/parameters.html',
                            controller: 'parametersController',
                            controllerAs: 'parameters'
                        }
                    },
                    params: { userId: '' , tokenid: ''}
                });
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.headers.get = {'Content-Type': 'application/json, text/html', 'If-Modified-Since': 0};
    }

})();