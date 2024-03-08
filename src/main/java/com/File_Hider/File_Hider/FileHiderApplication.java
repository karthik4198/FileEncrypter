package com.File_Hider.File_Hider;

import com.File_Hider.File_Hider.Exceptions.InvalidInputExeption;
import com.File_Hider.File_Hider.Views.Welcome;
import jakarta.mail.MessagingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class FileHiderApplication {

	public static void main(String[] args) throws MessagingException, IOException, InvalidInputExeption {
		ConfigurableApplicationContext context = SpringApplication.run(FileHiderApplication.class, args);
		Welcome w = context.getBean(Welcome.class);
		w.welcome();
	}

}
