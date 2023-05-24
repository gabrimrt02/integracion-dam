package pmr.facturapp.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class Venta {

    // Properties
    private ObjectProperty<ObjectId> idOP = new SimpleObjectProperty<>();
    private ObjectProperty<Cliente> clienteOP = new SimpleObjectProperty<>();
    private ListProperty<Producto> productosLP = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<Empleado> empleadoOP = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> fechaOP = new SimpleObjectProperty<>();

    public Venta(ObjectId id, Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        this.idOP.set(id);
        this.clienteOP.set(cliente);
        this.productosLP.addAll(productos);
        this.empleadoOP.set(empleado);
        this.fechaOP.set(fecha);
    }

    public Venta(Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        this.clienteOP.set(cliente);
        this.productosLP.addAll(productos);
        this.empleadoOP.set(empleado);
        this.fechaOP.set(fecha);
    }

    public ObjectProperty<ObjectId> idProperty() {
        return idOP;
    }

    public ObjectProperty<Cliente> clienteProperty() {
        return clienteOP;
    }

    public ListProperty<Producto> productosProperty() {
        return productosLP;
    }

    public ObjectProperty<Empleado> empleadoProperty() {
        return empleadoOP;
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
    public Cliente getCliente() {
        return clienteOP.get();
    }

    /**
     * @return List<Producto> return the productos
     */
    public List<Producto> getProductos() {
        return productosLP.get();
    }

    /**
     * @return Empleado return the empleado
     */
    public Empleado getEmpleado() {
        return empleadoOP.get();
    }

    /**
     * @return Date return the fecha
     */
    public LocalDate getFecha() {
        return fechaOP.get();
    }

    @Override
    public String toString() {
        return String.format("%s | %s | De: %s %s - Atención: %s %s", getFecha().toString(), getProductos().toString(),
                getCliente().getNombre(), getCliente().getApellido(), getEmpleado().getNombre(), getEmpleado().getApellido());
    }

}
