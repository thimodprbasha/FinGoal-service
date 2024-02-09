package com.example.fingoal;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class FinGoalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinGoalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService ,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            Optional<User> isAdminPresent = userRepository.findByRole(Role.ADMIN);
            if (isAdminPresent.isEmpty()){
                User admin = User.builder()
                        .firstName("admin")
                        .lastName("admin")
                        .telephone("07212333333")
                        .email("adminuser@admin.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .profilePicture("")
                        .build();
                userRepository.save(admin);
            }
        };
    }


}
