package pmr.facturapp.converters;

import org.bson.Document;
import org.bson.types.ObjectId;

import pmr.facturapp.classes.Producto;
import pmr.facturapp.classes.statics.Unidad;

public class ProductoConverter {

    public static Document convert(Producto producto) {
        return new Document("nombre", producto.getNombre())
            .append("descripcion", producto.getDescripcion())
            .append("precio", producto.getPrecio())
            .append("stock", producto.getStock())
            .append("unidad", UnidadConverter.convert(producto.getUnidad()));
    }

    public static Producto convert(Document documento) {
        ObjectId id = documento.getObjectId("_id");

        // Recuperamos el documento Cliente incluido dentro de la BBDD
        Document docProducto = documento.get("producto", Document.class);

        String nombre = docProducto.getString("nombre");
        String descripcion = docProducto.getString("descripcion");

        Double precio = docProducto.getDouble("precio");
        int stock = docProducto.getInteger("stock");
        
        // Transformamos el subDocumento en la clase correspondiente
        Unidad unidad = UnidadConverter.convert(docProducto.get("unidad", Document.class));

        return new Producto(id, nombre, descripcion, precio, stock, unidad);
    }

}
