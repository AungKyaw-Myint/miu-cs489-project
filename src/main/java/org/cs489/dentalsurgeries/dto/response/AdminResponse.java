package org.cs489.dentalsurgeries.dto.response;

public record AdminResponse(
         Long id,
         String firstName,
         String lastName,
         String email,
         String phone
) {
}
