package org.cs489.dentalsurgeries.service;

import org.cs489.dentalsurgeries.dto.request.PatientRequest;
import org.cs489.dentalsurgeries.dto.response.PatientResponse;

import java.util.List;

public interface PatientService {

    List<PatientResponse> getAllPatients();
    PatientResponse getPatientById(Long id);
    PatientResponse addPatient(PatientRequest patientRequest);
    PatientResponse updatePatient(PatientRequest patientRequest, Long id);
    void deletePatient(Long id);
    List<PatientResponse> searchPatient(String search);
}
