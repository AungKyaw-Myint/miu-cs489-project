package org.cs489.dentalsurgeries.repository;

import org.cs489.dentalsurgeries.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
