'use strict';

/* Controllers */

angular.module('app').controller('PromoController',
			[
				'$scope',
				'$log',
				'PromoService',
				function($scope, $log, PromoService) {
					
				$scope.scontrino = {
							id : '',
							data : '',
							idDeposito : '',
							cassa : '',
							scontrino : '',
							codFid : '',
							bollini : '',
							ora : '',
							totale : '',
							dettScontrino : {
								id : '',
								qta : '',
								riga : '',
								codArt : '',
								sconto : '',
								inPromo : '',
								qtaCassa : '',
								prezzo : '',
								barcode : '',
								idScontrino : ''
						}
						
					};
				
				$scope.scontrini = [];
				
				// ******** createScontrino **********
				$scope.createScontrino = function(scontrino) {
					ScontriniService
							.creaScontrino(scontrino)
							.then(
									//okInsArt(),
									function(errResponse) {
										showmsg("ERRORE: Impossibile Creare lo Scontrino");
										console.error('Errore Creazione  Scontrino');
									});
				};
				
				// ******** deleteScontrino **********
				$scope.deleteScontrino = function(idScontrino) {
					ScontriniService
							.deleteScontrino(idScontrino)
							.then(
									//OkInsEan(),
									function(errResponse) {
										console.error('Errore Eliminazione Scontrino');
									});
				};

				// ******** selectScontrino **********
				$scope.selectScontrino = function(idScontrino) {
					ScontriniService
							.selScontrino(idScontrino)
							.then(
									function(d) {
										$log.log('Caricamento Scontrino', d);
										$scope.scontrini = d;
										//$scope.edit(d.codart);
									},
									function(errResponse) {

										if (errResponse.status == "404") {
											
											showmsg("Scontrino non Trovato");
											
										} else if (errResponse.status == "-1") {

											showmsg("Errore: Servizio non attivo o non raggiungibile!");
										}

									});
				};
}]);