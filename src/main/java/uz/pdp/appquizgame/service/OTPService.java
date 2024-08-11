package uz.pdp.appquizgame.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPService {

    private final SecureRandom random = new SecureRandom();
    private final int OTP_LENGTH = 6;

    public String generateOTP() {
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
