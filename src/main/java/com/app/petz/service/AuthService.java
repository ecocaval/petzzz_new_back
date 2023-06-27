package com.app.petz.service;

import com.app.petz.core.requests.AuthenticationRequestJson;
import com.app.petz.core.requests.RegisterRequest;
import com.app.petz.core.responses.AuthenticationResponseJson;
import com.app.petz.mapper.CustomerMapper;
import com.app.petz.model.Customer;
import com.app.petz.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponseJson create(RegisterRequest registerRequest) {
        Customer customer = customerMapper.registerRequestToCustomer(registerRequest);
        customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(customer);
        return customerMapper.CustomerToAuthResponseJson(customer, jwtToken);
    }

    public AuthenticationResponseJson authenticate(AuthenticationRequestJson request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var customer = customerRepository.findByEmail(request.email())
                .orElseThrow(); //todo UserNotFoundException
        var jwtToken = jwtService.generateToken(customer);
        return customerMapper.CustomerToAuthResponseJson(customer, jwtToken);
    }
}
