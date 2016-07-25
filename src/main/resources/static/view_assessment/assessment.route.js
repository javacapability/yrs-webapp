(function () {

    angular.module('assessmentModule').config(routes);

    routes.$inject = ['$stateProvider', '$httpProvider'];

    function routes($stateProvider, $httpProvider) {
        $stateProvider
                .state('main.assessment_list', {views: {'assessment_view': {
                            templateUrl: 'view_assessment/assessment_list.html',
                            controller: 'assessmentController',
                            controllerAs: 'assessments'
                        }
                    },
                    params: { userId: '' , tokenid: ''}
                }).state('main.assessment_edit', {views: {'assessment_view': {
                            templateUrl: 'view_assessment/assessment_view.html',
                            controller: 'assessmentViewController',
                            controllerAs: 'assessments'
                        }
                    },
                    params: { userId: '' , tokenid: '', id: ''}
                });
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.headers.get = {'Content-Type': 'application/json, text/html', 'If-Modified-Since': 0};
    }

})();