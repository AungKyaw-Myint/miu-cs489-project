package org.cs489.dentalsurgeries.dto.request;

public record SurgeryRequest (
         String surgeryName,
         String surgeryDescription,
         AddressReqeust addressLocation
){
}
