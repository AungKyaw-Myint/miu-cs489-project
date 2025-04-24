package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.dto.request.AddressReqeust;
import org.cs489.dentalsurgeries.dto.response.AddressResponse;
import org.cs489.dentalsurgeries.dto.response.AddressResponse2;
import org.cs489.dentalsurgeries.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {

    Address dtoToAddress(AddressReqeust addressReqeust);

    AddressResponse addressToDtoResponse(Address address);

    List<AddressResponse2> addressListToAddressList(List<Address> addressList);
}
