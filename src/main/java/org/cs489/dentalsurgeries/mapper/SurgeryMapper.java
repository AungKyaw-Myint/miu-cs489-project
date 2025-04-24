package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.dto.request.SurgeryRequest;
import org.cs489.dentalsurgeries.dto.response.SurgeryResponse;
import org.cs489.dentalsurgeries.model.Surgery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SurgeryMapper {

    Surgery dtoSurgery(SurgeryRequest surgeryRequest);

    SurgeryResponse dtoSurgeryResponse(Surgery surgery);

    List<SurgeryResponse> dtoSurgeryResponseList(List<Surgery> surgeryList);
}
