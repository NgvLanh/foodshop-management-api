package com.edu.java6asm.config;

import com.edu.java6asm.enums.Roles;
import com.edu.java6asm.model.User;
import com.edu.java6asm.repository.UserRepository;
import com.edu.java6asm.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Roles.ADMIN.name());
                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(PasswordUtil.hashPassword("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.info("===>>> Default user has been created with default password: admin, please change it.");
            }
        };
    }
}
