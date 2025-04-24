package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByOrderByLastNameAsc();

    Optional<Patient> findByFirstNameAndLastNameEqualsIgnoreCaseAndBirthDate(String firstName, String lastName,
                                                                            LocalDate birthDate);

    List<Patient> findByFirstNameContainingOrLastNameContainingOrAddress_StreetOrAddress_Zip
            (String firstName,String lastName, String street, String zip);
}
