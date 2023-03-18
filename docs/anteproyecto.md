# Anteproyecto

> NOTA: Incluir diagramas donde proceda (diagramas de clases, casos de uso, entidad relación, ...).

## OBJETIVOS

El siguiente proyecto tendrá como objetivo principal aportar una plataforma de gestión de balances y facturación a una PYME dedicada al sector de la venta al por mayor de frutas y verduras en la Comunidad Autónoma de Canarias.

## PREANALISIS DE LO EXISTENTE (Opcional)

La empresa que recibirá la solución de software presenta una trayectoria alargada, siempre ligada a la expansión de la misma, pero descuidando la actualización de las IT.

Actualmente la empresa presenta una sistema de facturación clásico, realizando las facturas a mano, lo que ralentiza el flujo de clientes dentro del local, pudiendo acumular dentro del mismo hasta 5 o más clientes ya despachados, dificultando posibles futuras ventas. Añadido a esto, la gestión de balances de la empresa sigue la misma filosofía, registrando todos los gastos e ingresos a mano y realizando calculos de forma clásica para calcular pérdidas y ganancias.

## ANÁLISIS DEL SOFTWARE

> *Incuir los diagramas necesarios*

*[TODO] CREAR UN DIAGRAMA DE CASOS DE USO.*

El software proporcionará a la empresa un sistema digital de control de balances dentro de la misma, facilitando las labores de introducción de gastos, ingresos y automatizando los calculos de beneficios de la empresa. Por otro lado, aportará un sistema de registro de compras y/o pedidos que agilizará las ventas y reservas dentro del local, lo que favorecerá al flujo de clientes de la organización, permitiendo tener un local más depejado.

Por lo que se refiere al panel de gestión, este deberá ser capaz de recibir datos de ingresos y gastos y alojarlos en una base de datos para poder llevar un registro. También deberá ser capáz de interpretar los datos que introduzca el usuariod y calcular automáticamente el balance general de la empresa en el periodo de tiempo que especifique el usuario.

Por otro lado, el sistema de registro de pedidos y/o compras permitirá a los trabajadores de la empresa registrar las compras de los clientes con todos los datos pertinenetes, así como de la cantidad y tipo de producto. Estos datos también se registrarán dentro de una base de datos, permitiendo llevar un registro de todas las ventas que realiza la empresa.

## DISEÑO DEL SOFTWARE

*[TODO] Propuesta de posibles opciones de implementación del software que hay que construir, determinar cómo se va a llevar a cabo la implementación.*

>  *Incluir los diagramas necesarios.*

*[TODO] CREAR DIAGRAMAS DE CLASES.*

*[TODO] CREAR DIAGRAMAS DE ENTIDAD-RELACIÓN.*



Para la creación del Panel de Gestión, es decir, la aplicación de escritorio, se emplearan diferentes lenguajes , como Java y XML. Las Interfaces Gráficas de Usuario, GUI de ahora en adelante, se realizarán principalmente con JavaFX, aprendido durante el curso. Con relación a los datos generados por la aplicación se prentende usar en lenguaje Java, ya que es el que se ha impartido durante el curso, al igual que para la gestión de la base de datos. Otra de las opciones para gestionar los datos de la base de datos que maneja la aplicación es por medio de la implementación del framework de Hibernate.



Con respecto de la base de datos, la gestión de la misma se plantea usar, principalmente, MongoDB, por la facilidad de uso, la posibilidad de conectar diversos tipos de dispositivos a la misma base de datos y otras características. Para el host de la base de datos se prentende usar MongoDB Atlas, un servicio en la nube proporcionado de manera gratuita por la misma compañía que desarrolla el sistema usado para la base de datos.



Por último, el Sistema de Registro de Compras y/o Pedidos, será desarrollado mediante una aplicación android en lenguaje Java, conectado a la misma base de datos que el Panel de Gestión. Durante el desarrollo de esta parte del sistema se incluirán las librerías que sean necesarias, así como diferentes modificaciones en el planteamiento, como hacer partes del aplicativo en lenguaje Kotlin al ser más eficiente para ciertas tareas en entorno Android u otros cambios necesarios.



## ESTIMACIÓN DE COSTES

*[TODO] Estimar el coste que representará la creación del proyecto. Esta estimación será temporal y/o económica si procede (costes de contratación de servicios en la nube, por ejemplo).*
