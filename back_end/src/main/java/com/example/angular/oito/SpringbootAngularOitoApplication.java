package com.example.angular.oito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootAngularOitoApplication  implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAngularOitoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		System.out.println(bcryptPasswordEncoder.encode(password));
		System.out.println(bcryptPasswordEncoder.encode(password));
	}
}
