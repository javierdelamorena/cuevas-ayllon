
var paginass = 0;
var preciomaximo = 0;
var preciominimo = 0;

function filtrotiposervicioPrecio() {
    console.log('entramos en filtrar precio');
    preciominimo = $('#precioMinimo').val();
    preciomaximo = $('#precioMaximo').val();
    $("#ofertas").empty();
   
    cargardatosPrecio();
};


function cargardatosPrecio() {
    console.log('entramos en  cargardatosPrecio()');
    $.ajax({
        type: 'GET',
        //url: "mercadilloPaginasPreciosTipoServicio",
        url:"https://cuevas-de-ayllon.com/mercadilloPaginasPreciosTipoServicio",
        data: {
            'page': paginass,
            'tipoServicio': $('#myInput').val(),
            'precioMin': preciominimo,
            'precioMax': preciomaximo
        },

    }).done(function (objetos) {

        console.log(objetos);
        if (objetos.length != 0) {
            var html = '';
            
            for (var i = 0; i < objetos.length; i++) {


                if (objetos[i].tipo_servicio == 'Venta') {

                    html += '<div id="altura">';
                    html += '<div class="row" style="margin-top: 7%;">';
                    html += '<div class="col-lg-5" style="margin-top: 3%; float:left">';
                    html += '<div id="carouselExampleControls' + objetos[i].id + '" class="carousel slide" data-bs-ride="carousel">';
                    html += '<div class="carousel-inner">';
                    if (objetos[i].foto1) {
                        html += '<div class="carousel-item active">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto1 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto2) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto2 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto3) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto3 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    html += '</div>';
                    html += '<button class="carousel-control-prev" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="prev">';
                    html += '<span class="carousel-control-prev-icon" aria-hidden="true"></span>';
                   
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                   
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-7" style="margin-top: 1%;" id="textoMercadillo">';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;">';
                    html += '<p >Fecha del anuncio: ' + objetos[i].fecha + '</p>';
                    html += '</div>';

                    html += '<div  "class="col-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">';
                    html += '<p >Se vende: ' + objetos[i].nombre_servicio + '</p>';
                    html += '</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Precio: ' + objetos[i].precio + ' Euros</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Nombre: ' + objetos[i].nombre + ' </div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Telefono: ' + objetos[i].telefono + ' </div>';
                    html += '<div class="col-lg-1" style="display: contents;align-items: center;word-break: break-all; align-items: center; margin: 0 0 1em 1em;">';
                    html += '<div  style="padding: 3%;">' + objetos[i].texto + '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '<hr style="margin-top: 3%;">';




                }
                if (objetos[i].tipo_servicio == 'Compra') {
                    html += '<div id="altura">';

                    html += '<div class="row" style="margin-top: 7%;">';

                    html += '<div class="col-lg-5" style="margin-top: 3%; float:left">';
                    html += '<div id="carouselExampleControls' + objetos[i].id + '" class="carousel slide" data-bs-ride="carousel">';
                    html += '<div class="carousel-inner">';
                    if (objetos[i].foto1) {
                        html += '<div class="carousel-item active">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto1 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto2) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto2 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto3) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto3 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    html += '</div>';
                    html += '<button class="carousel-control-prev" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="prev">';
                    html += '<span class="carousel-control-prev-icon" aria-hidden="true"></span>';
                    
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                   
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-7" style="margin-top: 1%;" id="textoMercadillo">';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;">';
                    html += '<p >Fecha del anuncio: ' + objetos[i].fecha + '</p>';
                    html += '</div>';

                    html += '<div  "class="col-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">';
                    html += '<p >Se compra: ' + objetos[i].nombre_servicio + '</p>';
                    html += '</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Precio: ' + objetos[i].precio + ' Euros</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Nombre: ' + objetos[i].nombre + ' </div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Telefono: ' + objetos[i].telefono + ' </div>';
                    html += '<div class="col-lg-1" style="display: contents;align-items: center;word-break: break-all; align-items: center; margin: 0 0 1em 1em;">';
                    html += '<div  style="padding: 3%;">' + objetos[i].texto + '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '<hr style="margin-top: 3%;">';




                }
                if (objetos[i].tipo_servicio == 'Servicios') {
                    html += '<div id="altura">';

                    html += '<div class="row" style="margin-top: 7%;">';

                    html += '<div class="col-lg-5" style="margin-top: 3%; float:left">';
                    html += '<div id="carouselExampleControls' + objetos[i].id + '" class="carousel slide" data-bs-ride="carousel">';
                    html += '<div class="carousel-inner">';
                    if (objetos[i].foto1) {
                        html += '<div class="carousel-item active">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto1 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto2) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto2 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto3) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto3 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    html += '</div>';
                    html += '<button class="carousel-control-prev" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="prev">';
                    html += '<span class="carousel-control-prev-icon" aria-hidden="true"></span>';
                    
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                   
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-7" style="margin-top: 1%;" id="textoMercadillo">';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;">';
                    html += '<p >Fecha del anuncio: ' + objetos[i].fecha + '</p>';
                    html += ' </div>';

                    html += '<div  "class="col-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">';
                    html += '<p >Se ofrece: ' + objetos[i].nombre_servicio + '</p>';
                    html += '</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Precio: ' + objetos[i].precio + ' Euros</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Nombre: ' + objetos[i].nombre + ' </div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Telefono: ' + objetos[i].telefono + ' </div>';
                    html += '<div class="col-lg-1" style="display: contents;align-items: center;word-break: break-all; align-items: center; margin: 0 0 1em 1em;">';
                    html += '<div  style="padding: 3%;">' + objetos[i].texto + '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '<hr style="margin-top: 3%;">';
                    html += '</div>';

                }
                if (objetos[i].tipo_servicio == 'Alquiler') {

                    html += '<div id="altura">';
                    html += '<div class="row" style="margin-top: 7%;">';

                    html += '<div class="col-lg-5" style="margin-top: 3%; float:left">';
                    html += '<div id="carouselExampleControls' + objetos[i].id + '" class="carousel slide" data-bs-ride="carousel">';
                    html += '<div class="carousel-inner">';
                    if (objetos[i].foto1) {
                        html += '<div class="carousel-item active">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto1 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto2) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto2 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    if (objetos[i].foto3) {
                        html += '<div class="carousel-item">';

                        html += "<img src='/uploadsMercadillo/" + objetos[i].foto3 + "' class='d-block w-100'  alt='...'>";

                        html += '</div>';
                    }
                    html += '</div>';
                    html += '<button class="carousel-control-prev" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="prev">';
                    html += '<span class="carousel-control-prev-icon" aria-hidden="true"></span>';
                    
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                   
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-7" style="margin-top: 1%;" id="textoMercadillo">';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;">';
                    html += '<p >Fecha del anuncio: ' + objetos[i].fecha + '</p>';
                    html += ' </div>';

                    html += '<div  "class="col-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">';
                    html += '<p >Se alquila: ' + objetos[i].nombre_servicio + '</p>';
                    html += '</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Precio: ' + objetos[i].precio + ' Euros</div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Nombre: ' + objetos[i].nombre + ' </div>';
                    html += '<div class="col-lg-1" style="width: 100%;font-weight: 600;" id="textoMercadillo">Telefono: ' + objetos[i].telefono + ' </div>';
                    html += '<div class="col-lg-1" style="display: contents;align-items: center;word-break: break-all; align-items: center; margin: 0 0 1em 1em;">';
                    html += '<div  style="padding: 3%;">' + objetos[i].texto + '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '</div>';
                    html += '<hr style="margin-top: 3%;">';


                }




                $('#ofertas').append(html);
                observador = new IntersectionObserver((entradas) => {
                    entradas.forEach(entradas => {
                        if (entradas.isIntersecting) {
                            paginass++;
                            cargardatosPrecio();
                        }

                    })
                    console.log(entradas);

                }, {

                    rootMargin: '0px 0px 0px 0px',
                    threshole: 1.0
                });
                // if (producto) {
                //     observador.unobserve(producto);
                // }


                const alturas = document.querySelectorAll('#altura');
                producto = alturas[alturas.length - 1];
                console.log(producto);

                observador.observe(producto);

            }
        }

    }

    ).fail(function () {
        console.log("Fallo!");
    })
        .always(function () {
            console.log("Completo!");
        });;




};
