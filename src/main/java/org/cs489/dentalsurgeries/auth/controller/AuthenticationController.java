package org.cs489.dentalsurgeries.auth.controller;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.auth.service.impl.AuthenticationService;
import org.cs489.dentalsurgeries.auth.dto.AuthenticationRequest;
import org.cs489.dentalsurgeries.auth.dto.AuthenticationResponse;
import org.cs489.dentalsurgeries.dto.request.AdminRequest;
import org.cs489.dentalsurgeries.dto.request.DentistRequest;
import org.cs489.dentalsurgeries.dto.request.PatientRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register-patient")
    private ResponseEntity<AuthenticationResponse> registerPatient(@Validated
                                                                   @RequestBody PatientRequest patientRequest) {

        AuthenticationResponse response=authenticationService.registerPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register-dentist")
    private ResponseEntity<AuthenticationResponse> registerPatient(@Validated @RequestBody DentistRequest dentistRequest) {

        AuthenticationResponse response=authenticationService.registerDentist(dentistRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register-admin")
    private ResponseEntity<AuthenticationResponse> registerAdmin(@Validated @RequestBody AdminRequest adminRequest) {

        AuthenticationResponse response=authenticationService.registerAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/authenticate")
    private ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                                AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response=authenticationService.authenticate(authenticationRequest);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
