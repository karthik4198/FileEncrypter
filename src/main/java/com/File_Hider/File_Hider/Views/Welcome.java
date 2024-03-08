package com.File_Hider.File_Hider.Views;

import com.File_Hider.File_Hider.Exceptions.InvalidInputExeption;
import com.File_Hider.File_Hider.Models.User;
import com.File_Hider.File_Hider.Services.GenerateOtpService;
import com.File_Hider.File_Hider.Services.SendOTPService;
import com.File_Hider.File_Hider.Services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
@Component
public class Welcome {
    Scanner sc=new Scanner(System.in);
    @Autowired
    SendOTPService sendOTPService;
    @Autowired
    UserService userSerivce;
    @Autowired
    UserView userView;

    //Default constructor
    public Welcome(){
    }

    /*
    Function info:Login and sign up command line details for the app
    */
    public void welcome() throws MessagingException, IOException, InvalidInputExeption {
        HashSet<Integer> hs=new HashSet<>();
        System.out.println("Welcome to the FileEncypting app");
        do{
            System.out.println("Press 1 for login");
            System.out.println("Press 2 for signUp");
            System.out.println("Press 0 for exit");
            hs.add(1);
            hs.add(2);
            hs.add(0);
            int choice=sc.nextInt();
            if(!hs.contains(choice)){
                System.out.println("Please enter valid input");
            }
            else {
                switch (choice) {
                    case 1 -> loginMethod();
                    case 2 -> signUp();
                    case 3 -> System.exit(0);
                }
            }
        }while(true);
    }

    /*
    Function info:Logging in the user
    */
    private void loginMethod() throws MessagingException, IOException, InvalidInputExeption {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your email");
        String email=sc.nextLine();
        //If login exists in database verify and generate otp
        if(userSerivce.isExists(email)){
            String otp=GenerateOtpService.generateOtp();
            sendOTPService.sendOTP(email,"Login or SignUp OTP for the file encrypter app",otp);

            System.out.println("Please enter the OTP sent to the mail");
            String otpRecieved=sc.nextLine();

            if(otpRecieved.equals(otp)){
                System.out.println(email+" now you are logged in");
                userView.setEmail(email);
                userView.availableFunctionality();
            }
            else{
                System.out.println("Invalid OTP");
            }
        }
        else{
            System.out.println("Your email doesn't exist please do sign up");
        }

    }

    /*
    Function info:Signup function for the app
    */

    private void signUp() throws MessagingException {
        Scanner sc=new Scanner(System.in);

        System.out.println("Please enter your name");
        String name=sc.nextLine();
        System.out.println("Please enter your email");
        String email=sc.nextLine();

        String otp=GenerateOtpService.generateOtp();
        sendOTPService.sendOTP(email,"OTP for FileHider App",otp);
        System.out.println("Please enter the OTP which has been sent to the mail id");
        String otpRecieved=sc.nextLine();

        if(otpRecieved.equals(otp)){
            User user=new User(name,email);
            userSerivce.saveUser(user);
            System.out.println("Welcome to the app");
        }
        else{
            System.out.println("Invalid OTP");
        }
    }
}
