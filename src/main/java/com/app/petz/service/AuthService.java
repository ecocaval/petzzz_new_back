package com.app.petz.service;

import com.app.petz.core.requests.AuthenticationRequestJson;
import com.app.petz.core.requests.RegisterRequest;
import com.app.petz.core.responses.AuthenticationResponseJson;
import com.app.petz.core.utils.CpfValidator;
import com.app.petz.exception.DuplicatedEmailCpfException;
import com.app.petz.exception.InvalidCpfException;
import com.app.petz.exception.messages.ErrorMessages;
import com.app.petz.model.Customer;
import com.app.petz.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CpfValidator cpfValidator;
    private final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthenticationResponseJson create(RegisterRequest registerRequest) {
        boolean emailIsInUse = customerRepository.findByEmail(registerRequest.email()).isPresent();
        boolean cpfIsInUse = customerRepository.findByCpf(registerRequest.cpf()).isPresent();

        boolean cpfIsValid = cpfValidator.validateCpf(registerRequest.cpf());

        if (emailIsInUse || cpfIsInUse) {
            String errorMessage = emailIsInUse && cpfIsInUse
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

        Customer customer = Customer.fromRegisterRequest(
                registerRequest, birthdayFormatter, passwordEncoder, cpfValidator
        );

        customerRepository.save(customer);
        String jwtToken = jwtService.generateToken(customer);
        return Customer.authResponseJsonFromCustomer(customer, jwtToken);
    }

    public AuthenticationResponseJson authenticate(AuthenticationRequestJson request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Customer customer = customerRepository.findByEmail(request.email())
                .orElseThrow(); //todo UserNotFoundException
        String jwtToken = jwtService.generateToken(customer);
        return Customer.authResponseJsonFromCustomer(customer, jwtToken);
    }
}
