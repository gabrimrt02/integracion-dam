const lineChart = $('#line');

function createLineChart(nombreCanvas, datos, nombres, nombreGrafica) {
    chartData = {
        labels: nombres,
        datasets: [
            {
                data: datos,
                label: nombreGrafica,
                borderColor: "blue",
                fill: true
            }
        ]
    };

    chartOpciones = {
        responsive: true,
        maintainAspectRatio: false,
        resizeDelay: 0
    };

    cfg = {
        type: 'line',
        data: chartData,
        options: chartOpciones
    };

    new Chart(nombreCanvas, cfg);
}

var datos = [10, 20, 30, 50, 100, 40];
var nombres = ['N1', 'N2', 'N3', 'N4', 'N5', 'N6'];
var nombreG = 'Grafica';

createLineChart(lineChart, datos, nombres, nombreG);

// location.reload();