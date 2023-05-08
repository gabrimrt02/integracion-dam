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
    secciones.push($("#seccion6"));

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
                    pageToLoad = "#";
                    break;
            
                case 3:
                    pageToLoad = "../html/sections/test.html";
                    break;

                default:
                    break;
            }

            console.log("A cargar: " + pageToLoad)

            if (pageToLoad != lastLoaded) {
                frame.attr("src", pageToLoad);
                lastLoaded = frame.attr("src");
            } else {
                console.log('Ya se encuentra en la ventana');
            }
        });
    });

}
