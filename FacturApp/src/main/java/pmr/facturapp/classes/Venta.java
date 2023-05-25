package pmr.facturapp.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Venta extends Transaccion {

    // Properties
    private ObjectProperty<Cliente> clienteOP = new SimpleObjectProperty<>();
    private ObjectProperty<Empleado> empleadoOP = new SimpleObjectProperty<>();

    public Venta(ObjectId id, Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        super(id, productos, fecha);

        this.clienteOP.set(cliente);
        this.empleadoOP.set(empleado);
    }

    public Venta(Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        super(productos, fecha);
        
        this.clienteOP.set(cliente);
        this.empleadoOP.set(empleado);
    }


    /*
     * Getters Properties
     */
    public ObjectProperty<Cliente> clienteProperty() {
        return clienteOP;
    }

    public ObjectProperty<Empleado> empleadoProperty() {
        return empleadoOP;
    }

    /*
     * Getters de los valores
     */

    /**
     * @return Cliente return the cliente
     */
    public Cliente getCliente() {
        return clienteOP.get();
    }

    /**
     * @return Empleado return the empleado
     */
    public Empleado getEmpleado() {
        return empleadoOP.get();
    }

    @Override
    public String toString() {
        return String.format("%s | %s | De: %s %s - Atención: %s %s", getFecha().toString(), getProductos().toString(),
                getCliente().getNombre(), getCliente().getApellido(), getEmpleado().getNombre(), getEmpleado().getApellido());
    }

}
