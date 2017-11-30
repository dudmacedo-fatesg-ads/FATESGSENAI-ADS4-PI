function validarFormulario() {
    // Valida campos obrigatórios
//    obrig_ok = true;
//    if ($("#clienteTipo").val() === 'F') {
//        alert($("#clienteTelResidencial").val().length);
//        if ($("#clienteNome").val().length <= 0 ||
//                $("#clienteCPF").val().length <= 0 ||
//                $("#clienteRG").val().length <= 0 ||
//                $("#clienteNascimento").val().length <= 0 ||
//                $("#clienteEmail").val().length <= 0 ||
//                $("#clienteCEP").val().length <= 0 ||
//                $("#clienteLogradouro").val().length <= 0 ||
//                $("#clienteBairro").val().length <= 0) {
//            obrig_ok = false;
//        }
//    } else if ($("#clienteTipo").val() === 'J') {
//        obrig_ok = false;
//    }
//    if (!obrig_ok) {
//        alert("Todos os campos marcados com * devem ser preenchidos.");
//        return false;
//    }
//
//    // Valida CPF
//    if (!validaIDF($("#clienteCPF").val())) {
//        alert("O CPF informado é inválido.");
//        return false;
//    }
//    
    // Valida E-mail
    $.get("Register.do", {verifEmail: $("#clienteEmail").val()})
            .done(function (data) {
                if (data === true) {
                    alert("foi");
                }
            });
//
//    // Valida Senhas
//    if ($("#clienteSenha").val().length < 8) {
//        alert("A senha deve ter no mínimo 8 caracteres");
//        return false;
//    } else if ($("#clienteSenha").val() !== $("#clienteConfirmaSenha").val()) {
//        alert("A confirmação de senha informada não está correta");
//        return false;
//    }
    $("#clienteHashSenha").val(md5($("#clienteSenha").val()));

    return false;
}

$(document).ready(function () {
    $("#formEditCliente").submit(function () {
        return validarFormulario();
    });

    $("#clienteCPF").mask("999.999.999-99");

    $("#clienteTelResidencial").mask("(99) 9999-9999");
    $("#clienteTelComercial").mask("(99) 9999-9999");
    $("#clienteTelCelular").focusout(function () {
        var phone, element;
        element = $(this);
        element.unmask();
        phone = element.val().replace(/\D/g, '');
        if (phone.length > 10) {
            element.mask("(99) 99999-999?9");
        } else {
            element.mask("(99) 9999-9999?9");
        }
    }).trigger('focusout');

    $("#clienteCEP").mask("99999-999");

    $("#clienteCEP").focusout(function () {
        $.get("ApoioEndereco.get", {cep: $("#clienteCEP").val()})
                .done(function (data) {
                    objEndereco = jQuery.parseJSON(data);
                    $("#clienteLogradouro").val(objEndereco.logradouro);
                    $("#clienteBairro").val(objEndereco.bairro);
                    $("#clienteEstado").val(objEndereco.estado);
                    $.get("ApoioEndereco.get", {estado: $("#clienteEstado").val()})
                            .done(function (data) {
                                $("#clienteCidade").html(data);
                                $("#clienteCidade").val(objEndereco.cidade);
                            });
                });
    });

    $("#clienteEstado").change(function () {
        $.get("ApoioEndereco.get", {estado: $("#clienteEstado").val()})
                .done(function (data) {
                    $("#clienteCidade").html(data);
                });
    });
});