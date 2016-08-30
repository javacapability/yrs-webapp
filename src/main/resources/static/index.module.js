(function () {

    angular.module('irpbackendModule',
            [   
                'ngMaterial',
                'ngResource',
                'ui.router',
                'constants',
                'ngMessages',
                'ngAnimate',
                'ngCookies',
                'mainModule',
                'loginModule',
                'userModule',
                'questionModule',
                'assessmentModule',
                'parametersModule'
            ]);

}());