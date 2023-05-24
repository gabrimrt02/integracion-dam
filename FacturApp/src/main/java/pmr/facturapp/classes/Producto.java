package pmr.facturapp.classes;

import org.bson.types.ObjectId;

import pmr.facturapp.classes.statics.Unidad;

public class Producto {

    // Atributos
    private ObjectId id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
    private Unidad unidad;

    // Constructores
    public Producto() {
        // Constructor Vacio
    }

    public Producto(ObjectId id, String nombre, String descripcion, Double precio, int stock, Unidad unidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.unidad = unidad;
    }

    public Producto(String nombre, String descripcion, Double precio, int stock, Unidad unidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.unidad = unidad;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public String toString() {
        return String.format("%s | %.2f | %d %s", getNombre(), getPrecio(), getStock(), getUnidad().getUnidad());
    }

}
