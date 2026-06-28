package ucr.ac.cr.MercadU.model.dto;

public class ProductResponseDTO {
    private Integer idProduct;
    private String name;
    private String description;
    private Integer price;
    private boolean available;

    public ProductResponseDTO(Integer idProduct, String name, String description, int price, boolean available) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
}
