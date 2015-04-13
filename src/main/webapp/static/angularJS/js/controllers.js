'use strict';

var rentControllers = angular.module('rentControllers', []);

rentControllers.controller("rentController", function($scope,$http,$routeParams) {

	$http.get('http://localhost:8080/GradleSpringRestBasis-master/car/').
	  success(function(data, status, headers, config) {
	  	$scope.carsList = data;
	  	$scope.selected = data[0];
	  }).
	  error(function(data, status, headers, config) {
	  });

	$scope.rent = function(selected) {
		$http.delete('http://localhost:8080/GradleSpringRestBasis-master/car/' + selected.plateNumber).
		  success(function(data, status, headers, config) {
		  }).
		  error(function(data, status, headers, config) {
		  });
	};
        
});

rentControllers.controller("getBackController", function($scope,$http,$routeParams) {

	$scope.plateNumber = "";
	
	$scope.getBack = function(plateNumber) {
		$http.put('http://localhost:8080/GradleSpringRestBasis-master/car/' + plateNumber).
		  success(function(data, status, headers, config) {
		  }).
		  error(function(data, status, headers, config) {
		  });
	};
        
});