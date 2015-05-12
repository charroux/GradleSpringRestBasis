'use strict';

var app = angular.module('location', [
	'ngRoute','rentControllers']);

app.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/location', {
				templateUrl: 'partials/menu.html'
			}).
			when('/location/louer', {
				templateUrl: 'partials/louer.html',
				controller: 'louerController'
			}).
			when('/location/ramener', {
				templateUrl: 'partials/ramener.html',
				controller: 'ramenerController'
			}).
			otherwise({
				redirectTo: '/location'
			});
}]);
