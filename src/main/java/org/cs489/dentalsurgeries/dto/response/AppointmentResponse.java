package org.cs489.dentalsurgeries.dto.response;

import org.cs489.dentalsurgeries.model.AppointmentStatus;
import org.cs489.dentalsurgeries.model.Patient;
import org.cs489.dentalsurgeries.model.Surgery;

import java.time.LocalDate;

public record AppointmentResponse(
        Long id,
        String title,
        String appointmentCode,
        String description,
        LocalDate appointmentDate,
        String    appointmentType,
        AppointmentStatus appointmentStatus,
        PatientResponse patient,
        SurgeryResponse surgery,
        DentistResponse dentist
) {
}
