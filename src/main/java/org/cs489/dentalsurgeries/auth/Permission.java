package org.cs489.dentalsurgeries.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read"),
    PATIENT_WRITE("patient:write"),
    PATIENT_READ("patient:read"),
    DENTIST_WRITE("dentist:write"),
    DENTIST_READ("dentist:read");

    @Getter
    private final String permission;
}
