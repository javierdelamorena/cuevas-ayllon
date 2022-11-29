$("#salvarComentario").on("click", function () {
    console.log("se ha pulsado salvarComentario");

    $.ajax({
        type: 'GET',
        url: "salvarcomentario",
        data: { 'comentario': $('#comentario').val() }

    }).done(function (objetos) {

        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!");
        console.log("Hecho Correcto!" + objetos.usuario.foto);
        console.log("Hecho Correcto!" + objetos.usuario.nombre);
        console.log("Hecho Correcto!" + objetos.comentario);

        var html2 = '';
        html2 += '<tr>';
        html2 += '<td><img class="img-thumbnail  float-left" style="width: 50px ;float: left; border-radius: 90px;" src=http://192.168.0.103:8080/spring-boot-cuevas-ayllon/uploads/'+ objetos.usuario.foto + "></td>";
        html2 += '</tr>';

        html2 += '<tr>';
        html2 += '<td style=" border-style: solid;font-weight: bold;color: white;">' + objetos.usuario.nombre.toUpperCase() + '</td>';
        html2 += '</tr>';
        html2 += '<tr>';
        console.log("Hecho Correcto estamos dentro del for!" + objetos.comentarios.comentario);
        html2 += '<td><p   style="background-color: rgba(255, 255, 255, 0.9);border-radius: 50px;box-shadow: 0px 10px 3px 0px rgba(0,0,0,0.5);position: relative;margin-bottom: 30px;padding: 5px;">Ha dicho:  ' + objetos.comentarios[0].comentario + '</p></td>';

        html2 += '</tr>';

        $('#todosLosComentarios').append(html2);

    }).fail(function () {
        console.log("Fallo!");
    })
        .always(function () {
            console.log("Completo!");
        });;
});