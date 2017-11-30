function validaIDF(valor) {
    valor = valor.split(".").join("").split("-").join("").split("/").join("");
    if (valor === "") {
        return false;
    }
    var digitos_iguais = true;
    for (var i = 0; i < valor.length - 1; i++) {
        if (valor.charAt(i) !== valor.charAt(i + 1))
        {
            digitos_iguais = false;
            break;
        }
    }
    if (digitos_iguais) {
        return false;
    }

    if (valor.length === 11) {
        var cont = 10, somadv1 = 0, somadv2 = 0;
        for (var i = 0; i < 9; i++) {
            somadv1 += cont * valor.charAt(i);
            somadv2 += (cont + 1) * valor.charAt(i);
            cont--;
        }

        var dv1 = (somadv1 * 10) % 11;
        if (dv1 === 10) {
            dv1 = 0;
        }

        somadv2 += dv1 * 2;
        var dv2 = (somadv2 * 10) % 11;
        if (dv2 === 10) {
            dv2 = 0;
        }

        var dv = (dv1 * 10) + dv2;

        if (dv !== parseInt(valor.substring(9, 11))) {
            return false;
        } else {
            return true;
        }
    } else if (valor.length === 14) {
        var cont1 = 6, cont2 = 5, somadv1 = 0, somadv2 = 0;
        for (var i = 0; i < 12; i++) {
            somadv1 += cont1 * valor.charAt(i);
            somadv2 += cont2 * valor.charAt(i);
            if (cont1 < 9) {
                cont1++;
            } else {
                cont1 = 2;
            }
            if (cont2 < 9) {
                cont2++;
            } else {
                cont2 = 2;
            }
        }

        var dv1 = somadv1 % 11;
        if (dv1 === 10) {
            dv1 = 0;
        }

        somadv2 += dv1 * 9;
        var dv2 = somadv2 % 11;
        if (dv2 === 10) {
            dv2 = 0;
        }

        var dv = (dv1 * 10) + dv2;

        if (dv !== parseInt(valor.substring(12, 14))) {
            return false;
        }
    }
    return true;
}

$(document).ready(function () {
    $(".banner ul.lista_banner").slick({
        autoplay: true,
        dots: false,
        arrows: false,
        slidesToShow: 1,
        slidesToScroll: 1,
        infinite: true,
        autoplaySpeed: 2000,
        responsive: [

            {
                breakpoint: 1210,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: false
                }
            },
            {
                breakpoint: 640,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: false
                }
            },
            {
                breakpoint: 320,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: false
                }
            }

        ]
    });
});


