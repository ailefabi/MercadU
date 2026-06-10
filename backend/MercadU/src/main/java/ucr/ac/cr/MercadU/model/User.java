package ucr.ac.cr.MercadU.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String emailUcr;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Column(name = "rol", nullable = false, length = 20)
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
