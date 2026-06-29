package ucr.ac.cr.MercadU.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    //Relacion entre tablas**********
    //Muchos negocios para un usuario (dueño)
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_business_user"))
    private User owner;

    //Un negocio tiene muchos productos
    @OneToMany(mappedBy = "business")
    @JsonIgnore
    private List<Product> listProduct;

    //Un negocio tiene muchas reseñas
    @OneToMany(mappedBy = "business")
    @JsonIgnore
    private List<Review> listReview;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public List<Review> getListReview() {
        return listReview;
    }

    public void setListReview(List<Review> listReview) {
        this.listReview = listReview;
    }
    //*****************************

    public Business() {
    }

    public Business(Integer id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
