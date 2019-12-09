angular.module("MyApp", [])
    .controller("MyController",["$scope", "$http", function($scope, $http){
    	
    	$scope.showData = false; 	// Variable para mostrar resultados
    	$scope.data = {};     	    // Data encontrados en la consulta
    	$scope.record = {};     	// Clientes actual
    	$scope.errors = {};			// Mensajes de error
    	
    	/**
    	 * Permite consultar lista de envios de certificados fitosanitarios de
    	 * base de datos - protocolo HTTP
    	 */
    	$scope.findAll = function(){
    		console.log("Consulta de certificados");
    	  $http.get("/wlopera/acks")        // URI de llamada
    	  .then(function onSuccess(response) {     // Respuesta OK
    		$scope.showData = true;
    	    $scope.data=response.data;
    	    console.log("##=> Data: ", $scope.data);
    	  }).catch(function onError(response) {   // Respuesta Error
    		$scope.showData = false;
    	    console.log("##=> Error: ", response);
    	  });
    	};
    	
    	/**
    	 * Permite mostrar los detalles de los mensajes de error.
    	 */
    	$scope.mostrar = function(errors) {
    		$scope.errors = errors;
    		$('#recordModal').modal('show');
    	}
    	
    	$scope.findAll();
    	
    }]);