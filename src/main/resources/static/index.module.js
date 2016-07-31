(function () {

    angular.module('irpbackendModule',
            [   
                'ngMaterial',
                'ngResource',
                'ui.router',
                'constants',
                'ngMessages',
                'ngAnimate',
                'mainModule',
                'loginModule',
                'userModule',
                'questionModule',
                'assessmentModule',
                'parametersModule'
            ]);

}());