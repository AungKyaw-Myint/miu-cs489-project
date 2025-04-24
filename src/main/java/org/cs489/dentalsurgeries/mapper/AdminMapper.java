package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.dto.request.AdminRequest;
import org.cs489.dentalsurgeries.dto.response.AdminResponse;
import org.cs489.dentalsurgeries.dto.response.DentistResponse;
import org.cs489.dentalsurgeries.model.Admin;
import org.cs489.dentalsurgeries.model.Dentist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminMapper {

    Admin dtoToAdmin(AdminRequest adminRequest);

    AdminResponse dentistToAdminResponse(Admin admin);

    List<AdminResponse> adminListToDentistList(List<Admin> adminList);
}
