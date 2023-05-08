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
        Document productos = new Document();
        for(int i = 0; i < compra.getProductos().size(); i++) {
            productos.append("producto_" + i, ProductoConverter.convert(compra.getProductos().get(i)));
        }

        return new Document("proveedor", ProveedorConverter.convert(compra.getProveedor()))
                .append("productos", productos)
                .append("fecha", compra.getFecha().toEpochDay());
    }

    public static Compra convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");

        // Recuperamos el documento Compra incluido dentro de la BBDD
        Document docCompra = documento.get("compra", Document.class);

        Proveedor proveedor = ProveedorConverter.convert(docCompra);
        List<Producto> productos = docCompra.getList("productos", Producto.class);
        
        /*
         * Prueba
         */
    
        // Document productos = docCompra.get("productos", Document.class);

         /*
          * Fin Prueba
          */
        LocalDate fecha = LocalDate.ofEpochDay(docCompra.getLong("fecha"));

        return new Compra(id, proveedor, productos, fecha);
    }

}
