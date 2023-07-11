package com.app.petz.model;

import com.app.petz.core.requests.RegisterRequest;
import com.app.petz.core.responses.AuthenticationResponseJson;
import com.app.petz.core.utils.CpfValidator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Customer")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean removed = false;

    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column()
    private String mainImageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static Customer fromRegisterRequest(
            RegisterRequest createRequest,
            DateTimeFormatter birthdayFormatter,
            PasswordEncoder passwordEncoder,
            CpfValidator cpfValidator
    ){
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

    public static AuthenticationResponseJson authResponseJsonFromCustomer(Customer customer, String token) {
        return AuthenticationResponseJson.builder()
                .timeStamp(LocalDateTime.now())
                .message("Usuário "+customer.getName()+ " criado com sucesso!")
                .token(token)
                .customer(customer)
                .build();
    }
}