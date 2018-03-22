$(document).ready(function() {

	/*
	 * $('.navbar-nav li a').click(function(e) {
	 * 
	 * $('.navbar-nav li.active').removeClass('active');
	 * 
	 * var $parent = $(this).parent(); $parent.addClass('active');
	 * e.preventDefault(); });
	 */

	$(function() {
		
		function stripTrailingSlash(str) {
			if (str.substr(-1) == '/') {
				return str.substr(0, str.length - 1);
			}
			return str;
		}

		var url = window.location.pathname;
		var activePage = stripTrailingSlash(url);

		$('.nav li a').each(function() {
			var currentPage = stripTrailingSlash($(this).attr('href'));

			if (activePage == currentPage) {
				$(this).parent().addClass('active');
			}
		});
	});

	$('#account-tabs a').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show')
	})

	$('#codFidelity').keyup(function(e) {
		var txtVal = $(this).val();
		$('#codFidelity_utenti').val(txtVal);
	});
});