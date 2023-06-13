package com.app.petz.controller;

import com.app.petz.core.requests.PetPostRequestJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

//    @PostMapping("/product")
//    public ResponseEntity<?> create(
//            @RequestBody @Valid CustomerPostRequestJson customerPostRequestJson
//    ) {
//        var customer = customerService.create(customerPostRequestJson);
//        return ResponseEntity.status(HttpStatus.OK).body(customer);
//    }
}
