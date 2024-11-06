package com.nexo.mfa.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MFACodeService {

    public static int counter = 0;
    private final Map<String, String> map = new HashMap<>();

    public String generateCode() {
        // TODO generate actual codes
        return "code" + counter++;
    }
}
