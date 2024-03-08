package com.File_Hider.File_Hider.Services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateOtpService {

    /*
    Function info:Generating OTP of a 4 digit number
    */
    public static String generateOtp(){
        Random random=new Random();
        return String.format("%04d",random.nextInt(10000));
    }
}
