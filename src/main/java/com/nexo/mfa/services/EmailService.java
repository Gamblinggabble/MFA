package com.nexo.mfa.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final MFACodeService codeService;
    private final Map<String, String> map;

    public EmailService(MFACodeService codeService) {
        this.codeService = codeService;
        this.map = new HashMap<>();
    }

    public void sendCode(String emailAddress) {
        // TODO send email
        String code = codeService.generateCode();
        map.put(emailAddress, code);
        System.out.println("Sending code " + code + " to " + emailAddress + ".");
    }

    public boolean validateCode(String emailAddress, String inputCode) {
        // TODO use Redis to keep track of codes
        return map.containsKey(emailAddress) && map.get(emailAddress).equals(inputCode);
    }
}
