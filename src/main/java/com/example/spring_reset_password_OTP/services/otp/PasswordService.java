package com.example.spring_reset_password_OTP.services.otp;

import com.example.spring_reset_password_OTP.entities.UserEntity;
import com.example.spring_reset_password_OTP.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    //inject interface and class to use with it
    private final UserRepository userRepository;
    private final ForgotPasswordServiceImpl forgotPasswordService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService(UserRepository userRepository, ForgotPasswordServiceImpl forgotPasswordService, EmailService emailService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.forgotPasswordService = forgotPasswordService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void requestForResetPassword(String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                ()->new RuntimeException("user not found"));
        String otp = forgotPasswordService.generateOtp(email);
        emailService.sentOptEmail(email,otp);
    }

    public boolean verifyOtp(String email, String otp) {
        return forgotPasswordService.verifyOtp(email, otp);
    }

    public void resetPassword(String email, String otp, String newPassword){
        //check expired otp
        if(!forgotPasswordService.verifyOtp(email,otp)){
            throw new RuntimeException("Invalid or expired OTP");
        }

        //find email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("user not found"));

        //set new password and save in db again
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        //delete otp
        forgotPasswordService.deleteOtp(otp);
    }

}
