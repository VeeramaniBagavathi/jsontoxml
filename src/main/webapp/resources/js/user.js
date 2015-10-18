   angular.module('user', [])
    .controller('MISController', ['$scope', '$http', function($scope, $http) {
      $scope.category = {
       sports : true,
       adventure : true,
	   technology : true
     };
	 
	 $scope.flights = [];

    $scope.loadFlights = function() {
        var httpRequest = $http({
            method: 'GET',
            url: 'web/flight/itinerary',
            data: mockDataForFlights

        }).success(function(data, status) {
            $scope.flights = data;
        });

    };
    
    $scope.hotels = [];

    $scope.loadHotels = function() {
        var httpRequest = $http({
            method: 'GET',
            url: 'web/hotels',
            data: mockDataForHotels

        }).success(function(data, status) {
            $scope.hotels = data;
        });

    };
    
    $scope.expandedCardView = function() {
 	   $scope.expandedCard = true; 
    };
    
    $scope.expandedCardViewClose = function() {
  	   $scope.expandedCard = false; 
     };
  
    
    }]);
	var mockDataForFlights = "json=" + encodeURI(JSON.stringify([]));
	var mockDataForHotels = "json=" + encodeURI(JSON.stringify([]));
