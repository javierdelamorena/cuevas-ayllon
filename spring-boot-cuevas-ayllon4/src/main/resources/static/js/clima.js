$(document).ready(function(){
    var url

    $.ajax({
        type: 'GET',
        url : "https://api.openweathermap.org/data/2.5/weather?zip=42341,es&appid=23c09aa09289a7abbaa022343d4121da" 
       

}).done(function (data) {

    // limpiarhtml();
    mostrarClima(data);

    console.log(data);
}).fail(function () {
    console.log("Fallo!");
})
    .always(function () {
        console.log("Completo!");
    });;
});

function mostrarClima(data){
    const {weather:[{icon}],main:{temp,temp_max,temp_min}}=data;
    console.log("temperatura actual",parseInt(temp - 273.15));
    console.log("temperatura maxima",parseInt(temp_max - 273.15));
    console.log("temperatura mminima",parseInt(temp_min - 273.15));
    console.log("el icono es",icon);
     var icono='<span><img style="width: 12%;"src=" http://openweathermap.org/img/wn/'+icon+'@2x.png"></img></span> ';
    $('#fecha').append(', Temp: ',Math.trunc(temp- 273.15),' ºC ',icono);
}
// function limpiarhtml() {
//     while(fecha.firstChild){

//         temp.removeChild($('#fecha').firstChild);

//     }
// }