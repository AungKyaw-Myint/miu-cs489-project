package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.auth.UserRole;
import org.cs489.dentalsurgeries.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private SurgeryRepository surgeryRepository;

    private Patient savedPatient;
    private Dentist savedDentist;
    private Surgery savedSurgery;

    @BeforeEach
    void setUp() {
        Patient patient = new Patient("PAT0001", "Alice", "Smith", "F",
                                                              LocalDate.of(1990, 1, 1));
        patient.setAddress(new Address(101, "Elm St", "City", "ST", "12345"));
        patient.setUsername("alice");
        patient.setPassword("alice123");
        patient.setRole(UserRole.PATIENT);

        savedPatient=patientRepository.save(patient);

        Dentist dentist = new Dentist("Sarah",
                "Johnson",
                "Orthodontics",
                "sarah.johnson@dentalcare.com",
                "555-1234");
        dentist.setUsername("Johnson");
        dentist.setPassword("Johnson123");
        dentist.setRole(UserRole.DENTIST);

        savedDentist = dentistRepository.save(dentist);

        Address address = new Address(101, "Park Avenue", "New York", "NY", "10001");

        Surgery surgery = new Surgery(
                "Smile Dental Clinic",
                "Provides general and cosmetic dental services"
        );
        surgery.setAddressLocation(address);

        savedSurgery = surgeryRepository.save(surgery);
    }

    @Test
    @DisplayName("Save Appointment data")
    void givenAppointment_whenSave_thenReturnAppointment() {
        Appointment appointment = new Appointment("APPT001", "Cleaning", "Dental Cleaning",  LocalDate.now(), null,
                                                  AppointmentStatus.SCHEDULED);
        appointment.setPatient(savedPatient);
        appointment.setDentist(savedDentist);
        appointment.setSurgery(savedSurgery);

        appointmentRepository.save(appointment);

        Optional<Appointment> found = appointmentRepository.findByAppointmentCode("APPT001");

        assertTrue(found.isPresent());
        assertEquals("Cleaning", found.get().getTitle());
    }

    @Test
    @DisplayName("Find active Appointment")
    void given_whenfindByAppointmentStatusNotAndPatient_thenReturnAppointment() {
        Appointment appointment = new Appointment("APPT001", "Cleaning", "Dental Cleaning",  LocalDate.now(), null,
                                                  AppointmentStatus.SCHEDULED);
        appointment.setPatient(savedPatient);
        appointment.setDentist(savedDentist);
        appointment.setSurgery(savedSurgery);
        appointmentRepository.save(appointment);
        List<Appointment> results = appointmentRepository.findByAppointmentStatusNotAndPatient_Id(AppointmentStatus.PAID, savedPatient.getId());

        assertFalse(results.isEmpty());
    }

    @Test
    void findByAppointmentDateBetweenAndAppointmentStatusAndDentist_Id() {
        Appointment appointment = new Appointment("APPT001", "Cleaning", "Dental Cleaning",
                                                  LocalDate.now().plusDays(1), null,
                                                  AppointmentStatus.SCHEDULED);
        appointment.setPatient(savedPatient);
        appointment.setDentist(savedDentist);
        appointment.setSurgery(savedSurgery);
        appointmentRepository.save(appointment);
        List<Appointment> results =
                appointmentRepository.findByAppointmentDateBetweenAndAppointmentStatusAndDentist_Id(LocalDate.now(),
                                                                                                    LocalDate.now().plusDays(7),
                                                                                                    AppointmentStatus.SCHEDULED,
                                                                                                    savedDentist.getId());

        assertFalse(results.isEmpty());
    }
}