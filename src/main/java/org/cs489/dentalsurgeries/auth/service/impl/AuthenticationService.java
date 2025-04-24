package org.cs489.dentalsurgeries.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.auth.User;
import org.cs489.dentalsurgeries.auth.dto.AuthenticationRequest;
import org.cs489.dentalsurgeries.auth.dto.AuthenticationResponse;
import org.cs489.dentalsurgeries.config.JwtService;
import org.cs489.dentalsurgeries.dto.request.AdminRequest;
import org.cs489.dentalsurgeries.dto.request.DentistRequest;
import org.cs489.dentalsurgeries.dto.request.PatientRequest;
import org.cs489.dentalsurgeries.exception.user.DataNotFound;
import org.cs489.dentalsurgeries.mapper.AdminMapper;
import org.cs489.dentalsurgeries.mapper.DentistMapper;
import org.cs489.dentalsurgeries.mapper.PatientMapper;
import org.cs489.dentalsurgeries.mapper.UserMapper;
import org.cs489.dentalsurgeries.model.Admin;
import org.cs489.dentalsurgeries.model.Dentist;
import org.cs489.dentalsurgeries.model.Patient;
import org.cs489.dentalsurgeries.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements org.cs489.dentalsurgeries.auth.service.AuthenticationService {

    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService            jwtService;
    private final PatientMapper patientMapper;
    private final AdminMapper adminMapper;
    private final DentistMapper dentistMapper;

    @Transactional
    public AuthenticationResponse registerPatient(PatientRequest patientRequest) {
        Patient patient =patientMapper.dtoToPatient(patientRequest);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));

        User savedPatient =userRepository.save(patient);

        String token =jwtService.generateToken(savedPatient);

        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse registerDentist(DentistRequest dentistRequest) {
        Dentist dentist =dentistMapper.dtoToDentist(dentistRequest);
        dentist.setPassword(passwordEncoder.encode(dentist.getPassword()));

        User savedPatient =userRepository.save(dentist);

        String token =jwtService.generateToken(savedPatient);

        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse registerAdmin(AdminRequest adminRequest) {
        Admin admin =adminMapper.dtoToAdmin(adminRequest);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        User savedPatient =userRepository.save(admin);

        String token =jwtService.generateToken(savedPatient);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()));

        var user=(User)authentication.getPrincipal();

        String token =jwtService.generateToken(user);;

        return new AuthenticationResponse(token);
    }
}
