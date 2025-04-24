package org.cs489.dentalsurgeries.dto.response;

import org.cs489.dentalsurgeries.auth.UserRole;

public record UserResponse(
        Long id,
        String username,
        String password,
        UserRole role
) {
}
