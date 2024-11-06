package com.nexo.mfa.controllers;

import com.nexo.mfa.dtos.CodeVerificationRequest;
import com.nexo.mfa.dtos.EmailRequest;
import com.nexo.mfa.services.EmailService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mfa/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestBody EmailRequest request) {
        this.emailService.sendCode(request.getEmail());
        return ResponseEntity.ok("Code sent successfully to " + request.getEmail() + ".");
    }

    @PostMapping("/validate-code")
    public ResponseEntity<HttpStatus> validateCode(@RequestBody CodeVerificationRequest request) {
        boolean valid = this.emailService.validateCode(request.getEmail(), request.getCode());
        // TODO maybe return text message also
        if(valid) {
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
