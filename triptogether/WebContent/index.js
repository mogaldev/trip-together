var app = angular.module('MyApp', []);

app.service('MyService', function($http) {
	
	this.get = function(urlPath, parameters, sucssesCallBack) {
		$http({method:'GET',url:urlPath,params:parameters}).success(sucssesCallBack);
	};
	
	this.post = function(urlPath, parameters, sucssesCallBack) {
		$http({method:'POST',url:urlPath,params:parameters}).success(sucssesCallBack);
	};
});

app.controller('MyCtrl', function($scope, $location, MyService) {

	$scope.changeLocation = function() {
		$scope.locationString = $location.absUrl();
	};
	
	$scope.pushTheButton = function(name) {
		var url = $location.absUrl() + 'services/hello/' + $scope.name;
		MyService.get(url, null, function(data, status, headers, config) {
			$scope.answerPost = '';
			$scope.answer = data;
		});
	};
	
	$scope.pushTheButtonPost = function(name) {
		var url = $location.absUrl() + 'services/hello/';
		MyService.post(url, null, function(data, status, headers, config) {
			$scope.answer = '';
			$scope.answerPost = data;
		});
	};
});