package org.cs489.dentalsurgeries.dto.response;

public record AddressResponse(
        Long id,
         int no,
         String street,
         String city,
         String state,
         String zip
) {
}
