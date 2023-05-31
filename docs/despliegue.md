# Instalación/Despliegue
En esta fase del proyecto, nos centraremos en preparar el  programa para su futura distribución para que los usuarios finales puedan usarlo. Se preparará un instalador que facilite la instalación y configuración del programa.

### Instalador o Paquete de Instalación:
Para poder instalar el programa de una forma más sencilla, se creará un instalador específico para la plataforma de Windows, que será la única para la que estará disponible el programa.

El instalador será un archivo ejecutable (.exe) que nos permitirá realizar una instalación más sencilla. Este guiará al usuario durante todo el proceso de instalación y permitirá que realize la configuración adecuada del programa.

Para poder crear el instalador primero usaremos un plugin de Maven ([JavaPackager](https://github.com/fvarrui/JavaPackager)) para poder realizar la compilación. Posteriormente, usaremos un programa del estilo de [JWrapper](https://www.jwrapper.com/getting-started) o [Launch4j](https://sourceforge.net/projects/launch4j/) para la creación del instalador final.

### Pasos generales del proceso de instalación:
1. Descargar el instalador desde el apartado *Releases* del repositorio.
2. Seguir las instrucciones de las que informa el instalador.
3. Al finalizar la instalación ya se dispondrá de la aplicación para su uso completo.
