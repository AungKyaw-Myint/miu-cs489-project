package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.dto.request.AppointmentRequest;
import org.cs489.dentalsurgeries.dto.response.AppointmentResponse;
import org.cs489.dentalsurgeries.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {

    Appointment dtoToAppointment(AppointmentRequest dto);

    AppointmentResponse appointmentToDto(Appointment appointment);

    List<AppointmentResponse> appointmentListToDtoList(List<Appointment> appointmentList);
}
