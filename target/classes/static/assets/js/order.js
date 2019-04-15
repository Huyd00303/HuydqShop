var myapp = angular.module("order", ['ui.bootstrap']);
myapp.controller("orderList", function ($scope, $http, $filter) {

	$scope.order = {};

	$scope.edit = function (item) {
    $scope.order.id = item.id;
		$scope.order.amount = item.amount;
		$scope.order.customerAddress = item.customerAddress;
		$scope.order.customerEmail = item.customerEmail;
		$scope.order.customerName = item.customerName;
		$scope.order.customerPhone = item.customerPhone;
		$scope.order.orderDate = item.orderDate;
		$scope.order.orderNum = item.orderNum;
	}

	$scope.update = function () {
		$http.put('http://localhost:8080/order/update/', $scope.order)
		.then(function () {
			$http
				.get("http://localhost:8080/order/read")
				.then(function (response) {
					$scope.list = response.data;
				});
		});

	};


	$http({
		method: 'GET',
		url: 'http://localhost:8080/order/read'
	}).then(function mySuccess(response) {
		$scope.list = response.data;
		$scope.totalItems = $scope.list.length;
	}, function myError(response) {
		$scope.list = response.statusText;
	});

	$scope.list = [];
	$scope.itemsPerPage = 5;
	$scope.search = "";

	$scope.currentPage = 1;
	$scope.maxSize = 5; // Number of pager buttons to show



	$scope.getItem = function () {
		return $filter('filter')($scope.list, $scope.search).length;
	}


	$scope.delete = function (id) {
		alert(id)
		$http.delete('http://localhost:8080/order/' + id)
			.then(
				function (response) {
					console.log('remove succeed');
					window.location.reload();
				},
				function (response) {
					console.log(response)
				}
			);
	}


	$scope.create = function () {
		$http.post('http://localhost:8080/order/create/', $scope.order)
			.then(
				function (response) {
					window.location.reload();
				},
				function (response) {
					console.log(error);
				}
			);
	}

});



