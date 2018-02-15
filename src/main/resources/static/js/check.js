$('#senha, #confimar-senha').on('keyup', function () {
	if ($('#senha').val() == $('#confimar-senha').val()) {
		$('#message').html('Senhas são iguais').css('color', 'green');
	}else{
		$('#message').html('Senhas não são iguais').css('color', 'red');
	}
});
