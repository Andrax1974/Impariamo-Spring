'use strict';

angular.module('app').controller('MyController',
		[
			'$scope',
			'$log',
			function($scope, $log)
			{
				
				$scope.titolo = "Inserimento Articoli";
				$scope.nome = "Anonimo";
				
				$scope.articoli = [];
				
				$scope.categorie = ["Alimentari","Freschi","Surgelati"]
				
				$scope.inserisci = function(NewArticolo) {
					 
					$scope.articoli.push(NewArticolo);
				    delete $scope.NewArticolo;
				    $scope.numart++;
					 
				};
				
				
				$scope.$watch('numart',function(newValue,oldValue) {
					console.log('called '+ $scope.numart +' times');
					
					if(newValue == 2) {
						alert('Great! You have 2 items in your wish list. Time to buy what you love. ');
					}
				});
				
				$scope.numart = 0;
					
			}]);