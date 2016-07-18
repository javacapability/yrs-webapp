(function () {

    angular.module('loginModule', ['ngMaterial'])
        .config(['$compileProvider', function($compileProvider) {
            $compileProvider.debugInfoEnabled(false);
        }]);

})();