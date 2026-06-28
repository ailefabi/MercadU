package ucr.ac.cr.MercadU.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Integer idProduct;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "description")
    private String description;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(name = "price")
    private Integer price;

    @Column(name = "available")
    private boolean available;

    // Relación comentada temporalmente hasta que implementen Business
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_business")
    private Business business;
    */

    public Product() {
    }

    public Product(Integer idProduct, String name, String description, Integer price, boolean available) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
