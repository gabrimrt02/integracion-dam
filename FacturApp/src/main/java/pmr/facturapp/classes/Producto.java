package pmr.facturapp.classes;

import org.bson.types.ObjectId;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pmr.facturapp.classes.statics.Unidad;

public class Producto {

    // Properties
    private ObjectProperty<ObjectId> idOP = new SimpleObjectProperty<>();
    private StringProperty nombreSP = new SimpleStringProperty();
    private StringProperty descripcionSP = new SimpleStringProperty();
    private DoubleProperty precioDP = new SimpleDoubleProperty();
    private IntegerProperty stockIP = new SimpleIntegerProperty();
    private ObjectProperty<Unidad> unidadOP = new SimpleObjectProperty<>(); 

    // Constructores
    public Producto() {
        // Constructor Vacio
    }

    public Producto(ObjectId id, String nombre, String descripcion, Double precio, int stock, Unidad unidad) {
        this.idOP.set(id);
        this.nombreSP.set(nombre);
        this.descripcionSP.set(descripcion);
        this.precioDP.set(precio);
        this.stockIP.set(stock);
        this.unidadOP.set(unidad);
    }

    public Producto(String nombre, String descripcion, Double precio, int stock, Unidad unidad) {
        this.nombreSP.set(nombre);
        this.descripcionSP.set(descripcion);
        this.precioDP.set(precio);
        this.stockIP.set(stock);
        this.unidadOP.set(unidad);
    }

    public ObjectProperty<ObjectId> idProperty() {
        return idOP;
    }

    public StringProperty nombreProperty() {
        return nombreSP;
    }

    public StringProperty descripcionProperty() {
        return descripcionSP;
    }

    public DoubleProperty precioProperty() {
        return precioDP;
    }

    public IntegerProperty stockProperty() {
        return stockIP;
    }

    public ObjectProperty<Unidad> unidadProperty() {
        return unidadOP;
    }

    // Getters y Setters
    public ObjectId getId() {
        return idOP.get();
    }

    public String getNombre() {
        return nombreSP.get();
    }

    public void setNombre(String nombre) {
        this.nombreSP.set(nombre);
    }

    public String getDescripcion() {
        return descripcionSP.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcionSP.set(descripcion);
    }

    public Double getPrecio() {
        return precioDP.get();
    }

    public void setPrecio(Double precio) {
        this.precioDP.set(precio);
    }

    public int getStock() {
        return stockIP.get();
    }

    public void setStock(int stock) {
        this.stockIP.set(stock);
    }

    public Unidad getUnidad() {
        return unidadOP.get();
    }

    public void setUnidad(Unidad unidad) {
        this.unidadOP.set(unidad);
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f)", getNombre(), getPrecio());
    }

}
