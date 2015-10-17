   angular.module('user', [])
    .controller('MISController', ['$scope', '$http', function($scope, $http) {
      $scope.category = {
       sports : true,
       adventure : true,
	   technology : true
     };
	 
	 $scope.flights = [];

    $scope.loadPeople = function() {
        var httpRequest = $http({
            method: 'GET',
            url: 'web/flight/itinerary',
            data: mockDataForThisTest

        }).success(function(data, status) {
            $scope.flights = data;
        });

    };
    }]);
	var mockDataForThisTest = "json=" + encodeURI(JSON.stringify([
   
]));
