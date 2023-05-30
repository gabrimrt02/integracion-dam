# Pruebas

### Detección de fallos:

Durante el desarrollo del proyecto, se identificaron los siguientes fallos notables:

1. Errores en la lógica de las consultas de datos comparativos: Al comenzar con las pruebas manuales realizadas a este tipo de funciones de consulta, se detectó una inconsistencia en el orden de devolución de los datos. Se realizaron diversos ajustes en las funciones para poder recuperar los datos esperados.

2. Errores en la visualización de los datos en las gráficas de la interfaz: Relacionado con el error anterior, los datos no se montraban de forma adecuada dentro de la interfaz gráfica, por lo que se modificáron el tipo de gráficas en las que se mostraban y se realizaron pruebas para poder verificar que el problema estaba completamente solucinado.

3. Errores de sincronización en la primera versión del proyecto: Durante las primeras etápas del desarrollo del proyecto se producieron una serie de errores de sincronización y conexión entre el *front-end* y el *back-end* del programa, lo que impidió continuar con esa vía de desarrollo, obligando a realizar un cambio significativo en la lógica de negocio del programa y en el modo de desarrollo.

### Planes de pruebas:

* Pruebas manuales: Durante el desarrollo se realizaron pruebas periódicas que comprobaban en correcto funcionamiento de los diferentes aspectos de la aplicación, así como de sus componentes y partes cruciales de la lógica, antes de proceder a la realización del resto de pruebas.

* Pruebas de integración: Al finalizar partes importantes del desarrollo se realizaron pequeñas pruebas de integración para comprobar el funcionamiento de los diferentes componentes y procesos en funcionamiento conjunto. Se recopilaron los erroes producidos, se solucionaron las inconsistencias de datos y componentes y volvieron a realizar pruebas para comprobar su correcto funcionamiento.

* Pruebas de simulación: Se realizarán pruebas en un entorno controlado con datos que simulen ser reales para comprobar el funcionamiento de la aplicación y el comportamiento del sistema en un entorno casi real. Se realizará una recopilación de todos los datos aportados por el sistema y se procederá a solucionar, por orden de prioridades, los errores que se produzcan durante el funcionamiento del mismo.
