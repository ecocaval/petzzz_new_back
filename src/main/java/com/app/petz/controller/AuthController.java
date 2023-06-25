package com.app.petz.controller;

import com.app.petz.core.requests.AuthenticationRequestJson;
import com.app.petz.core.requests.RegisterRequest;
import com.app.petz.core.responses.AuthenticationResponseJson;
import com.app.petz.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseJson> create(
            @RequestBody @Valid RegisterRequest request){
        var customer = authService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseJson> authenticate(
            @RequestBody @Valid AuthenticationRequestJson request
            ){
        var customer = authService.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
}
