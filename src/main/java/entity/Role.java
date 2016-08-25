package entity;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

/**
 * Created by swatch on 8/20/16.
 */
@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer id;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "role")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
