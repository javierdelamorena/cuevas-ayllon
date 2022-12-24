$("#salvarComentario").on("click", function () {
    console.log("se ha pulsado salvarComentario");


    $.ajax({
        type: 'GET',
        url: "salvarcomentario",
        //url: "https://cuevas-de-ayllon.com/salvarcomentario",
        data: { 'comentario': $('#comentario').val() }

    }).done(function (objetos) {

        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!");
        console.log("foto: " + objetos.usuario.foto);
        console.log("nombre usuario: " + objetos.usuario.nombre);
        console.log("comentario: " + objetos.comentarios[0].comentario);
        console.log("propuesta: " + objetos.propuestas.titulo);

        var html2 = '';
        html2 += '<tr>';
        html2 += '<td><img class="img-thumbnail  float-left" style="width: 50px ;float: left; border-radius: 90px;" src=uploads/' + objetos.usuario.foto + "></td>";
        html2 += '</tr>';

        html2 += '<tr>';
        html2 += '<td style=" border-style: solid;font-weight: bold;color: white;">' + objetos.usuario.nombre.toUpperCase() + '</td>';
        html2 += '</tr>';
        html2 += '<tr>';
        console.log("Hecho Correcto estamos dentro del for!" + objetos.comentarios[0].comentario);
        console.log("Hecho Correcto estamos dentro del for!" + objetos.propuestas.idPropuesta);
        html2 += '<td><p   style="background-color: rgba(255, 255, 255, 0.9);border-radius: 50px;box-shadow: 0px 10px 3px 0px rgba(0,0,0,0.5);position: relative;margin-bottom: 30px;padding: 5px;font-weight: bold;font-size: 20px;font-family: inherit;display: -webkit-inline-box">Ha dicho:  ' + objetos.comentarios[0].comentario + '</p></td>';
        html2 += '</tr>';
        if (objetos.comentarios[0].editable != null) {
            console.log("Hecho Correcto estamos dentro del if de editable!" + objetos.comentarios[0].editable);
            html2 += '<tr>';
            html2 += "<td>";
            html2 += "<button type='submit' class='btn btn-dark' data-bs-toggle='modal' value='Editar' data-bs-target='#staticBackdrop1" + objetos.comentarios[0].id + "' style='color: #eeeeee;background-color: #463232a3; margin-bottom: 30px;margin-right: 1%;float: left;' id=" + objetos.comentarios[0].id + ">Editar</button>";
            html2 += " <form action='/spring-boot-cuevas-ayllon/borrarComentario' method='post'>";
            //html2 += " <form action='https://cuevas-de-ayllon.com/borrarComentario' method='post'>";

            html2 += "<input type='hidden' name='idComentario' value=" + objetos.comentarios[0].id + " >";
            html2 += "<input type='hidden' name='idPropuesta'  value=" + objetos.propuestas.idPropuesta + " >";

            html2 += "<input type='submit' class='btn' value='Borrar' style='color: #eeeeee;background-color: #463232a3; margin-bottom: 30px;'>";
            html2 += "</form>";
            html2 += "<div class='modal fade' id='staticBackdrop1" + objetos.comentarios[0].id + "' data-bs-backdrop='static' data-bs-keyboard='false' tabindex='-1' aria-labelledby='staticBackdropLabel1' aria-hidden='true'>";
            html2 += '<div class="modal-dialog">';
            html2 += '<div class="modal-content">';
            html2 += '<div class="modal-header">';
            html2 += '<h5 class="modal-title" id="staticBackdropLabel">Comentario</h5>';
            html2 += '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>';
            html2 += '</div>';
            html2 += '<div class="modal-body">';
            html2 += '<form action="/spring-boot-cuevas-ayllon/editarComentario" method="get">';
            //html2 += '<form action="https://cuevas-de-ayllon.com/editarComentario" method="get">';
            html2 += '<textarea id="objetos.comentarios.id" name="comentario" value="objetos.comentarios.comentario"  rows="4"cols="50" style=" height: 180px; width: 98%; margin-top: 10px;" maxlength="255">';
            html2 += '' + objetos.comentarios[0].comentario + '';
            html2 += '</textarea>';
            html2 += '<input type="hidden" name="idComentario" value=' + objetos.comentarios[0].id + '>';
            html2 += '<input type="submit" id="enviarPropuesta" value="enviar">';
            html2 += '</form>';
            html2 += '</div>';
            html2 += '<div class="modal-footer">';
            html2 += '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>';
            html2 += '</div>';
            html2 += '</div>';
            html2 += '</div>';
            html2 += '</div>';
            html2 += "</td>"
            html2 += '</tr>';

        }
        // html2 += "<td>"
        // html2 += '<tr>';
        // html2 += "<div class='modal fade' id='staticBackdrop1"+objetos.comentarios[0].id +"' data-bs-backdrop='static' data-bs-keyboard='false' tabindex='-1' aria-labelledby='staticBackdropLabel1' aria-hidden='true'>";
        // html2 += '<div class="modal-dialog">';
        // html2 += '<div class="modal-content">';
        // html2 += '<div class="modal-header">';
        // html2 += '<h5 class="modal-title" id="staticBackdropLabel">Comentario</h5>';
        // html2 += '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>';
        // html2 += '</div>';
        // html2 += '<div class="modal-body">';
        // html2 += '<form action="/spring-boot-cuevas-ayllon/editarComentario" method="get">';
        // html2 += '<textarea id="objetos.comentarios.id" name="comentario" value="objetos.comentarios.comentario"  rows="4"cols="50" style=" height: 180px; width: 98%; margin-top: 10px;" maxlength="255">';
        // html2 += '' + objetos.comentarios[0].comentario + '';
        // html2 += '</textarea>';
        // html2 += '<input type="hidden" name="idComentario" value=' + objetos.comentarios[0].id + '">';
        // html2 += '<input type="submit" id="enviarPropuesta" value="enviar">';
        // html2 += '</form>';
        // html2 += '</div>';
        // html2 += '<div class="modal-footer">';
        // html2 += '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>';
        // html2 += '</div>';
        // html2 += '</div>';
        // html2 += '</div>';
        // html2 += '</div>';
        // html2 += '</td>';
        // html2 += '</tr>';


        $('#todosLosComentarios').append(html2);

    }).fail(function () {
        console.log("Fallo!");
    })
        .always(function () {
            console.log("Completo!");
        });;
});