$(document).ready(function script() {


  console.log('etamos en ajax Manager barras');

  

    var html2 = '';

    console.log("encima del canvas");
    html2 += "<canvas id='myChart' ></canvas>";

    
    $('#graficoBarras').append(html2);
    var html = '';
    
    
    html += "<script src='js/graficos.js'></script>";
    $('body').append(html);



  
    });

