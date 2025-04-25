package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.auth.UserRole;
import org.cs489.dentalsurgeries.model.Address;
import org.cs489.dentalsurgeries.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PatientRepositoryTest {

    @Autowired private PatientRepository patientRepository;

    @Autowired private TestEntityManager entityManager;

    private Patient patient;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setNo(101);
        address.setStreet("Main Street");
        address.setCity("Springfield");
        address.setState("IL");
        address.setZip("62701");

        patient = new Patient();
        patient.setPatientNo("PAT12345");
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setGender("M");
        patient.setBirthDate(LocalDate.of(1990, 1, 1));
        patient.setAddress(address);
        patient.setUsername("john1");
        patient.setPassword("john123");
        patient.setRole(UserRole.PATIENT);
    }

    @Test
    @DisplayName("Save Patient data")
    void givenPatient_whenSave_thenReturnPatient() {
        Patient savedPatient=patientRepository.saveAndFlush(patient);

        //then
        assertNotNull(savedPatient);
        assertEquals("John",savedPatient.getFirstName());
        assertEquals("Doe",savedPatient.getLastName());
    }

    @Test
    @DisplayName("Patient List by order")
    void given_whenFindOrderByLastName_thenReturnPatients() {
        Address address2 = new Address();
        address2.setNo(108);
        address2.setStreet("Main 1 Street");
        address2.setCity("Springfield 1");
        address2.setState("IL");
        address2.setZip("62702");

        Patient p2 = new Patient();
        p2.setPatientNo("PAT12348");
        p2.setFirstName("Bob");
        p2.setLastName("Anderson");
        p2.setGender("M");
        p2.setBirthDate(LocalDate.of(1990, 2, 2));
        p2.setAddress(address2);
        p2.setUsername("bob1");
        p2.setPassword("bob123");
        p2.setRole(UserRole.PATIENT);

        patientRepository.saveAndFlush(patient);
        patientRepository.saveAndFlush(p2);

//        patientRepository.saveAllAndFlush(List.of(patient, p2));

        List<Patient> sortedPatients = patientRepository.findAllByOrderByLastNameAsc();

        assertEquals(2, sortedPatients.size());
        assertEquals("Anderson", sortedPatients.get(0).getLastName());
        assertEquals("Doe", sortedPatients.get(1).getLastName());

    }

    @Test
    @DisplayName("Find by data")
    void givenData_whenFindBy_thenReturnOptional() {
        patientRepository.saveAndFlush(patient);
        Optional<Patient> patient = patientRepository.findByFirstNameAndLastNameEqualsIgnoreCaseAndBirthDate(
                "John", "Doe", LocalDate.of(1990, 1, 1));


        assertNotNull(patient);
        assertEquals("John",patient.get().getFirstName());
        assertEquals("Doe",patient.get().getLastName());
    }

    @Test
    @DisplayName("Search by data")
    void givenData_whenSearchBy_thenReturnOptional() {
        patientRepository.save(patient);

        List<Patient> results = patientRepository.findByFirstNameContainingOrLastNameContainingOrAddress_StreetOrAddress_Zip(
                null, null, null, "62701");

        assertFalse(results.isEmpty());
    }
}