package pmr.facturdroid.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;


public class Compra extends Transaccion {

    /*
     * Properties
     */
    private Proveedor proveedor;

    /*
     * Contructores
     */
    public Compra() {
        // Constructor Vacio
    }

    public Compra(ObjectId id, Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        super(id, productos, fecha);

        this.proveedor = proveedor;
    }

    public Compra(Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        super(productos, fecha);

        this.proveedor = proveedor;
    }

    /*
     * Getters de los valores
     */

    /**
     * @return Cliente return the cliente
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s", getFecha().toString(), getProveedor().getNombreCompleto(),
                getProductos().toString());
    }

}
