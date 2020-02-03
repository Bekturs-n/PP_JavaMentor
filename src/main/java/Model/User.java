package Model;

public class User {
    private Long id;

    private String name;

    private String suname;

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
        this.suname = suname;
        this.age = age;
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
