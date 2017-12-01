function validarFormulario() {
    // Valida campos obrigatórios
    obrig_ok = true;
    // Pessoa Física
    if ($("#clienteTipo").val() === 'F') {
        if ($("#clienteNome").val().length <= 0 ||
                $("#clienteCPF").val().length <= 0 ||
                $("#clienteRG").val().length <= 0 ||
                $("#clienteNascimento").val().length <= 0) {
            obrig_ok = false;
        }
    } //
    // Pessoa Jurídica
    else if ($("#clienteTipo").val() === 'J') {
        if ($("#clienteRazao").val().length <= 0 ||
                $("#clienteCNPJ").val().length <= 0) {
            obrig_ok = false;
        }
    } //
    // Demais campos
    if ($("#clienteEmail").val().length <= 0 ||
            $("#clienteCEP").val().length <= 0 ||
            $("#clienteLogradouro").val().length <= 0 ||
            $("#clienteBairro").val().length <= 0) {
        obrig_ok = false;
    }
    if (!obrig_ok) {
        alert("Todos os campos marcados com * devem ser preenchidos.");
        return false;
    }

    // Valida CPF/CNPJ
    if ($("#clienteTipo").val() === "F") {
        // Valida CPF
        if (!validaIDF($("#clienteCPF").val())) {
            alert("O CPF informado é inválido.");
            $("#clienteCPF").focus();
            return false;
        }
    } else if ($("#clienteTipo").val() === "J") {
        // Valida CNPJ
        if (!validaIDF($("#clienteCNPJ").val())) {
            alert("O CNPJ informado é inválido.");
            $("#clienteCNPJ").focus();
            return false;
        }
    }

    // Valida Telefones
    ntelefones = 0;
    if ($("#clienteTelResidencial").val().length > 0) {
        ntelefones++;
    }
    if ($("#clienteTelComercial").val().length > 0) {
        ntelefones++;
    }
    if ($("#clienteTelCelular").val().length > 0) {
        ntelefones++;
    }
    if (ntelefones < 2) {
        alert("Por favor, informe ao menos dois números de telefone");
        return false;
    }

// Valida E-mail
    $.get("Register.do", {verifEmail: $("#clienteEmail").val()})
            .done(function (data) {
                if (data === "true") {
                    alert("Este endereço de e-mail já está cadastrado para um cliente.");
                    $("#clienteEmail").focus();
                    return false;
                }
            });

//    // Valida Senhas
    if ($("#clienteSenha").val().length < 8) {
        alert("A senha deve ter no mínimo 8 caracteres");
        return false;
    } else if ($("#clienteSenha").val() !== $("#clienteConfirmaSenha").val()) {
        alert("A confirmação de senha informada não está correta");
        return false;
    }
    $("#clienteHashSenha").val(md5($("#clienteSenha").val()));

    return true;
}

function updateTipoCliente() {
    tipo = $("#clienteTipo").val();
    if (tipo === 'F') {
        $("#clientePFCampos").css("display", "block");
        $("#clientePJCampos").css("display", "none");
    } else if (tipo === 'J') {
        $("#clientePJCampos").css("display", "block");
        $("#clientePFCampos").css("display", "none");
    }
}

function updateEnderecoByCEP() {
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
}

function updateListaCidades() {
    $.get("ApoioEndereco.get", {estado: $("#clienteEstado").val()})
            .done(function (data) {
                $("#clienteCidade").html(data);
            });
}

$(document).ready(function () {
    // Controles do form
    $("#formEditCliente").submit(function () {
        return validarFormulario();
    });
    $("#btnCancelar").click(function () {
        window.location = "./";
    });
    // Alternação do tipo do Cliente
    $("#clienteTipo").change(function () {
        updateTipoCliente();
    });
    // Campos do cliente PF
    $("#clienteCPF").mask("999.999.999-99");
    // Campos do cliente PJ
    $("#clienteCNPJ").mask("99.999.999/9999-99");
    $("#clientePJCampos").css("display", "none");
    // Outros Campos
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
    // Campos do endereço
    $("#clienteCEP").mask("99999-999");
    $("#clienteCEP").focusout(function () {
        updateEnderecoByCEP();
    });
    $("#clienteEstado").change(function () {
        updateListaCidades();
    });
});