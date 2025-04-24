package org.cs489.dentalsurgeries.auth.service;

import org.cs489.dentalsurgeries.auth.dto.AuthenticationRequest;
import org.cs489.dentalsurgeries.auth.dto.AuthenticationResponse;
import org.cs489.dentalsurgeries.dto.request.AdminRequest;
import org.cs489.dentalsurgeries.dto.request.DentistRequest;
import org.cs489.dentalsurgeries.dto.request.PatientRequest;

public interface AuthenticationService {

    public AuthenticationResponse registerPatient(PatientRequest patientRequest);
    public AuthenticationResponse registerDentist(DentistRequest dentistRequest);
    public AuthenticationResponse registerAdmin(AdminRequest adminRequest);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
