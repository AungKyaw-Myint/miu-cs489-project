package org.cs489.dentalsurgeries.service;

import org.cs489.dentalsurgeries.dto.response.AddressResponse;
import org.cs489.dentalsurgeries.dto.response.AddressResponse2;

import java.util.List;

public interface AddressService {
    List<AddressResponse2> getAddresses();
}
