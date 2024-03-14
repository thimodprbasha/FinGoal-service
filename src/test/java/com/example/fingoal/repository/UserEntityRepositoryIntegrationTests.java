package com.example.fingoal.repository;

import com.example.fingoal.model.users.Role;
import com.example.fingoal.model.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Log
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEntityRepositoryIntegrationTests {

    private final UserRepository userRepositoryTest;

    private final PasswordEncoder passwordEncoder;

    @Test
    public void setUserCreateAndRetrieveTest(){
//
        User testUser = User.builder()
                .firstName("tom")
                .lastName("cat")
                .telephone("0712345678")
                .email("user@user.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.USER)
                .profilePicture("https://unsplash.com/photos/grayscale-photo-of-man-c_GmwfHBDzk?utm_content=creditShareLink&utm_medium=referral&utm_source=unsplash")
                .build();

        var user = userRepositoryTest.save(testUser);
        Optional<User> foundUser = userRepositoryTest.findById(user.getId());
        assertThat(foundUser).isPresent();
//        assertThat(user).isEqualTo(foundUser);
    }
}
