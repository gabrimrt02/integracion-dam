const chartMes = $('#grafica-mes');
const chartSemana = $('#grafica-semana');

const valoresMes = [500, 205, 425, 600, 200, 90, 1800];
const valoresSemana = [1800, 90, 200, 600, 425, 205, 500];

// var colorMes = "";
// var colorSemana = "";

// if (valoresMes[valoresMes.length - 1] - valoresMes[0] > 0) {
//     colorMes = "#3cba9f";
// } else if (valoresMes[valoresMes.length - 1] - valoresMes[0] < 0) {
//     colorMes = "#ff0000";
// } else {
//     colorMes = "#D3D3D3";
// }

function createGraficaMes(datos, mes) {
    mesData = {
        labels: [1, 5, 10, 15, 20, 25, 30],
        datasets: [{
            data: datos,
            label: mes,
            borderColor:"#" + Math.floor(Math.random()*16777215).toString(16),
            fill: true
        }]
    };

    mesOpciones = {
        responsive: true,
        maintainAspectRatio: false,
        resizeDelay: 0,
        // animation: false
    };

    mesCFG = {
        type: 'line',
        data: mesData,
        options: mesOpciones
    }

    new Chart(chartMes, mesCFG);
}

function createGraficaSemana(datos, nSemana) {
    semanaData = {
        labels: ['L', 'M', 'X', 'J', 'V', 'S', 'D'],
        datasets: [{
            data: datos,
            label: "Semana " + nSemana,
            borderColor:"#" + Math.floor(Math.random()*16777215).toString(16),
            fill: true
        }]
    };

    semanaOpciones = {
        responsive: true,
        maintainAspectRatio: false,
        resizeDelay: 0,
        // animation: false
    };

    semanaCFG = {
        type: 'line',
        data: semanaData,
        options: semanaOpciones
    }

    new Chart(chartSemana, semanaCFG);
}

createGraficaMes(valoresMes, "Junio");
createGraficaSemana(valoresSemana, "50");

function replaceUsuario(nombre) {
    $('#username').text(nombre);
}

// replaceUsuario('Gabriel');

function replaceFecha() {
    const fecha = new Date();

    var dia = fecha.getDate();
    var mes = fecha.getMonth() + 1;
    var anyo = fecha.getFullYear();

    $('#fecha-dia').text(dia + '/' + mes + '/' + anyo);
}

replaceFecha();