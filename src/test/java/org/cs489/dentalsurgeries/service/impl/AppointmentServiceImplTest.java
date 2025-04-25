package org.cs489.dentalsurgeries.service.impl;

import org.cs489.dentalsurgeries.dto.request.AppointmentRequest;
import org.cs489.dentalsurgeries.dto.response.AppointmentResponse;
import org.cs489.dentalsurgeries.mapper.AppointmentMapper;
import org.cs489.dentalsurgeries.model.Appointment;
import org.cs489.dentalsurgeries.model.Dentist;
import org.cs489.dentalsurgeries.model.Patient;
import org.cs489.dentalsurgeries.model.Surgery;
import org.cs489.dentalsurgeries.repository.AppointmentRepository;
import org.cs489.dentalsurgeries.repository.DentistRepository;
import org.cs489.dentalsurgeries.repository.PatientRepository;
import org.cs489.dentalsurgeries.repository.SurgeryRepository;
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
class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DentistRepository dentistRepository;

    @Mock
    private SurgeryRepository surgeryRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    AppointmentResponse expectedResponse;

    @BeforeEach
    void setUp() {
        expectedResponse = new AppointmentResponse(null,null,null,null,null,null,null,
                                                   null,null,null); // create
    }
    @Test
    void saveAppointment() {
        // Arrange
        AppointmentRequest appointmentRequest = new AppointmentRequest(
                "APP12345", null, null, LocalDate.now(),null,null,null,null,null); // Adjust constructor as necessary
        Patient patient = new Patient(); // create a mock Patient
        Dentist     dentist     = new Dentist(); // create a mock Dentist
        Surgery     surgery     = new Surgery(); // create a mock Surgery
        Appointment appointment = new Appointment(); // create a mock Appointment

        // Mock repository behavior
        Mockito.when(appointmentRepository.findByAppointmentCode(appointmentRequest.appointmentCode()))
               .thenReturn(Optional.empty()); // No appointment found with this code
        Mockito.when(appointmentRepository.findByAppointmentStatusNotAndPatient_Id(Mockito.any(), Mockito.any()))
               .thenReturn(List.of()); // No unpaid appointment exists
        Mockito.when(patientRepository.findById(appointmentRequest.patientId()))
               .thenReturn(Optional.of(patient)); // Patient found
        Mockito.when(dentistRepository.findById(appointmentRequest.dentistId()))
               .thenReturn(Optional.of(dentist)); // Dentist found
        Mockito.when(surgeryRepository.findById(appointmentRequest.surgeryId()))
               .thenReturn(Optional.of(surgery)); // Surgery found
        Mockito.when(appointmentMapper.dtoToAppointment(Mockito.any()))
               .thenReturn(appointment); // Mapper converts DTO to Appointment

        Mockito.when(appointmentRepository.save(Mockito.any())).thenReturn(appointment); // Mock save


        Mockito.when(appointmentMapper.appointmentToDto(Mockito.any()))
               .thenReturn(expectedResponse); // Mapper converts Appointment to DTO

        // Act
        AppointmentResponse result = appointmentService.saveAppointment(appointmentRequest);

        // Assert
        assertEquals(expectedResponse, result);
        Mockito.verify(appointmentRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void givenAppointmentCode_whenGetAppointment_thenSuccess() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentCode("APP12345");

        // Mock repository behavior
        Mockito.when(appointmentRepository.findByAppointmentCode("APP12345"))
               .thenReturn(Optional.of(appointment)); // Appointment found

        // expected
        // response
        Mockito.when(appointmentMapper.appointmentToDto(appointment))
               .thenReturn(expectedResponse); // Mapper converts Appointment to DTO

        // Act
        AppointmentResponse result = appointmentService.getAppointmentByCode("APP12345");

        // Assert
        assertEquals(expectedResponse, result);
        Mockito.verify(appointmentRepository, Mockito.times(1)).findByAppointmentCode("APP12345"); // Ensure repository was called
    }
}