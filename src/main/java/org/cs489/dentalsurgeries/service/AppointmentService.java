package org.cs489.dentalsurgeries.service;

import org.cs489.dentalsurgeries.dto.request.AppointmentRequest;
import org.cs489.dentalsurgeries.dto.response.AppointmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentService{

    AppointmentResponse saveAppointment(AppointmentRequest appointmentRequest);
    AppointmentResponse updateAppointment(AppointmentRequest appointmentRequest, Long id);
    void deleteAppointment(Long appointmentId);
    AppointmentResponse getAppointmentByCode(String appointmentCode);
    Page<AppointmentResponse> getAllAppointments(Long patientId, Long dentistId, Long surgeryId,
                                                 Pageable pageable);
}
