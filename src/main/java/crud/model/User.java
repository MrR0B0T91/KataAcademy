package crud.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static List<User> createList() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "Zaur", "Tregulov", "Email"));
        list.add(new User(2L, "Maria", "Ivanova", "MariaEmail"));
        list.add(new User(3L, "Mark", "Zukerberg", "ZukerEmail"));
        list.add(new User(4L, "Neil", "Alishev", "AlishevEmail"));
        list.add(new User(5L, "Max", "Payne", "PayneEmail"));

        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
