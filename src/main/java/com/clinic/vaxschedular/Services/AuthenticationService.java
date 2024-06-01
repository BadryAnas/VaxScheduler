package com.clinic.vaxschedular.Services;

import com.clinic.vaxschedular.DTO.JwtAuthenticationResponse;
import com.clinic.vaxschedular.DTO.RefreshTokenRequest;
import com.clinic.vaxschedular.DTO.SignUpRequest;
import com.clinic.vaxschedular.DTO.SigninRequest;
import com.clinic.vaxschedular.Entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
