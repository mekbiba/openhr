var app = angular.module("root",[]);
app.controller("nodesController", function($scope, $http){
    $http.get("https://localhost:8443/get-all-nodes")
    .then(function (response) {
    	console.log(response.data);
        $scope.nodes = response.data;
    });
});