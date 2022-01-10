package org.skillbox.devtales.service;

import org.skillbox.devtales.api.request.AuthRequest;
import org.skillbox.devtales.api.request.RegisterRequest;
import org.skillbox.devtales.api.response.AuthResponse;
import org.skillbox.devtales.api.response.UserResponse;
import org.skillbox.devtales.exception.DuplicateUserEmailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {

    UserResponse register(RegisterRequest registerRequest) throws DuplicateUserEmailException;

    AuthResponse login(AuthRequest authRequest, AuthenticationManager authenticationManager);

    AuthResponse getAuthResponse(String userName);

    AuthResponse logout();
}