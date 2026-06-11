package ucr.ac.cr.MercadU.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion")
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(name = "precio")
    private int precio;

    @Column(name = "disponible")
    private boolean disponible;

    // Relación comentada temporalmente hasta que implementen Business
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_business")
    private Business business;
    */

    public Product() {
    }

    public Product(boolean disponible, int precio, String descripcion, String nombre) {
        this.disponible = disponible;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
