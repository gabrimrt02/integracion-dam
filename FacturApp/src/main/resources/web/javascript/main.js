// TODO Implementar los Scripts interactivos de la página principal

/*
 * Variables de elementos
*/
var documento = $(document);
var secciones = [];
var frame;

/*
 * Variables
*/
var lastLoaded = ""; // Almacena el ultimo recurso cargado dentro del frame

documento.ready(inicio);

function inicio() {
    secciones.push($("#seccion1"));
    secciones.push($("#seccion2"));
    secciones.push($("#seccion3"));
    secciones.push($("#seccion4"));
    secciones.push($("#seccion5"));

    frame = $("#frame");
    lastLoaded = frame.attr("src");

    $.each(secciones, function (indexInArray, valueOfElement) {
        secciones[indexInArray].click(function (e) {
            var pageToLoad = "";

            switch (indexInArray) {
                case 0:
                    pageToLoad = "../html/sections/inicio.html";
                    break;

                case 1:
                    pageToLoad = "../html/sections/ventas.html";
                    break;

                case 2:
                    pageToLoad = "../html/sections/compras.html";
                    break;
            
                case 3:
                    pageToLoad = "../html/sections/clientes.html";
                    break;

                case 4:pageToLoad = "../html/sections/empleados.html"

                default:
                    break;
            }

            if (pageToLoad != lastLoaded) {
                frame.attr("src", pageToLoad);
                lastLoaded = frame.attr("src");
            }
        });
    });

}
