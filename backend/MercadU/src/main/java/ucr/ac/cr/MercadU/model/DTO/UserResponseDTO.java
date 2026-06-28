package ucr.ac.cr.MercadU.model.dto;

public class UserResponseDTO {

    private Integer id;
    private String name;
    private String emailUcr;
    private String rol;


    public UserResponseDTO(Integer id, String name, String emailUcr, String rol) {
        this.id = id;
        this.name = name;
        this.emailUcr = emailUcr;
        this.rol = rol;
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
}
