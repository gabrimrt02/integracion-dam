package pmr.facturapp.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Compra extends Transaccion {

    /*
     * Properties
     */
    private ObjectProperty<Proveedor> proveedorOP = new SimpleObjectProperty<>();

    /*
     * Contructores
     */
    public Compra() {
        // Constructor Vacio
    }

    public Compra(ObjectId id, Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        super(id, productos, fecha);

        this.proveedorOP.set(proveedor);
    }

    public Compra(Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        super(productos, fecha);

        this.proveedorOP.set(proveedor);
    }

    /*
     * Getters de las properties
     */
    public ObjectProperty<Proveedor> proveedorProperty() {
        return proveedorOP;
    }

    /*
     * Getters de los valores
     */

    /**
     * @return Cliente return the cliente
     */
    public Proveedor getProveedor() {
        return proveedorOP.get();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", getFecha().toString(), getProveedor().getNombreCompleto());
    }

}
