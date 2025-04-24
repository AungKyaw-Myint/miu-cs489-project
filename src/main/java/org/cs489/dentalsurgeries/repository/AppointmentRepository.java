package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.model.Appointment;
import org.cs489.dentalsurgeries.model.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByAppointmentCode(String appointmentCode);
    List<Appointment> findByAppointmentStatusNotAndPatient_Id(AppointmentStatus appointmentStatus,Long patientId);
    List<Appointment> findByAppointmentDateBetweenAndAppointmentStatusAndDentist_Id(LocalDate startDate,
                                                                                  LocalDate endDate,
                                                                 AppointmentStatus appointmentStatus,Long dentistId);

    Page<Appointment> findByPatient_IdOrDentist_IdOrSurgery_Id(Long patientId, Long dentistId, Long surgeryId,
                                                               Pageable pageable);
}
