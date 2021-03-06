package org.skillbox.devtales.controller;

import lombok.RequiredArgsConstructor;
import org.skillbox.devtales.api.request.AuthRequest;
import org.skillbox.devtales.api.request.ChangePasswordRequest;
import org.skillbox.devtales.api.request.RegisterRequest;
import org.skillbox.devtales.api.request.RestoreRequest;
import org.skillbox.devtales.api.response.AuthResponse;
import org.skillbox.devtales.api.response.CommonResponse;
import org.skillbox.devtales.exception.DuplicateUserEmailException;
import org.skillbox.devtales.service.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@RequestBody RegisterRequest registerRequest) throws DuplicateUserEmailException {
        return new ResponseEntity<>(authUserService.register(registerRequest), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        return new ResponseEntity<>(authUserService.login(authRequest, authenticationManager), HttpStatus.OK);
    }

    @GetMapping("/logout")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<AuthResponse> logout() {

        return new ResponseEntity<>(authUserService.logout(), HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<AuthResponse> check(Principal principal) {
        return new ResponseEntity<>(authUserService.check(principal), HttpStatus.OK);
    }

    @PostMapping("/restore")
    public ResponseEntity<CommonResponse> restorePassword(@RequestBody RestoreRequest restoreRequest) {

        return new ResponseEntity<>(authUserService.restorePassword(restoreRequest.getEmail()), HttpStatus.OK);
    }

    @PostMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {

        return new ResponseEntity<>(authUserService.changePassword(changePasswordRequest), HttpStatus.OK);
    }

}
