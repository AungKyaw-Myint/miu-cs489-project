package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.dto.request.PatientRequest;
import org.cs489.dentalsurgeries.dto.response.PatientResponse;
import org.cs489.dentalsurgeries.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {

    Patient dtoToPatient(PatientRequest patientRequest);

    PatientResponse patientToDto(Patient patient);

    List<PatientResponse> patientToDtoList(List<Patient> patient);
}
