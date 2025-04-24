package org.cs489.dentalsurgeries.dto.response;

public record AddressResponse2(
        Long id,
         int no,
         String street,
         String city,
         String state,
         String zip,
        PatientResponse2 patient
) {
}
