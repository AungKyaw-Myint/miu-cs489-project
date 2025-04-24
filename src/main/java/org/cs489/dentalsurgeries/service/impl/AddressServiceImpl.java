package org.cs489.dentalsurgeries.service.impl;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.dto.response.AddressResponse2;
import org.cs489.dentalsurgeries.mapper.AddressMapper;
import org.cs489.dentalsurgeries.model.Address;
import org.cs489.dentalsurgeries.repository.AddressRepository;
import org.cs489.dentalsurgeries.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper     addressMapper;

    @Override
    public List<AddressResponse2> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addressMapper.addressListToAddressList(addresses);
    }
}
