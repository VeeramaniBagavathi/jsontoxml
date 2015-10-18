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
	 $scope.option.hotel = true;

    $scope.loadServices = function() {
        var httpRequest = $http({
            method: 'GET',
            url: 'web/flight/itinerary',
            data: mockDataForFlights

        }).success(function(data, status) {
            $scope.flights = data;
            $scope.travelPrice = data[1].price;
            
            var httpRequestHotels = $http({
                method: 'GET',
                url: 'web/hotels?lat=51.4700256&longi=-0.4564842',
                data: mockDataForHotels

            }).success(function(data, status) {
                $scope.hotels = data;
                $scope.hotelprice = $scope.hotels.hotels[1].amount|0;
                $scope.totalPrice = parseInt($scope.hotels.hotels[1].amount) + parseInt($scope.flights[1].price);
            });
        });
        
       
        
       
      

    };
    
  
    
    $scope.togglePrice = function() {
    	if($scope.option.hotel){
    		$scope.totalPrice =   parseInt($scope.hotels.hotels[1].amount) + parseInt($scope.flights[1].price);
    	}
    	else {
    		$scope.totalPrice =  parseInt($scope.flights[1].price);
    	}
    	/*$scope.totalPrice= $scope.flights[1].price|0;*/
    }
  

    
    $scope.expandedCardView = function(img) {
       $scope.hash=img;
 	   $scope.expandedCard = true; 
    };
    
    $scope.expandedCardViewClose = function() {
  	   $scope.expandedCard = false; 
     };
  
    
    }]);
	var mockDataForFlights = "json=" + encodeURI(JSON.stringify([]));
	var mockDataForHotels = "json=" + encodeURI(JSON.stringify([]));
