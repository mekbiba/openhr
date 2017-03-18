var app = angular.module("root",[]);
app.controller("testController", function($scope, $http){
    $http.get("/rest-api/hr/employee/all")
    .then(function (response) {
        $scope.employees = response.data;
    });
});