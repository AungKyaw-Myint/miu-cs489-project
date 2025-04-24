package org.cs489.dentalsurgeries.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long id;

    @Column(nullable = false)
    private int no;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zip;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Patient patient;

    @OneToOne(mappedBy = "addressLocation")
    private Surgery surgery;

    public Address(int no, String street, String city, String state, String zip) {
        this.no     = no;
        this.street = street;
        this.city   = city;
        this.state  = state;
        this.zip    = zip;
    }
}
