package uz.pdp.appquizgame.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appquizgame.service.EmailService;
import uz.pdp.appquizgame.service.OTPService;
import uz.pdp.appquizgame.service.token.JwtTokenService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final EmailService emailService;
    private final OTPService otpService;
    private final JwtTokenService jwtTokenService;

    private final Map<String, String> otpStorage = new HashMap<>();

    @PostMapping("/gmail")
    public ResponseEntity<?> gmailSendPassword(@RequestParam String email) {
        String otp = otpService.generateOTP();
        otpStorage.put(email, otp);
        emailService.sendEmail(email, "Your OTP Code", "Your OTP code is: " + otp);
        return ResponseEntity.ok("OTP sent to email: " + email);


    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, Object>> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String fullName,
            @RequestParam String phoneNumber) {

        Map<String, Object> response = new HashMap<>();
        if (otp.equals(otpStorage.get(email))) {
            String token = jwtTokenService.generateToken(email);
            int coinCount = 0; // replace with actual coin count logic

            response.put("token", token);
            response.put("coinCount", coinCount);
            response.put("fullName", fullName);
            response.put("email", email);
            response.put("role", "User"); // replace with actual role logic

            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid OTP");
            return ResponseEntity.status(400).body(response);
        }
    }
}
