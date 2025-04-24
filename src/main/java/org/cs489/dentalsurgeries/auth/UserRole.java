package org.cs489.dentalsurgeries.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum UserRole {
    PATIENT(
            Set.of(Permission.PATIENT_WRITE,
                   Permission.PATIENT_READ)
    ),

    DENTIST(
            Set.of(Permission.DENTIST_WRITE,
                   Permission.DENTIST_READ)
    ),
    ADMIN(
            Set.of(Permission.ADMIN_WRITE,
                   Permission.ADMIN_READ)
    );

    private final Set<Permission> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities =
                permissions.stream().map(
                                   permission -> new SimpleGrantedAuthority(permission.getPermission())
                                        )
                           .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));

        return authorities;
    }
}
