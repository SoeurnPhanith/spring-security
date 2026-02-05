package com.example.spring_reset_password_OTP.services.otp;

import com.example.spring_reset_password_OTP.entities.ForgotPasswordEntity;
import com.example.spring_reset_password_OTP.repositories.ForgotPasswordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    //inject data from repo
    private final ForgotPasswordRepository forgotPasswordRepo;

    public ForgotPasswordServiceImpl(ForgotPasswordRepository forgotPasswordRepo) {
        this.forgotPasswordRepo = forgotPasswordRepo;
    }

    public String generateOtp(String email){
        //1.random on otp code
        Random random = new Random();
        String otpCode = String.format("%06d", random.nextInt(10000000));

        //2.set otp and email to entity
        ForgotPasswordEntity otp = new ForgotPasswordEntity();
        otp.setEmail(email);
        otp.setCode(otpCode);
        otp.setExpirationTime(LocalDateTime.now().plusMinutes(5));

        //3.save to db
        forgotPasswordRepo.save(otp);
        return otpCode;
    }

    @Override
    public boolean verifyOtp(String email, String otpCode) {
        //find otp by email and it's code and get only otp who before 5mn
        return forgotPasswordRepo.findByEmailAndCode(email,otpCode)
                .filter(otp->otp.getExpirationTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }

    @Override
    @Transactional
    public void deleteOtp(String email) {
        //delete otp who after 5mn
        forgotPasswordRepo.deleteByEmail(email);
    }
}
