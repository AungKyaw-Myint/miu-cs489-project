package org.cs489.dentalsurgeries.auth.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
