package com.nexo.mfa.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final MFACodeService codeService;
    private final JavaMailSender mailSender;
    private final Map<String, String> map;

    public EmailService(MFACodeService codeService, JavaMailSender mailSender) {
        this.codeService = codeService;
        this.mailSender = mailSender;
        this.map = new HashMap<>();
    }

    public void sendCode(String emailAddress) {
        String code = codeService.generateCode();
        map.put(emailAddress, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject("MFA code");
        message.setText("Your code is " + code);
        mailSender.send(message);

        System.out.println("Sending code " + code + " to " + emailAddress + ".");
    }

    public boolean validateCode(String emailAddress, String inputCode) {
        // TODO use Redis to keep track of codes
        return map.containsKey(emailAddress) && map.get(emailAddress).equals(inputCode);
    }
}
