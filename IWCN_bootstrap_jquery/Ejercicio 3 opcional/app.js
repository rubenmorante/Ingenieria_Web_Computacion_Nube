
$( document ).ready(function() {
    $('.buscar').click(function() {
        buscar({
            min_taken_date:'txtMin_taken_date',
            max_taken_date:'txtMax_taken_date',
            min_upload_date:'txtMin_upload_date',
            max_upload_date:'txtMax_upload_date',
            tags:'txtGroup_id',
            text:'txtText'
        });
    });

    // Asociamos el datepicker a cada input de fecha
    $('.fecha').each(function() {
        $(this).datepicker({
            format: "yyyy-mm-dd",
            language: "es",
            autoclose: true
        });
    });
});

function crear_url_busqueda(app_config) {
    var url_busqueda = 'https://api.flickr.com/services/rest/?&method=flickr.photos.search&api_key=' + api_key + '&user_id=' + user_id;

    for (var param in app_config) {
        // cogemos cada componente del objeto app_config y si el input al que
        // hace referencia no está vacio lo añadimos a la url del servicio

        if (app_config.hasOwnProperty(param)) {
            var input_id = '#' + app_config[param];
            var value = $(input_id).val();
            if (value.length > 0) {
                url_busqueda += '&' + param + '=' + value
            }
        }
    }

    url_busqueda += '&format=json&nojsoncallback=1';
    return url_busqueda;
}

function mostrar_resultado(indice, url) {
    var pastilla_responsive = $("<div></div>").attr("class", "responsive").attr("id", "pastilla" + indice);
    var miniatura = $("<div></div>").attr("class", "imagenes").attr("id", "miniatura" + indice);
    var imagen = $("<img/>").attr("src", url);

    miniatura.append(imagen);
    pastilla_responsive.append(miniatura);
    $("#resultados-busqueda").append(pastilla_responsive);
}

function comprobar_resultados(datos) {
    if (datos.photos.photo.length < 1) {
        $("#resultados-busqueda").append("<h3>No hay resultados</h3>")
    }
}

function buscar(app_config) {
    var url_busqueda = crear_url_busqueda(app_config);

    $.getJSON(url_busqueda, function(datos) {
        var i;
        $("#resultados-busqueda").empty(); // borrar fotos anteriores

        comprobar_resultados(datos);

        for (i=0;i<datos.photos.photo.length;i++) {
            var item = datos.photos.photo[i];
            var url = 'https://farm'+item.farm+".staticflickr.com/"+item.server+'/'+item.id+'_'+item.secret+'_m.jpg';

            mostrar_resultado(i, url);
        }
        crear_visor_de_fotos();
    });
}

function crear_visor_de_fotos(){
    // Get the modal
    var modal = document.getElementById('myModal');

    var boton_cerrar = document.getElementsByClassName("close")[0];

    boton_cerrar.onclick = function() {
        modal.style.display = "none";
    };

    var images = document.getElementsByTagName("img");
    var modalImg = document.getElementById("imagen-ampliada");
    var i;
    for (i = 0; i < images.length; i++) {
        // Para cada imagen mostrada, le asociamos al evento click sobre la
        // imagen una función anonima que cargue la foto correspondiente.
        images[i].onclick = function(){
            modal.style.display = "block";
            modalImg.alt = this.alt;

            // Cambiar el tamaño de la miniatura por una mas grande
            // https://www.flickr.com/services/api/misc.urls.html
            modalImg.src = this.src.replace(/\_m\.jpg/, '_c.jpg');
        }
    }
}