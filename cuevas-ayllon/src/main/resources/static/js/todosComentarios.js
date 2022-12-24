$(document).ready(function script() {


  $.ajax({
    type: 'GET',
    url: "todosComentarios",
    //url: "https://cuevas-de-ayllon.com/todosComentarios",


  }).done(function (data) {



    // console.log("Hecho Correcto!"+data.usuario.foto);
    // console.log("Esta es la data!" + data);

    // var usuario = [];
    // for (let i = 0; i < data.length; i++) {
    // usuario.push(data.usuario);
    // }
    // console.log("Esta es el usuario: " + usuario);
    // var html2 = '';
    // for (let i = 0; i < data.length; i++) {


    //   html2 += '<tr>';
    //   html2 += '<td><img class="img-thumbnail  float-left" style="width: 50px ;float: left; border-radius: 90px;" src=http://192.168.0.105:8080/spring-boot-cuevas-ayllon/uploads/' + usuario[i].foto + "></td>";     
    //   html2 += '</tr>';
    //   html2 += '<tr>';
    //   html2 += '<td style=" border-style: solid;font-weight: bold;color: white;">' + usuario[i].nombre + '</td>';    
    //   html2 += '</tr>';
    //   html2 += '<tr>';     
    //   html2 += '<td><textarea  style="border-style: solid;padding: 10px;border-radius: 30px;background-color: rgb(251, 251, 251);font-weight: bold; border-width: thin; width: 600px;height: 150px;" disabled>Ha dicho :' + data[i].comentario + '</textarea></td>';      
    //   html2 += '</tr>';

      
    // }


    // $('#todosLosComentarios').append(html2);

  }).fail(function () {
    console.log("Fallo!");
  })
    .always(function () {
      console.log("Completo!");
    });;
})