package org.cs489.dentalsurgeries.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cs489.dentalsurgeries.auth.User;

import java.util.List;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@Data
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Admin(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.phone     = phone;
    }
}
