'use strict';
(function() {
    var todoItemController = function($scope, GenericFactory, $http) {
        $scope.items = [],
        $scope.item = {"completed":false};
        var me = this;
         
        me.doGetItems = function(){
            GenericFactory.getMethod({
                url : '/api/items',
                successCB : function(result) {
                    $scope.items = result;
                }
            });
        }
        
        me.doGetPendingItems = function(){
            GenericFactory.getMethod({
                url : '/api/items/pending',
                successCB : function(result) {
                    $scope.items = result;
                }
            });
        }
        
        me.doGetCompletedItems = function(){
            GenericFactory.getMethod({
                url : '/api/items/completed',
                successCB : function(result) {
                    $scope.items = result;
                }
            });
        }
         
        $scope.doDeleteItem = function(id){
            GenericFactory.deleteMethod({
                url : '/api/item/delete/'+id,
                method:"DELETE",
                //data : id,T
                successCB : function(result) {
                    me.doGetItems();
                }
            });
        }
        
        $scope.doSaveItems = function(items){
            GenericFactory.postMethod({
                url : '/api/items/save',
                method:"POST",
                data : items,
                successCB : function(result) {
                    me.doGetItems();
                }
            });
        }
         
        $scope.doEditItem = function(id){
            for (var i = 0; i < $scope.items.length; i++) {
                if($scope.items[i].id === id){
                    $scope.item = $scope.items[i];
                    break;
                }
            }
        }
         
        me.createItem = function(){
            $http({
            	method: 'POST',
	            url: '/api/item/save',
	            headers: { 'Content-Type': 'application/json' },
	            data: $scope.item
            }).then(function successCallback(response) {
            	  me.doGetItems();
			  }, function errorCallback(response) {
				  me.doGetItems();
			  });
        }
         
        me.updateItem = function(){
        	
        	$http({
            	method: 'POST',
	            url: '/api/item/update',
	            headers: { 'Content-Type': 'application/json' },
	            data: $scope.item
            }).then(function successCallback(response) {
            	  me.doGetItems();
			  }, function errorCallback(response) {
				  me.doGetItems();
			  });
        	
        }
         
        $scope.doSubmit = function(){
            if($scope.item.id !== '' && $scope.item.id !== undefined){
                me.updateItem();
            }else{
                me.createItem();
            }
        }
         
        $scope.reset = function(){
            $scope.item = {"completed":false};
            $scope.itemForm.$setPristine();
        }
        
        $scope.statuses = [
            { id: 0, name: "All ..."},
            { id: 1, name: "Pending"},
            { id: 2, name: "Completed"},
        ];

        $scope.statusSelected = $scope.statuses[0];
        
        $scope.onStatusChange = function (statusSelected) {
        	if(statusSelected.id == 0) {
        		me.doGetItems();
        	} else if(statusSelected.id == 1) {
        		me.doGetPendingItems();
        	} else if(statusSelected.id == 2) {
        		me.doGetCompletedItems();
        	}
        };
         
        function init() {
            me.doGetItems();
        }
        init();
         
    };
 
    myApp.controllers.controller('todoItemController', [ '$scope', 'GenericFactory', '$http', todoItemController ]);
})();