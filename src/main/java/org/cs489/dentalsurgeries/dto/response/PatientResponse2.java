package org.cs489.dentalsurgeries.dto.response;

import java.time.LocalDate;

public record PatientResponse2(
         Long id,
         String firstName,
         String lastName,
         String gender,
         LocalDate birthDate
) {
}
