package ucr.ac.cr.MercadU.model.dto;

public class BusinessResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private String category;
    private Integer ownerId;

    public BusinessResponseDTO(Integer id, String name, String description, String category, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Integer getOwnerId() {
        return ownerId;
    }
}
