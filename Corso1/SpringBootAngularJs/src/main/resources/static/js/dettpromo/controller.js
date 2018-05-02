'use strict';

/* Controller */

angular.module('app').controller('GestPromoController',
			[
				'$scope',
				'$log',
				'$location',
				'$timeout',
				'PromoService',
				function($scope, $log, $location, $timeout, PromoService) {
					
				$scope.dettPromo = {
							id : '',
							riga : '',
							codart : '',
							codfid : '',
							inizio : '',
							fine : '',
							oggetto : '',
							isfid : '',
							tipoPromo : {
								descrizione : '',
								id : ''
						}
				};
				
				$scope.promozioni = [];
				
				// ******** CREATE **********
				$scope.createPromo = function(promo) {
					PromoService
							.InsPromo(promo)
							.then(
									okPromo(),
									function(errResponse) {
										//showmsg("ERRORE: Impossibile Creare la Promozione");
										console.error('Errore Creazione Promozione');
									});
				};
				
				// ******** DELETE **********
				$scope.deletePromo = function(idPromo) {
					PromoService
							.DelPromo(idPromo)
							.then(
									okPromo,
									function(errResponse) {
										console.error(errResponse);
									});
				};
				
				// ******** DELETE ROW PROMO**********
				$scope.deleteRowPromo = function(idRowPromo) {
					PromoService
							.DelDettPromo(idRowPromo)
							.then(
									okPromo(),
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
										
									},
									function(errResponse) {

										if (errResponse.status == "404") {
											
											showmsg("Promo non Trovata");
											
										} else if (errResponse.status == "-1") {

											showmsg("Errore: Servizio non attivo o non raggiungibile!");
										}

									});
				};

				// ******** SELECT **********
				$scope.selectPromo = function(idPromo) {
					PromoService
							.SelPromo(idPromo)
							.then(
									function(d) {
										$log.log('Caricamento Promo', d);
										
										$scope.promozioni.length = 0;
										$scope.promozioni = d;
										
										//$scope.edit();
									},
									function(errResponse) {

										if (errResponse.status == "404") {
											
											$log.log('Promo non Trovata');
											//showmsg("Promo non Trovata");
											
										} 
										else if (errResponse.status == "-1") 
										{
											$log.log('Errore: Servizio non attivo o non raggiungibile!');
											//showmsg("Errore: Servizio non attivo o non raggiungibile!");
										}

									});
				};
				
				// ******** okInsPromo **********
				var okPromo = function() 
				{
					  setTimeout(function() 
					  {
						if ($scope.IdPromo.length > 0) 
						{
							$scope.selectPromo($scope.IdPromo);
							resetPromo();
						}
						
					    $scope.$apply(); //this triggers a $digest
					  }, 500);
				};
				
				var resetPromo = function() {

					$log.log('Avvio funzione reset');

					$scope.dettPromo = {
							id : '',
							riga : '',
							codart : '',
							codfid : '',
							inizio : '',
							fine : '',
							oggetto : '',
							isfid : '',
							tipoPromo : {
								descrizione : '',
								id : ''
						}
					};

					$scope.frmPromo.$setPristine();  // reset Form
														
					//var txtbarcode = $window.document.getElementById('barcode');
					//txtbarcode.focus();
					
				}
				
				$scope.edit = function() 
				{
					$scope.promo  = angular.copy($scope.promozioni);
				}
				
				// ******** Funzione Salva **********
				$scope.salva = function() 
				{

					$log.log('Avvio funzione salva');
					
					$scope.dettPromo.isfid = ($scope.dettPromo.isfid == true) ? "Si" : "No";
					var riga = $scope.promozioni.dettPromo.length + 1
					
					$scope.dettPromo.riga = riga;
					
					$log.log('Salvataggio DettPromo', $scope.dettPromo);
					$scope.promozioni.dettPromo.push($scope.dettPromo);
					
					$scope.createPromo($scope.promozioni);

				};
				
				// ******** Funzione Eliminazione Riga **********
				$scope.deleteRiga = function(Id)
				{
					$log.log('Avvio funzione Eliminazione Riga ' + Id);
					
					$scope.deleteRowPromo(Id);
				}
				
				//STARTUP FUNCTION
				var startAction = function() {
					
					$log.log('Avvio DettPromo Controller');
					
					$scope.titolo = "Gestione Promozione";
					
					var url = $location.absUrl().split('/');
					
					if (url[4].length > 0)
					{
						$scope.IdPromo =  url[4];
						$log.log("IdPromo: " + $scope.IdPromo);
						$scope.selectPromo($scope.IdPromo);
					}
					
					$scope.tipopromo = 
					{
						availableOptions : [ 
							{
								value : '1',
								name : 'TAGLIO PREZZO'
							}, 
							{
								value : '2',
								name : 'SCONTO PERCENTUALE'
							}, 
							{
								value : '3',
								name : 'BOLLINI'
							},
							{
								value : '4',
								name : 'NxM'
							}]
					};
					
					
				}
				
				
				var init = function() {
					startAction();
				};

				init();
				
				}]);