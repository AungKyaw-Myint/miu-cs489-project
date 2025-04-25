package org.cs489.dentalsurgeries;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.dto.request.*;
import org.cs489.dentalsurgeries.model.Address;
import org.cs489.dentalsurgeries.model.Appointment;
import org.cs489.dentalsurgeries.model.Surgery;
import org.cs489.dentalsurgeries.repository.SurgeryRepository;
import org.cs489.dentalsurgeries.service.AppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DentalSurgeriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DentalSurgeriesApplication.class, args);
    }
}
