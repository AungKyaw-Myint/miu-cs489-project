package org.cs489.dentalsurgeries.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.cs489.dentalsurgeries.auth.UserRole;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

public record PatientRequest(

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 10, message = "Username must be between 3 and 7 characters")
         String username,
         String password,
         UserRole role,
         String patientNo,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

         String gender,
         LocalDate birthDate,

         @Validated
         AddressReqeust address
) {
}
