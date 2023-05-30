package pmr.facturapp.database;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;

import pmr.facturapp.classes.Cliente;
import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Domicilio;
import pmr.facturapp.classes.Empleado;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;
import pmr.facturapp.classes.Venta;
import pmr.facturapp.classes.statics.ProvinciasMunicipios;
import pmr.facturapp.classes.statics.TipoCliente;
import pmr.facturapp.classes.statics.Unidad;
import pmr.facturapp.converters.ClienteConverter;
import pmr.facturapp.converters.CompraConverter;
import pmr.facturapp.converters.EmpleadoConverter;
import pmr.facturapp.converters.ProductoConverter;
import pmr.facturapp.converters.ProveedorConverter;
import pmr.facturapp.converters.VentaConverter;

public class Pruebas {

    private final static String SEPARADOR = "=================================";

    public static void main(String[] args) {
        MongoDBManager manager = MongoDBManager.connect();

        /*
         * Objetos de prueba para las inserciones
         */
        Cliente pCliente = new Cliente(TipoCliente.COOPERATIVA(), "Nombre_Cooperativa_2", "Apellido_Cooperativa",
                new Domicilio("SC de Tenerife", "Tacoronte"), "922 333 111", "cooperativa.ape@dev.facturapp.es");

        Empleado pEmpleado = new Empleado("Administrador", "",
                new Domicilio(ProvinciasMunicipios.getProvincias()[0], ProvinciasMunicipios.getMunicipioSCT()[13]), "", "admin@dev.facturapp.es");

        Producto pProducto = new Producto("Nombre_Producto", "Descripción_Producto", 1.1, 100, Unidad.KILOS());

        Proveedor pProveedor = new Proveedor("Nombre_Proveedor", "Apellido_Proveedor",
                new Domicilio("Las Palmas de Gran Canaria", "Arucas"), "928 789 654", "prov.ape@dev.facturapp.es");

        Compra pCompra = new Compra(pProveedor, List.of(pProducto, pProducto, pProducto), LocalDate.now());

        Venta pVenta = new Venta(pCliente, List.of(pProducto, pProducto, pProducto), pEmpleado, LocalDate.now());


        /*
         * Inserción de objetos Cliente
         */
        // manager.insertCliente(pCliente);

        /*
         * Inserción de objetos Empleado
         */
        manager.insertEmpleado(pEmpleado);

        /*
         * Inserción de objetos Producto
         */
        // manager.insertProducto(pProducto);

        /*
         * Inserción de objetos Proveedor
         */
        // manager.insertProveedor(pProveedor);

        /*
         * Inserción de objetos Compra
         */
        // manager.insertCompra(pCompra);

        /*
         * Inserción de objetos Venta
         */
        // manager.insertVenta(pVenta);

        /*
         * Retorno de todos los clientes registrados
         */
        // System.out.println("=====  CLIENTES  =====");
        // List<Document> clientes = manager.getAllClientes();
        // for (int i = 0; i < clientes.size(); i++) {
        //     Cliente c = ClienteConverter.convert(clientes.get(i));
        //     System.out.println(c);
        // }

        // System.out.println(SEPARADOR);

        /*
         * Retorno de todos los empleado registrados
         */
        
        System.out.println("=====  EMPLEADOS  =====");
        List<Document> empleados = manager.getAllEmpleados();
        for (int i = 0; i < empleados.size(); i++) {
            Empleado e = EmpleadoConverter.convert(empleados.get(i));
            System.out.println(e);
        }

        System.out.println(SEPARADOR);

        /*
         * Retorno de todos los productos registrados
        //  */
        // System.out.println("=====  PRODUCTOS  =====");
        // List<Document> productos = manager.getAllProductos();
        // for (int i = 0; i < productos.size(); i++) {
        //     Producto p = ProductoConverter.convert(productos.get(i));
        //     System.out.println(p);
        // }

        // System.out.println(SEPARADOR);

        /*
         * Retorno de todos los proveedores registrados
         */
        // System.out.println("=====  PROVEEDORES  =====");
        // List<Document> proveedores = manager.getAllProveedores();
        // for (int i = 0; i < proveedores.size(); i++) {
        //     Proveedor pr = ProveedorConverter.convert(proveedores.get(i));
        //     System.out.println(pr);
        // }

        // System.out.println(SEPARADOR);

        /*
         * Retorno de todas las compra registradas
         */
        // System.out.println("=====  COMPRAS  =====");
        // List<Document> compras = manager.getAllCompras();
        // for (int i = 0; i < compras.size(); i++) {
        //     Compra cmp = CompraConverter.convert(compras.get(i));
        //     System.out.println(cmp);
        // }

        // System.out.println(SEPARADOR);

        /*
         * Retorno de todas las ventas registradas
         */
        // System.out.println("=====  VENTAS  =====");
        // List<Document> ventas = manager.getAllVentas();
        // for (int i = 0; i < ventas.size(); i++) {
        //     Venta vn = VentaConverter.convert(ventas.get(i));
        //     System.out.println(vn);
        // }

        // System.out.println(SEPARADOR);
    }
}
