(function () {
    angular.module('irpbackendModule').config(routes);

    routes.$inject = ['$urlRouterProvider'];

    function routes($urlRouterProvider) {
        $urlRouterProvider.otherwise('login');
    }

})();