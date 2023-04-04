// TODO Implementar los Scripts interactivos de la página principal
var documento = $(document);
var btnPrimera;
documento.ready(inicio);

function inicio() {
    btnPrimera = document.querySelector("#boton1");    
    btnPrimera.click(function() {
        $("#frame").load('../html/sections/balance.html')
    });
}