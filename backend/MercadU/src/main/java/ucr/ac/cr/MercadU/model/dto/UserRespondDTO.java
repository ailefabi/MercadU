package ucr.ac.cr.MercadU.model.dto;

public class UserRespondDTO {

    private Integer id;
    private String name;
    private String emailUcr;
    private String rol;

    public UserRespondDTO() {
    }

    public UserRespondDTO(Integer id, String name, String emailUcr, String rol) {
        this.id = id;
        this.name = name;
        this.emailUcr = emailUcr;
        this.rol = rol;
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

    public String getEmailUcr() {
        return emailUcr;
    }

    public void setEmailUcr(String emailUcr) {
        this.emailUcr = emailUcr;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
