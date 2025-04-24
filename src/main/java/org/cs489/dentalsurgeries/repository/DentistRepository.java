package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
}
