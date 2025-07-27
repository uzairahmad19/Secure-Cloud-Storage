package com.cloudstorage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CloudstorageApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudstorageApplication.class, args);
	}
}
// admin username: admin
//admin password: adminpassword