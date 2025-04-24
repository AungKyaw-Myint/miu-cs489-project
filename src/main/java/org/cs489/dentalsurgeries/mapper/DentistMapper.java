package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.dto.request.DentistRequest;
import org.cs489.dentalsurgeries.dto.response.DentistResponse;
import org.cs489.dentalsurgeries.model.Dentist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DentistMapper {

    Dentist dtoToDentist(DentistRequest dentistRequest);

    DentistResponse dentistToDentistResponse(Dentist dentist);

    List<DentistResponse> dentistListToDentistList(List<Dentist> dentistList);
}
