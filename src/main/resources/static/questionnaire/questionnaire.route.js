(function () {

    angular.module('questionModule').config(routes);

    routes.$inject = ['$stateProvider', '$httpProvider'];

    function routes($stateProvider, $httpProvider) {
        $stateProvider
                .state('main.question_main', {views: {'question_view': {
                            templateUrl: 'questionnaire/question_list.html',
                            controller: 'questionController',
                            controllerAs: 'questions'
                        }
                    },
                    params: { userId: '' , tokenid: ''}
                }).state('main.question_edit', {views: {'question_view': {
                            templateUrl: 'questionnaire/question_edit.html',
                            controller: 'questionEditController',
                            controllerAs: 'questions'
                        }
                    },
                    params: { userId: '' , tokenid: '', editMode: '' , id: '', priority: ''}
                });
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.headers.get = {'Content-Type': 'application/json, text/html', 'If-Modified-Since': 0};
    }

})();