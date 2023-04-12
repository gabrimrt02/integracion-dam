const chartName = document.getElementById('line-chart');

        const valores = [500, 205, 1321, 1516, 2107,
            2191, 3133, 3221, 4783, 500];

        var color = "";

        if(valores[valores.length - 1] - valores[0] > 0) {
            color = "#3cba9f";
        } else if (valores[valores.length - 1] - valores[0] < 0) {
            color = "#ff0000";
        } else {
            color = "#D3D3D3";
        }

        const datos = {
            labels: [1500, 1600, 1700, 1750, 1800, 1850,
                1900, 1950, 1999, 2050],
            datasets: [
                {
                    data: valores,
                    label: "America",
                    borderColor: color,
                    fill: true
                }],
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