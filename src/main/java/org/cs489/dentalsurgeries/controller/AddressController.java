package org.cs489.dentalsurgeries.controller;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.dto.response.AddressResponse2;
import org.cs489.dentalsurgeries.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<AddressResponse2>> listAddress() {
        return ResponseEntity.ok(addressService.getAddresses());
    }

}
