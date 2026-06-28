package ucr.ac.cr.MercadU.model.dto;

public class BusinessResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private String category;

    public BusinessResponseDTO(Integer id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
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
}
