package ucr.ac.cr.MercadU.model;

public class User {

    private Integer id;
    private String name;
    private String emailUcr;
    private String password;
    private String rol;

    public User() {
    }

    public User(Integer id, String name, String emailUcr, String password, String rol) {
        this.id = id;
        this.name = name;
        this.emailUcr = emailUcr;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailUcr='" + emailUcr + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
