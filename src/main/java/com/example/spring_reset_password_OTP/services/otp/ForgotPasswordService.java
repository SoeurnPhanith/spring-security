package com.example.spring_reset_password_OTP.services.otp;

import org.springframework.stereotype.Service;

@Service
public interface ForgotPasswordService {

    String generateOtp(String email);

    boolean verifyOtp(String email, String otpCode);

    void deleteOtp(String email);

}
