$(document).ready(function() {
    $(".hp-select").select2({
    	placeholder: "Tipo do imóvel"
	});

    $( "#campo_pessoa_juridica").click(function() {
    	$('.hp-grupo-pessoa-fisica').removeClass('d-none')
	});
	$( "#campo_pessoa_fisica").click(function() {
    	$('.hp-grupo-pessoa-fisica').addClass('d-none')
	});
});