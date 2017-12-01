function validarFormulario() {
    if ($("#loginEmail").val().length <= 0) {
        alert("Por favor, preencha o e-mail");
        $("#loginEmail").focus();
        return false;
    } else if ($("#loginSenha").val().length <= 0) {
        alert("Por favor, preencha a senha");
        $("#loginSenha").focus();
        return false;
    }
    $("#loginSenha").val(md5($("#loginSenha").val()));

    return true;
}

$(document).ready(function () {
    $("#usuarioCEP").mask("99999-999");

    $("#formLogin").submit(function () {
        return validarFormulario();
    });
});