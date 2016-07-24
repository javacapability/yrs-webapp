(function() {
    
    angular.module('irpbackendModule')
            .config(function($mdThemingProvider) {
        $mdThemingProvider.theme('default')
          .primaryPalette('green',{ 'default':'600'})
          .accentPalette('light-green');
      });

}());