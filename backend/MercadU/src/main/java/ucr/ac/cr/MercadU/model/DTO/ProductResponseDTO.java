package ucr.ac.cr.MercadU.model.dto;

public class ProductResponseDTO {
    private Integer idProduct;
    private String name;
    private String description;
    private Integer price;
    private boolean available;

    private Integer businessId;
    private String businessName;
    private String businessOwnerName;

    public ProductResponseDTO(Integer idProduct, String name, String description, Integer price, boolean available, Integer businessId, String businessName, String businessOwnerName) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessOwnerName = businessOwnerName;
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

    public Integer getBusinessId() {
        return businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessOwnerName() {
        return businessOwnerName;
    }
}
