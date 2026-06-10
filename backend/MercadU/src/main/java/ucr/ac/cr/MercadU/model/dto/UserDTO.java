package ucr.ac.cr.MercadU.model.dto;

public class UserDTO {

    private Integer id;
    private String name;
    private String emailUcr;


    public UserDTO(Integer id, String name, String emailUcr) {
        this.id = id;
        this.name = name;
        this.emailUcr = emailUcr;
    }

    public UserDTO() {

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
}
