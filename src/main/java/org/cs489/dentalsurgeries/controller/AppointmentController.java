package org.cs489.dentalsurgeries.controller;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.dto.request.AppointmentRequest;
import org.cs489.dentalsurgeries.dto.response.AppointmentResponse;
import org.cs489.dentalsurgeries.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<AppointmentResponse> addAppointment(@Validated @RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse appointmentResponse =appointmentService.saveAppointment(appointmentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
    }

    @PutMapping("/{appointmentId}")
    @PreAuthorize("hasAnyAuthority( 'admin:write')")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable("appointmentId") Long appointmentId,
            @Validated @RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse appointmentResponse =appointmentService.updateAppointment(appointmentRequest,appointmentId);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
    }

    @DeleteMapping("/{appointmentId}")
    @PreAuthorize("hasAnyAuthority('admin:write')")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointmentId") Long appointmentId) {

        appointmentService.deleteAppointment(appointmentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{appointmentCode}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable("appointmentCode") String appointmentCode) {

        AppointmentResponse appointmentResponse =appointmentService.getAppointmentByCode(appointmentCode);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AppointmentResponse>> getAppointments(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) Long dentistId,
            @RequestParam(required = false) Long surgeryId,
            @PageableDefault(page = 0, size = 10, sort = "appointmentDate", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<AppointmentResponse> appointmentResponse=appointmentService.getAllAppointments(
                patientId,dentistId,surgeryId,pageable);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
    }
}
