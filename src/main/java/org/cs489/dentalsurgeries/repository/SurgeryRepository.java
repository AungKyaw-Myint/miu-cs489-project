package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
