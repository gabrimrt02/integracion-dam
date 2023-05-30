package pmr.facturdroid.converters;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturdroid.classes.Cliente;
import pmr.facturdroid.classes.Empleado;
import pmr.facturdroid.classes.Producto;
import pmr.facturdroid.classes.Venta;

public class VentaConverter {

    public static Document convert(Venta venta) {
        return new Document("cliente", ClienteConverter.convert(venta.getCliente()))
                .append("productos", ProductoConverter.convertListToDocument(venta.getProductos()))
                .append("empleado", EmpleadoConverter.convert(venta.getEmpleado()))
                .append("fecha", venta.getFecha().toEpochDay());
    }

    public static Venta convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");

        // Recuperamos el subdocumento venta incluido dentro de la BBDD
        Document docVenta = documento.get("venta", Document.class);

        Cliente cliente = ClienteConverter.convert(docVenta);
        List<Producto> productos = ProductoConverter.convertDocumentToList(docVenta);
        Empleado empleado = EmpleadoConverter.convert(docVenta);

        LocalDate fecha = LocalDate.ofEpochDay(docVenta.getLong("fecha"));

        return new Venta(id, cliente, productos, empleado, fecha);
    }

}
