package Model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String name, String password, Integer age, String role) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public User(Long id, String name, String password, Integer age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public User(String name, String password, Integer age) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        if (age > 0 && age < 100) {
            this.age = age;
        } else {
            this.age = 18;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
