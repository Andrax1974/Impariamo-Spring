'use strict';

/* Controller */

angular.module('app').controller('PromoController',
			[
				'$scope',
				'$log',
				'$interval',
				'PromoService',
				function($scope, $log, $interval, PromoService) {
					
				$scope.promozioni = [];
				
				// ******** DELETE **********
				$scope.deletePromo = function(idPromo) {
					PromoService
							.DelPromo(idPromo)
							.then(
									//OkInsEan(),
									function(errResponse) {
										console.error(errResponse);
									});
				};
				
				// ******** SELECT ALL **********
				$scope.selectAllPromo = function() {
					PromoService
							.SelAllPromo()
							.then(
									function(d) {
										$log.log('Caricamento Tutte Le Promo', d);
										$scope.promozioni = d;
										//$scope.edit(d.codart);
									},
									function(errResponse) {

										if (errResponse.status == "404") {
											
											showmsg("Promo non Trovata");
											
										} else if (errResponse.status == "-1") {

											showmsg("Errore: Servizio non attivo o non raggiungibile!");
										}

									});
				};

				$interval( function()
					{ 
						$scope.selectAllPromo() 
					}, 2000);
				
				$scope.elimina = function(IdPromo) {
					 
					$log.log('Eliminazione Promo Id' + IdPromo);
					
					$scope.deletePromo(IdPromo);
					
					$scope.selectAllPromo();
				};
				
				//STARTUP FUNCTION
				var startAction = function() {
					
					$log.log('Avvio Promo Controller');
					
					$scope.titolo = "Promozioni Disponibili";
					
					$scope.selectAllPromo();
				}
				
				
				var init = function() {
					startAction();
					$scope.numrow = 0;
				};

				init();
				
				}]);