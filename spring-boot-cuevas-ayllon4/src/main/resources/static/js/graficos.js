var nombre = [];
var puntuacion = [];

var color3 = [];

var borde = [];

var objeto = [];
var data;

    $.ajax({
        type: 'GET',
        url: "https://cuevas-de-ayllon.com/votacionPropuestasAjax",
    }).done(function (datos) {


       
        var html2 = '';
        html2 += '<table class="table table-striped table-bordered" style="font-size: 23px;background-color: rgb(0 126 255 / 34%);text-align: center;border: solid;margin-top: 2%;">';
        html2 += '<tr>  ';
        html2 += '<th scope="col">Propuesta</th> ';
        html2 += '<th scope="col">Apoyos</th>';
       
        html2 += '</tr>';

        for (let i = 0; i < datos.length; i++) {




            html2 += '</tr>';
            html2 += '<tr>';
            html2 += '<td ">' + datos[i].propuesta + '</td>';
            html2 += '<td ">' + datos[i].puntuacion + '</td>';
            html2 += '</tr>';



        }
        html2 += '</table>';
        $('#propuestas').append(html2);

    });


