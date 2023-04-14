const chartName = document.getElementById('line-chart');

const valores = [500, 205, 425, 600, 200, 90, 1800];

var color = "";

if (valores[valores.length - 1] - valores[0] > 0) {
    color = "#3cba9f";
} else if (valores[valores.length - 1] - valores[0] < 0) {
    color = "#ff0000";
} else {
    color = "#D3D3D3";
}

const datos = {
    labels: [1, 5, 10, 15, 20, 25, 30],
    datasets: [
        {
            data: valores,
            label: "Dato 1",
            borderColor: color,
            fill: true
        }]
};

const opciones = {
    responsive: true,
    maintainAspectRatio: true,
    resizeDelay: 0
}

const cfg = {
    type: 'line',
    data: datos,
    options: opciones
};

new Chart(chartName, cfg);