var taxCalculatorApp = angular.module("taxCalculatorApp", []);

taxCalculatorApp.controller("taxCalculatorController", function($scope) {
	$scope.calculate = function(costValue, taxValue) {
		$.ajax({
			url : "./api/calculator",
			type : "GET",
			data : {
				cost : costValue,
				tax : taxValue
			},
			dataType : "json",
			async : false,
			success : function(data, textStatus, jqXHR) {
				if ('error' in data) {
					$scope.totalCostValue = data.error;
				} else {
					$scope.totalCostValue = data.total;
				}
			}
		});
	};
});