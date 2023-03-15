package com.covid19.Covid19Tracker;

import com.covid19.Covid19Tracker.repository.UserRepository;
import com.covid19.Covid19Tracker.service.AppUserDetailsService;
import com.covid19.Covid19Tracker.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan({"com.covid19.Covid19Tracker.controller", "com.covid19.Covid19Tracker.config"})
public class Covid19TrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19TrackerApplication.class, args);
	}

	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public static UserService userService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		return new UserService(userRepository, passwordEncoder);
	}

	@Bean
	public static UserDetailsService userDetailsService(UserService userService) {
		return new AppUserDetailsService(userService);
	}
}
