package org.cs489.dentalsurgeries.dto.request;

import org.cs489.dentalsurgeries.model.AppointmentStatus;

import java.time.LocalDate;

public record AppointmentRequest(
        String title,
        String appointmentCode,
        String description,
        LocalDate appointmentDate,
        String    appointmentType,
        AppointmentStatus appointmentStatus,
        Long patientId,
        Long surgeryId,
        Long dentistId
) {
}
