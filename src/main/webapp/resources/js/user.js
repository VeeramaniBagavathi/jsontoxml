   angular.module('user', [])
    .controller('MISController', ['$scope', '$http', function($scope, $http) {
      $scope.category = {
       sports : true,
       adventure : true,
	   technology : true
     };
      $scope.option = {
    	       stay : true
    	     };
	 
	 $scope.flights = [];
	 $scope.hotels = [];
	 $scope.travelStayPrice = 0;
	 $scope.travelPrice = 0;
	 $scope.stayPrice = 0;
	

    $scope.loadServices = function() {
        var httpRequest = $http({
            method: 'GET',
            url: 'web/flight/itinerary',
            data: mockDataForFlights

        }).success(function(data, status) {
            $scope.flights = data;
            $scope.travelPrice = data[1].price;
        });
        
        var httpRequestHotels = $http({
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
