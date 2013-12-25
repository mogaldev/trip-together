var app = angular.module('MyApp', []);

app.service('MyService', function($http) {
	
	this.get = function(urlPath, parameters, sucssesCallBack) {
		$http({method:'GET',url:urlPath,params:parameters}).success(sucssesCallBack).error(this.errorResponse);
	};
	
	this.post = function(urlPath, parameters, sucssesCallBack) {
		$http({method:'POST',url:urlPath,params:parameters}).success(sucssesCallBack).error(this.errorResponse);
	};
	
	this.errorResponse = function() {
		alert('request failed');
	};
});

app.controller('MyCtrl', function($scope, $location, MyService) {
	
	$scope.currentTime = "wait for server...";

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
	
	$scope.pushTheButtonPost = function() {
		var url = $location.absUrl() + 'services/hello/getTime';
		MyService.post(url, null, function(data, status, headers, config) {
			$scope.answer = '';
			$scope.answerPost = data;
		});
	};
});