package pmr.facturapp.converters;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Compra;
import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.Proveedor;

public class CompraConverter {
 
    public static Document convert(Compra compra) {
        return new Document("proveedor", ProveedorConverter.convert(compra.getProveedor()))
                .append("productos", ProductoConverter.convertListToDocument(compra.getProductos()))
                .append("fecha", compra.getFecha().toEpochDay());
    }

    public static Compra convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");

        // Recuperamos el documento Compra incluido dentro de la BBDD
        Document docCompra = documento.get("compra", Document.class);

        Proveedor proveedor = ProveedorConverter.convert(docCompra);
        List<Producto> productos = ProductoConverter.convertDocumentToList(docCompra);
        
        LocalDate fecha = LocalDate.ofEpochDay(docCompra.getLong("fecha"));

        return new Compra(id, proveedor, productos, fecha);
    }

}
