'use strict';
(function() {
    myApp.factories.factory('GenericFactory', [ '$http', function($http) {
        var genericFactory = {};
 
        genericFactory.getMethod = function(config) {
            return $http(config).then(function success(result) {
                if(result.status >= 200 && result.status <=299){
                    if(config.successCB) config.successCB(result.data);
                }
            });
        };
        
        genericFactory.postMethod = function(config) {
            return $http(config).then(function success(result) {
                if(result.status >= 200 && result.status <=299){
                    if(config.successCB) config.successCB(result.data);
                }
            });
        };
        
        genericFactory.putMethod = function(config) {
            return $http(config).then(function success(result) {
                if(result.status >= 200 && result.status <=299){
                    if(config.successCB) config.successCB(result.data);
                }
            });
        };
        
        genericFactory.deleteMethod = function(config) {
            return $http(config).then(function success(result) {
                if(result.status >= 200 && result.status <=299){
                    if(config.successCB) config.successCB(result.data);
                }
            });
        };
 
        return genericFactory;
    } ]);
})();