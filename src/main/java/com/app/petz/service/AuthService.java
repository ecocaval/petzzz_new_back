package com.app.petz.service;

import com.app.petz.core.requests.AuthenticationRequestJson;
import com.app.petz.core.requests.RegisterRequest;
import com.app.petz.core.responses.AuthenticationResponseJson;
import com.app.petz.core.utils.CpfValidator;
import com.app.petz.exception.DuplicatedEmailCpfException;
import com.app.petz.exception.InvalidCpfException;
import com.app.petz.exception.messages.ErrorMessages;
import com.app.petz.mapper.CustomerMapper;
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
    private final CpfValidator cpfValidator;

    @Transactional
    public AuthenticationResponseJson create(RegisterRequest registerRequest) {
        var emailIsInUse = customerRepository.findByEmail(registerRequest.email()).isPresent();
        var cpfIsInUse = customerRepository.findByCpf(registerRequest.cpf()).isPresent();

        var cpfIsValid = cpfValidator.validateCpf(registerRequest.cpf());

        if (emailIsInUse || cpfIsInUse) {
            var errorMessage = emailIsInUse && cpfIsInUse
                    ? ErrorMessages.EMAIL_AND_CPF_ARE_ALREADY_IN_USE.getErrorMessage()
                    : cpfIsInUse ?
                    ErrorMessages.CPF_IS_ALREADY_IN_USE.getErrorMessage() :
                    ErrorMessages.EMAIL_IS_ALREADY_IN_USE.getErrorMessage();

            throw new DuplicatedEmailCpfException(
                    errorMessage,
                    registerRequest.email(),
                    registerRequest.cpf()
            );
        }

        if(!cpfIsValid) {
            throw new InvalidCpfException(ErrorMessages.INVALID_CPF.getErrorMessage(), registerRequest.cpf());
        }

        var customer = customerMapper.registerRequestToCustomer(registerRequest);
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
