package com.app.petz.mapper;

import com.app.petz.core.requests.RegisterRequest;
import com.app.petz.core.responses.AuthenticationResponseJson;
import com.app.petz.core.utils.CpfValidator;
import com.app.petz.model.Customer;
import com.app.petz.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final PasswordEncoder passwordEncoder;
    private final CpfValidator cpfValidator;

    public Customer registerRequestToCustomer(RegisterRequest createRequest){
        return Customer.builder()
                .name(createRequest.name())
                .removed(false)
                .creationDate(LocalDateTime.now())
                .birthday(LocalDate.parse(createRequest.birthday(), birthdayFormatter))
                .cpf(cpfValidator.cleanCpfCharacter(createRequest.cpf()))
                .email(createRequest.email())
                .password(passwordEncoder.encode(createRequest.password()))
                .mainImageUrl(createRequest.mainImageUrl())
                .role(Role.USER)
                .build();
    }

    public AuthenticationResponseJson CustomerToAuthResponseJson(Customer customer, String token) {
        return AuthenticationResponseJson.builder()
                .timeStamp(LocalDateTime.now())
                .message("Usuário "+customer.getName()+ " criado com sucesso!")
                .token(token)
                .customer(customer)
                .build();
    }
}
