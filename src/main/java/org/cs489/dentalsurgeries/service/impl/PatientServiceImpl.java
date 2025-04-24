package org.cs489.dentalsurgeries.service.impl;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.auth.service.impl.AuthenticationService;
import org.cs489.dentalsurgeries.dto.request.PatientRequest;
import org.cs489.dentalsurgeries.dto.response.PatientResponse;
import org.cs489.dentalsurgeries.exception.user.DataNotFound;
import org.cs489.dentalsurgeries.exception.user.DuplicateDataException;
import org.cs489.dentalsurgeries.mapper.PatientMapper;
import org.cs489.dentalsurgeries.model.Patient;
import org.cs489.dentalsurgeries.repository.PatientRepository;
import org.cs489.dentalsurgeries.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper     patientMapper;
    private final AuthenticationService authenticationService;

    @Override
    public List<PatientResponse> getAllPatients() {
        List<Patient> patients = patientRepository.findAllByOrderByLastNameAsc();

        return patientMapper.patientToDtoList(patients);
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            return patientMapper.patientToDto(patient.get());
        }

        throw new DataNotFound("Patient not found");
    }

    @Override
    public PatientResponse addPatient(PatientRequest patientRequest) {
        Optional<Patient> foundPatient=
                patientRepository.findByFirstNameAndLastNameEqualsIgnoreCaseAndBirthDate(patientRequest.firstName(),
                                                                                   patientRequest.lastName(),
                                                                                   patientRequest.birthDate());
        if(foundPatient.isPresent()){
            throw new DuplicateDataException("Patient already exists");
        }


        Patient requestData=patientMapper.dtoToPatient(patientRequest);
//        requestData.setUser(authenticationService.getUser());

        Patient patient=patientRepository.save(requestData);
        return patientMapper.patientToDto(patient);
    }

    @Override
    public PatientResponse updatePatient(PatientRequest patientRequest, Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {

            Patient updatedPatient = patientMapper.dtoToPatient(patientRequest);
            updatedPatient.setId(patient.get().getId());
            if(patient.get().getAddress() != null){
                updatedPatient.getAddress().setId(patient.get().getAddress().getId());
            }

            Patient savedPatient=patientRepository.save(updatedPatient);

            return patientMapper.patientToDto(savedPatient);
        }

        throw new DataNotFound("Patient not found");
    }

    @Override
    public void deletePatient(Long id) {
        Optional patient=patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
        }else {
            throw new DataNotFound("Patient not found");
        }
    }

    @Override
    public List<PatientResponse> searchPatient(String search) {
        List<Patient> patients= patientRepository.findByFirstNameContainingOrLastNameContainingOrAddress_StreetOrAddress_Zip(
                                search, search, search, search);
        return patientMapper.patientToDtoList(patients);
    }
}
