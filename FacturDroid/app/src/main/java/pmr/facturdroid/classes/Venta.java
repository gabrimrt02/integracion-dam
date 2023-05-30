package pmr.facturdroid.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

public class Venta extends Transaccion {

    // Properties
    private Cliente cliente;
    private Empleado empleado;

    public Venta(ObjectId id, Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        super(id, productos, fecha);

        this.cliente = cliente;
        this.empleado = empleado;
    }

    public Venta(Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        super(productos, fecha);

        this.cliente = cliente;
        this.empleado = empleado;
    }

    /*
     * Getters de los valores
     */

    /**
     * @return Cliente return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return Empleado return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | De: %s %s - Atención: %s %s", getFecha().toString(), getProductos().toString(),
                getCliente().getNombre(), getCliente().getApellido(), getEmpleado().getNombre(), getEmpleado().getApellido());
    }

}
