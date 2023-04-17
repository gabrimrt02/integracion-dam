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
        String nombre = documento.getString("nombre");
        String descripcion = documento.getString("descripcion");
        Double precio = documento.getDouble("precio");
        int stock = documento.getInteger("stock");
        Unidad unidad = (Unidad) documento.get("unidad");
        return new Producto(id, nombre, descripcion, precio, stock, unidad);
    }

}
