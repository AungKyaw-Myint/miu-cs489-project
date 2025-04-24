package org.cs489.dentalsurgeries.dto.response;

public record SurgeryResponse(
        Long id,
         String surgeryName,
         String surgeryDescription,
         AddressResponse addressLocation
){
}
