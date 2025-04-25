package org.cs489.dentalsurgeries.service.impl;

import org.cs489.dentalsurgeries.auth.UserRole;
import org.cs489.dentalsurgeries.dto.response.AddressResponse;
import org.cs489.dentalsurgeries.dto.response.PatientResponse;
import org.cs489.dentalsurgeries.exception.user.DataNotFound;
import org.cs489.dentalsurgeries.mapper.PatientMapper;
import org.cs489.dentalsurgeries.model.Address;
import org.cs489.dentalsurgeries.model.Patient;
import org.cs489.dentalsurgeries.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;
    private Address address;
    private PatientResponse patientResponse;

    @Mock
    private PatientMapper   patientMapper;

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

        patientResponse = new PatientResponse(
                patient.getId(),
                patient.getPatientNo(), patient.getFirstName(), patient.getLastName(),
                patient.getGender(),
                patient.getBirthDate(),
                new AddressResponse(null,101,null,null,null,null));
    }

    @Test
    void given_whenFindAllPatient_thenReturnPatientList() {
        Mockito.when(patientRepository.findAllByOrderByLastNameAsc()).thenReturn(List.of(patient));
        Mockito.when(patientMapper.patientToDtoList(List.of(patient))).thenReturn(List.of(patientResponse));
        patientService.getAllPatients();

        Mockito.verify(patientRepository, Mockito.times(1)).findAllByOrderByLastNameAsc();

    }

    @Test
    void givenId_whenFindPatient_thenThrowError() {
        Long id = 1L;

        Mockito.when(patientRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(DataNotFound.class, () -> {
            patientService.getPatientById(id);
        });
    }

    @Test
    void givenName_whenFindPatient_thenPatientList() {
        String searchTerm = "Doe";

        // Mocking the repository to return a list of patients based on search term
        List<Patient> patients = List.of(patient); // assuming 'patient' is already set up in @BeforeEach
        Mockito.when(patientRepository.findByFirstNameContainingOrLastNameContainingOrAddress_StreetOrAddress_Zip(
                searchTerm, searchTerm, searchTerm, searchTerm)).thenReturn(patients);

        // Mocking the mapper to return a list of PatientResponse
        List<PatientResponse> patientResponses = List.of(patientResponse); // Assuming this is a valid response
        Mockito.when(patientMapper.patientToDtoList(patients)).thenReturn(patientResponses);

        // Calling the service method
        List<PatientResponse> result = patientService.searchPatient(searchTerm);

        // Verifying results
        assertEquals(1, result.size());  // Assuming you expect one result in this test
        Mockito.verify(patientRepository, Mockito.times(1))
               .findByFirstNameContainingOrLastNameContainingOrAddress_StreetOrAddress_Zip(searchTerm, searchTerm, searchTerm, searchTerm);
        Mockito.verify(patientMapper, Mockito.times(1)).patientToDtoList(patients);
    }
}