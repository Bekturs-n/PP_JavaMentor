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

    @Column(name  = "name")
    private String name;

    @Column (name = "suname")
    private String suname;

    @Column (name = "age")
    private Integer age;

    public User() {
    }

    public User(Long id, String name, String suname, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.suname = suname;
    }

    public User(String name, String suname, Integer age) {
        this.name = name;
        this.age = age;
        this.suname = suname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSuname(String suname) {
        this.suname = suname;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getSuname() {
        return suname;
    }
}
