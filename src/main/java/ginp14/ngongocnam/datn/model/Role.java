package ginp14.ngongocnam.datn.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role_name")
    @NotBlank(message = "Role name cannot be blank")
    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be empty")
    private String name;

    public Role() {
    }

    public Role(@NotBlank(message = "Role name cannot be blank") @NotNull(message = "Role name cannot be null") @NotEmpty(message = "Role name cannot be empty") String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
