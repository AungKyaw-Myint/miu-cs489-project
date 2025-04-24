package org.cs489.dentalsurgeries.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "surgery")
@NoArgsConstructor
@Data
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "surgery_id")
    private Long id;

    private String surgeryName;
    private String surgeryDescription;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address addressLocation;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments;

    public Surgery(String surgeryName,String surgeryDescription) {
        this.surgeryName        = surgeryName;
        this.surgeryDescription = surgeryDescription;
    }
}
