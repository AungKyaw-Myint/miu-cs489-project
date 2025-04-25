package org.cs489.dentalsurgeries.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.cs489.dentalsurgeries.auth.UserRole;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

public record PatientRequest(

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
         String username,
        @NotBlank(message = "Password is required")
        @Size(min = 3, max = 10, message = "Password must be between 3 and 10")
         String password,
         UserRole role,
         String patientNo,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

         String gender,
         LocalDate birthDate,

         @Valid
         AddressReqeust address
) {
}
