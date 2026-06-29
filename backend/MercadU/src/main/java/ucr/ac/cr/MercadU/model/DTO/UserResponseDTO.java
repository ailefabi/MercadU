package ucr.ac.cr.MercadU.model.dto;

import java.util.List;

public class UserResponseDTO {

    private Integer id;
    private String name;
    private String emailUcr;
    private String rol;

    private List<String> businessNames;

    public UserResponseDTO(Integer id, String name, String emailUcr, String rol, List<String> businessNames) {
        this.id = id;
        this.name = name;
        this.emailUcr = emailUcr;
        this.rol = rol;
        this.businessNames = businessNames;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailUcr() {
        return emailUcr;
    }

    public String getRol() {
        return rol;
    }

    public List<String> getBusinessNames() {
        return businessNames;
    }
}
