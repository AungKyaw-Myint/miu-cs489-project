package org.cs489.dentalsurgeries.controller;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.auth.User;
import org.cs489.dentalsurgeries.dto.request.PatientRequest;
import org.cs489.dentalsurgeries.dto.response.PatientResponse;
import org.cs489.dentalsurgeries.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientResponse>> getPatients() {
        List<PatientResponse> patientResponse =patientService.getAllPatients();

        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable("patientId") Long patientId) {
        PatientResponse patientResponse=patientService.getPatientById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

//    @PostMapping()
//    public ResponseEntity<PatientResponse> addPatient(@RequestBody PatientRequest patientRequest) {
//        PatientResponse patientResponse =patientService.addPatient(patientRequest);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
//    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable("patientId") Long patientId,
                                                         @Validated @RequestBody PatientRequest patientRequest) {

        PatientResponse patientResponse=patientService.updatePatient(patientRequest,patientId);

        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientId") Long patientId) {

        patientService.deletePatient(patientId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/search/{searchString}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'dentist:write')")
    public ResponseEntity<List<PatientResponse>> searchPublisher(@PathVariable String searchString) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.searchPatient(searchString));
    }
}
