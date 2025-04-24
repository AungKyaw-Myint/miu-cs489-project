package org.cs489.dentalsurgeries.dto.request;

import org.cs489.dentalsurgeries.auth.UserRole;

public record UserRequest(

         String username,
         String password,
         UserRole role
//         DentistRequest dentist,
//         PatientRequest patient
) {
}
