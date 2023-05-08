package pmr.facturapp.classes;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

public class Venta {

    private ObjectId id;
    private Cliente cliente;
    private List<Producto> productos;
    private Empleado empleado;
    private LocalDate fecha;

    public Venta(ObjectId id, Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.empleado = empleado;
        this.fecha = fecha;
    }

    public Venta(Cliente cliente, List<Producto> productos, Empleado empleado, LocalDate fecha) {
        this.cliente = cliente;
        this.productos = productos;
        this.empleado = empleado;
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
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return List<Producto> return the productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * @return Empleado return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @return Date return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | De: %s %s - Atención: %s %s", fecha.toString(), productos.toString(),
                cliente.getNombre(), cliente.getApellido(), empleado.getNombre(), empleado.getApellido());
    }

}
