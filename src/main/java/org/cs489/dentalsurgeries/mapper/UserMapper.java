package org.cs489.dentalsurgeries.mapper;

import org.cs489.dentalsurgeries.auth.User;
import org.cs489.dentalsurgeries.dto.request.UserRequest;
import org.cs489.dentalsurgeries.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User dtoToUser(UserRequest userRequest);

    UserResponse userToDto(User user);

    List<User> dtoToUsers(List<UserRequest> userRequests);
}
