package org.cs489.dentalsurgeries.dto.response;

import java.time.LocalDate;

public record PatientResponse(
         Long id,
         String patientNo,
         String firstName,
         String lastName,
         String gender,
         LocalDate birthDate,
         AddressResponse address
) {
}
