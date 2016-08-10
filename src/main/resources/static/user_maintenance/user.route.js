(function () {

    angular.module('userModule').config(routes);

    routes.$inject = ['$stateProvider', '$httpProvider'];

    function routes($stateProvider, $httpProvider) {
        $stateProvider
                .state('main.user_main', {views: {'user_view': {
                            templateUrl: 'user_maintenance/user_maintenance.html',
                            controller: 'userController',
                            controllerAs: 'users'
                        }
                    },
                    params: { userId: '' , tokenid: ''}
                }).state('main.user_edit', {views: {'user_view': {
                            templateUrl: 'user_maintenance/user_edit.html',
                            controller: 'userEditController',
                            controllerAs: 'users'
                        }
                    },
                    params: { userId: '' , tokenid: '', editMode: '' , userId: ''}
                });
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.headers.get = {'Content-Type': 'application/json, text/html', 'If-Modified-Since': 0};
    }

})();