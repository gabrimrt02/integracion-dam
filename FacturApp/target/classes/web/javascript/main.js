// TODO Implementar los Scripts interactivos de la página principal
var documento = $(document);
var btnPrimera;
var frame;
documento.ready(inicio);

function inicio() {
    btnPrimera = $("#boton1");
    btnSegunda = $("#boton2");
    frame = $("#frame");
    btnPrimera.click(function(e) {
        console.log("HolaMundo!!");
        frame.attr("src", "../html/sections/balance.html");
    });
    btnSegunda.click(function(e) {
        console.log("HolaMundo!!");
        frame.attr("src", "../html/sections/test.html");
    });

}