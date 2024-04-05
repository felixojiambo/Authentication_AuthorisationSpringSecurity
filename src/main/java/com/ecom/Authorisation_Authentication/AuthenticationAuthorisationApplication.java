package com.ecom.Authorisation_Authentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ecom.Authorisation_Authentication.User.repository")
public class AuthenticationAuthorisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationAuthorisationApplication.class, args);
		System.out.println("User Authorisation");
	}
}
