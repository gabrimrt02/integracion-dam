package pmr.facturapp.classes;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.bson.types.ObjectId;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class Transaccion {

    /*
     * Propertiess
     */
    private ObjectProperty<ObjectId> idOP = new SimpleObjectProperty<>();
    private ListProperty<Producto> productosLP = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<LocalDate> fechaOP = new SimpleObjectProperty<>();
    private DoubleProperty totalDP = new SimpleDoubleProperty();

    /*
     * Constructores
     */
    public Transaccion() {
        // Constructor vacio
    }

    public Transaccion(ObjectId id, List<Producto> productos, LocalDate fecha) {
        this.idOP.set(id);
        this.productosLP.setAll(productos);
        this.fechaOP.set(fecha);
        
        Double auxTotal = 0.0;
        for(Producto p : productos) {
            auxTotal += p.getPrecio();
        }
        this.totalDP.set(Double.parseDouble(NumberFormat.getNumberInstance(Locale.US).format(auxTotal)));

    }

    public Transaccion(List<Producto> productos, LocalDate fecha) {
        this.productosLP.setAll(productos);
        this.fechaOP.set(fecha);

        for(Producto p : productos) {
            this.totalDP.add(p.getPrecio());
        }
    }

    /*
     * Getters Properties
     */
    public ObjectProperty<ObjectId> idProperty() {
        return this.idOP;
    }

    public ListProperty<Producto> productosProperty() {
        return this.productosLP;
    }

    public ObjectProperty<LocalDate> fechaProperty() {
        return this.fechaOP;
    }

    public DoubleProperty totalProperty() {
        return this.totalDP;
    }

    /*
     * Getters y Setter de los valores
     */

    /**
     * @return Retorna del identificador de la transacción
     */
    public ObjectId getId() {
        return this.idOP.get();
    }

    /**
     * @return Retorna la lista de productos de la transacción
     */
    public List<Producto> getProductos() {
        return this.productosLP.get();
    }

    /**
     * @return Retorna la fecha en la que se realiza la transacción
     */
    public LocalDate getFecha() {
        return this.fechaOP.get();
    }

    /**
     * @return Retornoa el total de la transacción
     */
    public Double getTotal() {
        return this.totalDP.get();
    }

}
