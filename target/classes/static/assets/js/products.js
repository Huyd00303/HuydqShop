var myApp = angular.module('product', ['ui.bootstrap']);

myApp.directive('fileModel', ['$parse', function ($parse) {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      var model = $parse(attrs.fileModel);
      var modelSetter = model.assign;

      element.bind('change', function () {
        scope.$apply(function () {
          modelSetter(scope, element[0].files[0]);
        });
      });
    }
  };
}]);
myApp.service('fileUpload', ['$https:', function ($https:) {
  this.uploadFileToUrl = function (file, uploadUrl) {
    var fd = new FormData();
    fd.append('file', file);

    $https.post(uploadUrl, fd,{
      transformRequest: angular.identity,
      headers: { 'Content-Type': undefined }
    })
      .success(function () {
      })
      .error(function () {
      });
  }
}]);


myApp.controller("productList", function ($scope, $http, $filter, fileUpload) {


  $scope.product = {};
  $scope.search = "";
  $scope.list = [];



  $scope.create = function () {
    var file = $scope.myFile;
    console.log('file is ');
    console.dir(file);
    var uploadUrl = "/http://localhost:8080/product/upload";
    fileUpload.uploadFileToUrl(file, uploadUrl);
  $http.post("http://localhost:8080/product/create/", $scope.product)
    .then(
      function successCallback(response) {
        console.log($scope.product);
        window.location.reload();
      },
      function errorCallback(error) {
        console.log(error);
      }
    );
}

  $http({
  method: 'GET',
  url: 'http://localhost:8080/product/read'
}).then(function mySuccess(response) {
  $scope.list = response.data;
  $scope.totalItems = $scope.list.length;
}, function myError(error) {
  console.log(error);
});


$scope.itemsPerPage = 3;

$scope.currentPage = 1;
$scope.maxSize = 5; // Number of pager buttons to show

$scope.getItem = function () {
  return $filter('filter')($scope.list, $scope.search).length;
}



$scope.delete = function (id) {

  alert(id)
  $http.delete("http://localhost:8080/product/" + id).then(
    function successCallback(response) {
      console.log(id);
      window.location.reload();
    },
    function errorCallback(response) {
      console.log("Unable to perform get request");
    }
  );
}

$scope.edit = function (item) {

  $scope.product.id = item.id;
  $scope.product.name = item.name;
  $scope.product.price = item.price;

}

$scope.updateProduct = function () {
  $http
    .put(
      "http://localhost:8080/product/update/", $scope.product)
    .then(function () {
      $http
        .get("http://localhost:8080/product/read")
        .then(function (response) {
          $scope.list = response.data;
        });
    });
}
});