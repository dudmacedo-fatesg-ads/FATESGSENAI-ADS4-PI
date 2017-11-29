$(document).ready(function () {
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
    
    $("#clienteEstado").change(function () {
        $.get("ApoioEndereco.get", {estado : $("#clienteEstado").val()})
                .done(function (data) {
                    $("#clienteCidade").html(data);
                });
    });
//    
//    $("#data").mask("99/99/9999");
//    $("#telefone").mask("(99) 9999-9999");
//    $("#cpf").mask("999.999.999-99");
//    $("#cep").mask("99999-999");
//
//    $('#celular').focusout(function () {
//        var phone, element;
//        element = $(this);
//        element.unmask();
//        phone = element.val().replace(/\D/g, '');
//        if (phone.length > 10) {
//            element.mask("(99) 99999-999?9");
//        } else {
//            element.mask("(99) 9999-9999?9");
//        }
//    }).trigger('focusout');
});