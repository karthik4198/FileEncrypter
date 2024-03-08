package com.File_Hider.File_Hider.Services;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendOTPService {
    @Autowired
    private JavaMailSender mailSender;

    /*
    Function info:Sending generated otp to the respective mail
    */
    public void sendOTP(String toEmail, String subject, String genOTP) throws MessagingException {
        SimpleMailMessage message=new SimpleMailMessage();

        message.setFrom("**************");//Please do mention the mail id from which you need to send mail
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(genOTP);

        mailSender.send(message);
    }
}
