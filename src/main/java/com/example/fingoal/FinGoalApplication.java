package com.example.fingoal;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
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

//            User testUser = User.builder()
//                    .firstName("tom")
//                    .lastName("cat")
//                    .telephone("0712345678")
//                    .email("user@user.com")
//                    .password(passwordEncoder.encode("admin123"))
//                    .role(Role.ADMIN)
//                    .profilePicture("https://unsplash.com/photos/grayscale-photo-of-man-c_GmwfHBDzk?utm_content=creditShareLink&utm_medium=referral&utm_source=unsplash")
//                    .build();


        };
    }


}
