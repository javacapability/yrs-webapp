(function () {

    angular.module('parametersModule')
            .controller('parametersController', [
                '$scope',
                '$state',
                '$stateParams',
                'parametersServices',
                parametersController
            ]);
            
    function parametersController($scope, $state, $stateParams, parametersServices) {
        var parameters = this;
        
        parameters.parametersList = [];
        
        parametersServices.getParameters($stateParams)
            .then(function (data) {
                parameters.parametersList = data;
            });

        parameters.updateParameters = function(){
            parametersServices.updateParameters(parameters.parametersList, $stateParams)
                .then(function () {
                });
        };
    }
    
}());