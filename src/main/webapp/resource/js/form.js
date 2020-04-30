/* 
 * 
 *


var helloAjaxApp = angular.module("myApp", []);

helloAjaxApp.controller("UserController", [ '$scope', '$http', function($scope, $http) {

	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
	$scope.sendData = function() {
		$http({
			url : 'formServlet',
			method : "POST",
			data : {
				'username' : $scope.name
			}
		}).then(function(response) {
		
			console.log("Success -> " + response.data);
			$scope.msgFromServlet = response.data;
		}, function(response) {
		
			console.log("Failure -> " + response.data);
			$scope.msgFromServlet = response.data;
		});
	};
} ]); */



var app = angular.module('MyApp', ['ngFileUpload'])
    app.controller('MyController', function ($scope, $http, $window) {
    	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
    	
    	
        $scope.SelectFile = function (file) {
            $scope.SelectedFile = file;
        };
        $scope.Upload = function () {
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xls|.xlsx)$/;
            if (regex.test($scope.SelectedFile.name.toLowerCase())) {
                if (typeof (FileReader) != "undefined") {
                    var reader = new FileReader();
                    //Para otros navegadores que no sean IE.
                    if (reader.readAsBinaryString) {
                        reader.onload = function (e) {
                            $scope.ProcessExcel(e.target.result);
                            $scope.sendData();
                        };
                        reader.readAsBinaryString($scope.SelectedFile);
                    } else {
                        //Para navegador IE.
                        reader.onload = function (e) {
                            var data = "";
                            var bytes = new Uint8Array(e.target.result);
                            for (var i = 0; i < bytes.byteLength; i++) {
                                data += String.fromCharCode(bytes[i]);
                            }
                            $scope.ProcessExcel(data);
                            $scope.sendData();
                        };
                        reader.readAsArrayBuffer($scope.SelectedFile);
                    }
                } else {
                    $window.alert("Este navegador no soporta HTML5.");
                }
            } else {
                $window.alert("Por favor slecciona un archivo excel válido.");
            }
        };
 
        $scope.ProcessExcel = function (data) {
            //Read the Excel File data.
            var workbook = XLSX.read(data, {
                type: 'binary'
            });
 
            //Fetch the name of First Sheet.
            var firstSheet = workbook.SheetNames[0];
 
            //Read all rows from First Sheet into an JSON array.
            var excelRows = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[firstSheet]);
            console.log(excelRows.length);
 
            //Display the data from Excel file in Table.
            $scope.$apply(function () {
                $scope.Productos = excelRows;
                console.log("Llegue hasta aquí");
                $scope.IsVisible = true;
            });
        };
        
    	$scope.sendData = function() {
    		$http({
    			url : 'formServlet',
    			method : "POST",
    			data : {
    				'productos' : $scope.Productos
    			}
    		}).then(function(response) {
    			
    	            $scope.prod = response.data;
    		
    			console.log("Success -> " + response.data);
    			console.log("Success -> " + $scope.prod.productosI);
//    			$scope.msgFromServlet = response.data;
    		}, function(response) {
    		
    			console.log("Failure -> " + response.data);
    			$scope.msgFromServlet = response.data;
    		});
    	};
    	
    	
    	
    });
