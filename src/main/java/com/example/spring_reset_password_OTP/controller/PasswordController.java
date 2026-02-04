package com.example.spring_reset_password_OTP.controller;

import com.example.spring_reset_password_OTP.services.otp.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/request-reset")
    public String requestReset(@RequestParam String email) {
        passwordService.requestForResetPassword(email);
        return "OTP sent to email!";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        if (passwordService.verifyOtp(email, otp)) {
            return "OTP verified! You can now reset your password.";
        }
        return "Invalid or expired OTP!";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        passwordService.resetPassword(email, otp, newPassword);
        return "Password reset successfully!";
    }
}
