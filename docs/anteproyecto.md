# Anteproyecto

> NOTA: Incluir diagramas donde proceda (diagramas de clases, casos de uso, entidad relación, ...).

## OBJETIVOS

El siguiente proyecto tendrá como objetivo principal aportar una plataforma de gestión de balances y facturación a una PYME dedicada al sector de la venta al por mayor de frutas y verduras en la Comunidad Autónoma de Canarias.

## PREANALISIS DE LO EXISTENTE (Opcional)

La empresa que recibirá la solución de software presenta una trayectoria alargada, siempre ligada a la expansión de la misma, pero descuidando la actualización de las IT. 

Actualmente, la empresa presenta un sistema de facturación clásico, realizando las facturas a mano, lo que ralentiza el flujo de clientes dentro del local, pudiendo acumular dentro del mismo hasta 5 o más clientes ya despachados, dificultando posibles futuras ventas. Añadido a esto, la gestión de balances de la empresa sigue la misma filosofía, registrando todos los gastos e ingresos a mano y efectuando cálculos de forma clásica para calcular pérdidas y ganancias.

## ANÁLISIS DEL SOFTWARE

> *Incuir los diagramas necesarios*

*[TODO] CREAR UN DIAGRAMA DE CASOS DE USO.*

El software proporcionará a la empresa un sistema digital de control de balances dentro de la misma, facilitando las labores de introducción de gastos, ingresos y automatizando los cálculos de beneficios de la empresa. Por otro lado, aportará un sistema de registro de compras y/o pedidos que agilizará las ventas y reservas dentro del local, lo que favorecerá al flujo de clientes de la organización, permitiendo tener un local más despejado.
Por lo que se refiere al panel de gestión, este deberá ser capaz de recibir datos de ingresos y gastos y alojarlos en una base de datos para poder llevar un registro. También deberá ser capaz de interpretar los datos que introduzca el usuario y calcular automáticamente el balance general de la empresa en el periodo de tiempo que especifique el usuario.
Por otro lado, el sistema de registro de pedidos y/o compras permitirá a los trabajadores de la empresa registrar las compras de los clientes con todos los datos pertinentes, así como de la cantidad y tipo de producto. Estos datos también se registrarán dentro de una base de datos, permitiendo llevar un registro de todas las ventas que realiza la empresa.

## DISEÑO DEL SOFTWARE

*[TODO] Propuesta de posibles opciones de implementación del software que hay que construir, determinar cómo se va a llevar a cabo la implementación.*

>  *Incluir los diagramas necesarios.*

*[TODO] CREAR DIAGRAMAS DE CLASES.*

*[TODO] CREAR DIAGRAMAS DE ENTIDAD-RELACIÓN.*

Para la creación del Panel de Gestión, es decir, la aplicación de escritorio, se emplearán diferentes lenguajes, como Java y XML. Las Interfaces Gráficas de Usuario, GUI de ahora en adelante, se realizarán principalmente con Java FX, aprendido durante el curso. Con relación a los datos generados por la aplicación, se pretende usar en lenguaje Java, ya que es el que se ha impartido durante el curso, al igual que para la gestión de la base de datos. Otra de las opciones para gestionar los datos de la base de datos que maneja la aplicación es por medio de la implementación del framework de Hibernate.

Con respecto de la base de datos, la gestión de la misma se plantea utilizar, principalmente, MongoDB, por la facilidad de uso, la posibilidad de conectar diversos tipos de dispositivos a la misma base de datos y otras características. Para el host de la base de datos se pretende emplear MongoDB Atlas, un servicio en la nube proporcionado de manera gratuita por la misma compañía que desarrolla el sistema usado para la base de datos.

Por último, el Sistema de Registro de Compras y/o Pedidos, será desarrollado mediante una aplicación Android en lenguaje Java, conectado a la misma base de datos que el Panel de Gestión. Durante el desarrollo de esta parte del sistema se incluirán las librerías que sean necesarias, así como diferentes modificaciones en el planteamiento, como hacer partes del aplicativo en lenguaje Kotlin al ser más eficiente para ciertas tareas en entorno Android u otros cambios necesarios.

## ESTIMACIÓN DE COSTES

El coste de ente proyecto, es principalmente temporal, ya que todos los elementos que se pretenden usar para la realización del mismo son gratuitos o de código abierto, lo que nos permite ahorrarnos una gran cantidad de dinero al no usar soluciones propietarias.

Para poder calcular el tiempo que requerirá este proyecto, primero deberemos saber que es todo lo que lo compone. La realización de diagramas, es la primera de las etapas del proyecto y una de las más importantes, teniendo en cuenta la cantidad de diagramas que hay que hacer y los diferentes aspectos a tener en cuenta como posibles modificaciones o cambios en la estructura, se estima que esta parte podría consumir entre 6 y 8 horas del proyecto.

Terminada la planificación del proyecto, damos paso a la producción, es en este momento donde tendremos que desarrollar ambos aplicativos e implementar la base de datos. Para todo ello tendremos que destinar tiempo para el diseño, el desarrollo y también destinar algo de tiempo para la gestión de posibles fallos que puedan surgir a lo largo del desarrollo de los sistemas. Tiendo en cuenta lo anterior, el desarrollo de Panel de Gestión se estima en 40 horas más un añadido de 10 horas más para la gestión de posibles fallos. El Sistema de Gestión de Compras se estima en torno a las 50 horas, con un añadido de 15 horas para la gestión de fallos procedentes del desarrollo. Por último, la base de datos que acompañará a ambos aplicativos, se estima que su desarrollo podría consumir entre 10 y 15 horas del tiempo total del proyecto, a lo que se tendría que añadir algunas horas para la gestión de problemas y fallos generados por el desarrollo de las aplicaciones o el mal desarrollo de la base de datos.

Otro aspecto que deberemos de tener en cuenta a la hora de desarrollar el proyecto es el tiempo dedicado a las pruebas del mismo. Las pruebas se irán realizando a lo largo del proyecto, por lo que dependiendo de las implementaciones a las que se les vaya a realizar testing, tendremos que realizar más o menos horas de pruebas. En términos generales, el tiempo total destinado a las pruebas no será inferior a las 20 horas para poder comprobar que todos los productos desarrollados funcionan de forma adecuada y sin problemas.

En resumen, el costo del proyecto solamente será temporal que, sumando todas las partes del proyecto, tendrá una estimación de unas 160 horas aproximadamente, o lo que es lo mismo, unos 20 días de trabajo a jornada completa.
