package com.example.fingoal;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class FinGoalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinGoalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            Optional<User> isAdminPresent = userRepository.findByEmail("adminuser@admin.com");
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
