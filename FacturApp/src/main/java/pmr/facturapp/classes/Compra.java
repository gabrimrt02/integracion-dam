package pmr.facturapp.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

public class Compra {
    
    private ObjectId id;
    private Proveedor proveedor;
    private List<Producto> productos;
    private LocalDate fecha;

    public Compra() {
        // Constructor Vacio
    }

    public Compra(ObjectId id, Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        this.id = id;
        this.proveedor = proveedor;
        this.productos = productos;
        this.fecha = fecha;
    }

    public Compra(Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        this.proveedor = proveedor;
        this.productos = productos;
        this.fecha = fecha;
    }

    /**
     * @return ObjectId return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @return Cliente return the cliente
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @return List<Producto> return the productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * @return Date return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | De: %s %s", fecha.toString(), productos.toString(),
                proveedor.getNombre(), proveedor.getApellido());
    }

}
