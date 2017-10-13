'use strict';
(function() {
    myApp.controllers.config([ '$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            controller : 'todoItemController',
            templateUrl : '/app/view/items.html'
        }).otherwise('/');
    } ]);
})();