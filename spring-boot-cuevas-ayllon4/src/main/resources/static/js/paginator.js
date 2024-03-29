const factsEl = document.querySelector('.facts');
const loader = document.querySelector('.loader');

const getfacts = async (page, limit) => {
    const API_URL = `https://cuevas-de-ayllon.com/mercadilloPginasPreciosTipoServicio?page=${page}&limit=${limit}`;
    const response = await fetch(API_URL);
    // handle 404 
    if (!response.ok) {
        throw new Error(`An error occurred: ${response.status}`);
    }
    return await response.json();
}
const showfacts = (facts) => {
    facts.forEach(objetos => {
        const factEl = document.createElement('blockfact');
        factEl.classList.add('fact');
        //factEl.innerHTML = ` ${fact.fact} `;
        var x = $("#ofertas").empty();
            console.log(objetos);
            var html = '';
            html += '<div class="row g-3" style="margin-top: 3%;">';
            html += '<div class="col-auto">'; 
            html += '<label for="precioMinimo" class="form-label">Precio Minimo</label>';                  
            html += '<input type="number" value="0" class="form-control" id="precioMinimo" placeholder="precio min">';
            html += '</div>'
            html += '<div class="col-auto">';  
            html += '<label for="precioMaximo" class="form-label">Precio Maximo</label>';                  
            html += '<input type="number" value="0" class="form-control" id="precioMaximo" placeholder="precio max">';
            html += '</div>'
            html += '<div class="col-auto">'; 
                             
            html+= '<button class="btn btn-primary mb-3" id="filtrarPrecio" onclick="myFunction()" style="margin-top: 23%;">Filtrar por Precio</button>';
            html+= '</div>';
            html+= '</div>';

            for (var i = 0; i < objetos.length; i++) {
                

                if (objetos[i].tipo_servicio == 'Venta') {
                    
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
                    html += '<span class="sr-only">Previous</span>';
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                    html += '<span class="sr-only">Next</span>';
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-4" style="margin-top: 1%;" id="textoMercadillo">';
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
                    html += '<hr style="margin-top: 3%;width: 72%;">';




                }
                if (objetos[i].tipo_servicio == 'Compra') {
                    
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
                    html += '<span class="sr-only">Previous</span>';
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                    html += '<span class="sr-only">Next</span>';
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-4" style="margin-top: 1%;" id="textoMercadillo">';
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
                    html += '<hr style="margin-top: 3%;width: 72%;">';




                }
                if (objetos[i].tipo_servicio == 'Servicios') {
                    
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
                    html += '<span class="sr-only">Previous</span>';
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                    html += '<span class="sr-only">Next</span>';
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-4" style="margin-top: 1%;" id="textoMercadillo">';
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
                    html += '<hr style="margin-top: 3%;width: 72%;">';

                }
                if (objetos[i].tipo_servicio == 'Alquiler') {
                    
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
                    html += '<span class="sr-only">Previous</span>';
                    html += '</button>';
                    html += '<button class="carousel-control-next" data-bs-target="#carouselExampleControls' + objetos[i].id + '" role="button" data-bs-slide="next">';
                    html += '<span class="carousel-control-next-icon" aria-hidden="true"></span>';
                    html += '<span class="sr-only">Next</span>';
                    html += '</button>';
                    html += '</div>';

                    html += '</div>';
                    html += '<div class="col-lg-4" style="margin-top: 1%;" id="textoMercadillo">';
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
                    html += '<hr style="margin-top: 3%;width: 72%;">';


                }



                // html += '</div>';

            }
            // 
        factsEl.appendChild(html);
    });
};


const hideLoader = () => {
    loader.classList.remove('show');
};
const showLoader = () => {
    loader.classList.add('show');
};
const hasMorefacts = (page, limit, total) => {
    const startIndex = (page - 1) * limit + 1;
    return total === 0 || startIndex < total;
};
const loadfacts = async (page, limit) => {
    // show the loader 
    showLoader();
    try {
        // if having more facts to fetch 
        if (hasMorefacts(page, limit, total)) {
            // call the API to get facts 
            const response = await getfacts(page, limit);
            // show facts 
            showfacts(response.data);
            // update the total 
            total = response.total;
        }
    } catch (error) {
        console.log(error.message);
    } finally {
        hideLoader();
    }
};