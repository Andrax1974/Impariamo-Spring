$(document).ready(function() {
	$('.navbar-nav li a').click(function(e) {

		$('.navbar-nav li.active').removeClass('active');

		var $parent = $(this).parent();
		$parent.addClass('active');
		e.preventDefault();
	});
});