package com.app.petz.core.responses;

import com.app.petz.model.Customer;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuthenticationResponseJson(
        LocalDateTime timeStamp,
        String message,
        String token,
        Customer customer
) {
}
