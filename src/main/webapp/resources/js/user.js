   angular.module('user', [])
    .controller('MISController', ['$scope', '$http', function($scope, $http) {
      $scope.category = {
       sports : true,
       adventure : true,
	   technology : true
     };
	 
	 $scope.people = [];

    $scope.loadPeople = function() {
        var httpRequest = $http({
            method: 'GET',
            url: 'web/rest/test',
            data: mockDataForThisTest

        }).success(function(data, status) {
            $scope.people = data;
        });

    };
    }]);
	var mockDataForThisTest = "json=" + encodeURI(JSON.stringify([
    {
    id: 1,
    firstName: "Peter",
    lastName: "Jhons"},
{
    id: 2,
    firstName: "David",
    lastName: "Bowie"}
]));
