package pmr.facturapp.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class Compra {
    
    // Properties
    private ObjectProperty<ObjectId> idOP = new SimpleObjectProperty<>();
    private ObjectProperty<Proveedor> proveedorOP = new SimpleObjectProperty<>();
    private ListProperty<Producto> productosLP = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<LocalDate> fechaOP = new SimpleObjectProperty<>();

    public Compra() {
        // Constructor Vacio
    }

    public Compra(ObjectId id, Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        this.idOP.set(id);
        this.proveedorOP.set(proveedor);
        this.productosLP.addAll(productos);
        this.fechaOP.set(fecha);
    }

    public Compra(Proveedor proveedor, List<Producto> productos, LocalDate fecha) {
        this.proveedorOP.set(proveedor);
        this.productosLP.setAll(productos);
        this.fechaOP.set(fecha);
    }

    public ObjectProperty<ObjectId> idProperty() {
        return idOP;
    }

    public ObjectProperty<Proveedor> proveedorProperty() {
        return proveedorOP;
    }

    public ListProperty<Producto> productosProperty() {
        return productosLP;
    }

    public ObjectProperty<LocalDate> fechaProperty() {
        return fechaOP;
    }

    /**
     * @return ObjectId return the id
     */
    public ObjectId getId() {
        return idOP.get();
    }

    /**
     * @return Cliente return the cliente
     */
    public Proveedor getProveedor() {
        return proveedorOP.get();
    }

    /**
     * @return List<Producto> return the productos
     */
    public List<Producto> getProductos() {
        return productosLP.get();
    }

    /**
     * @return Date return the fecha
     */
    public LocalDate getFecha() {
        return fechaOP.get();
    }

    @Override
    public String toString() {
        return String.format("%s | %s | De: %s %s", getFecha().toString(), getProductos().toString(),
                getProveedor().getNombre(), getProveedor().getApellido());
    }

}
