package org.cs489.dentalsurgeries.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.cs489.dentalsurgeries.auth.UserRole;

public record DentistRequest(

         @NotBlank(message = "Username is required")
         @Size(min = 3, max = 10, message = "Username must be between 3 and 7 characters")
         String username,
         String password,
         UserRole role,
         @NotBlank(message = "First name is required")
         String firstName,
         @NotBlank(message = "Last name is required")
         String lastName,
         String specialization,

         @Email(message = "Invalid email format")
         @NotBlank(message = "Email is required")
         String email,
         String phone
) {
}
