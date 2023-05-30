package pmr.facturdroid.classes;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.bson.types.ObjectId;

public class Transaccion {

    /*
     * Propertiess
     */
    private ObjectId id;
    private List<Producto> productos;
    private LocalDate fecha;
    private Double total;

    /*
     * Constructores
     */
    public Transaccion() {
        // Constructor vacio
    }

    public Transaccion(ObjectId id, List<Producto> productos, LocalDate fecha) {
        this.id = id;
        this.productos = productos;
        this.fecha = fecha;

        Double auxTotal = 0.0;
        for(Producto p : productos) {
            auxTotal += p.getPrecio();
        }
        this.total = auxTotal;

    }

    public Transaccion(List<Producto> productos, LocalDate fecha) {
        this.productos = productos;
        this.fecha = fecha;

        for(Producto p : productos) {
            this.total += p.getPrecio();
        }
    }

    /*
     * Getters y Setter de los valores
     */

    /**
     * @return Retorna del identificador de la transacción
     */
    public ObjectId getId() {
        return this.id;
    }

    /**
     * @return Retorna la lista de productos de la transacción
     */
    public List<Producto> getProductos() {
        return this.productos;
    }

    /**
     * @return Retorna la fecha en la que se realiza la transacción
     */
    public LocalDate getFecha() {
        return this.fecha;
    }

    /**
     * @return Retornoa el total de la transacción
     */
    public Double getTotal() {
        return this.total;
    }

}
