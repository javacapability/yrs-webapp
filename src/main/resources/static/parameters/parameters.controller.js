(function () {

    angular.module('parametersModule')
            .controller('parametersController', [
                '$scope',
                '$state',
                '$stateParams',
                '$mdToast',
                'parametersServices',
                parametersController
            ]);
            
    function parametersController($scope, $state, $stateParams, $mdToast, parametersServices) {
        var parameters = this;
        
        parameters.parametersList = [];
        
        parametersServices.getParameters($stateParams)
            .then(function (data) {
                parameters.parametersList = data;
            }, function (error) {
                $mdToast.show($mdToast.simple()
                    .textContent('Failed to retrieve settings')
                    .position('top right' )
                    .parent('#mainBody')
                    .hideDelay(4000)
                );
            });

        parameters.updateParameters = function(){
            parametersServices.updateParameters(parameters.parametersList, $stateParams)
                .then(function () {
                }, function (error) {
                    $mdToast.show($mdToast.simple()
                        .textContent('Error saving settings')
                        .position('top right' )
                        .parent('#mainBody')
                        .hideDelay(4000)
                    );
                });
        };
    }
    
}());