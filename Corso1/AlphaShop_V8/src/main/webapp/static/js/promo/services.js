'use strict';

MyApp.factory('PromoService', [
	'$http',
	'$q',
	function($http, $q) {

		return {
			
			Crea : function(scontrino) {
				return $http.post('http://localhost:8080/alphashop/rest/scontrino/', scontrino)
					.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Errore Creazione Scontrino');
					return $q.reject(errResponse);
				});
			},
			
			Seleziona : function(idscontrino) {
				return $http
						.get('http://localhost:8080/alphashop/rest/scontrino/' + idscontrino)
						.then(function(response) {
							return response.data;
						}, function(errResponse) {
							console.error('Errore caricamento scontrino');
							return $q.reject(errResponse);
						});
			},
			
			Elimina : function(idscontrino) {
				return $http.delete('http://localhost:8080/alphashop/rest/scontrino/' + idscontrino)
						.then(function(response) {
							return response.data;
				}, function(errResponse) {
					console.error('Errore Eliminazione Scontrino');
					return $q.reject(errResponse);
				});
			}
		};

	}]);