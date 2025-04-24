package org.cs489.dentalsurgeries.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cs489.dentalsurgeries.auth.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@Data
@DiscriminatorValue("PATIENT")
public class Patient extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Long id;
    @Column(name = "patient_no", unique = true, nullable = false)
    private String patientNo;
    private String firstName;
    private String lastName;
    private String    gender;
    private LocalDate birthDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient(String patientNo, String firstName, String lastName, String gender, LocalDate birthDate) {
        this.patientNo = patientNo;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.gender    = gender;
        this.birthDate = birthDate;
    }
}
