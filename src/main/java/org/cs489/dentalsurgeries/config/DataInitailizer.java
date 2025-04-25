package org.cs489.dentalsurgeries.config;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.model.Address;
import org.cs489.dentalsurgeries.model.Surgery;
import org.cs489.dentalsurgeries.repository.SurgeryRepository;
import org.cs489.dentalsurgeries.service.AppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Transactional
public class DataInitailizer {

    private final SurgeryRepository  surgeryRepository;

    @Bean
    CommandLineRunner init(AppointmentService appointmentService) {
        return args -> {
            List<Surgery> surgeries = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                Address address = new Address();
                address.setStreet("Street " + i + " Health Park");
                address.setCity("City" + i);
                address.setState("State" + i);
                address.setZip("1000" + i);

                Surgery surgery = new Surgery();
                surgery.setSurgeryName("Surgery Type " + i);
                surgery.setSurgeryDescription("Description for surgery type " + i);
                surgery.setAddressLocation(address);

                surgeries.add(surgery);
            }
            surgeryRepository.saveAll(surgeries);
        };
    }
}
