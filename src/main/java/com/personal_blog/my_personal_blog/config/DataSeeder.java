package com.personal_blog.my_personal_blog.config;

import com.personal_blog.my_personal_blog.shared.enums.Role;
import com.personal_blog.my_personal_blog.user.UserModel;
import com.personal_blog.my_personal_blog.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${seeder.admin.email}")
    private String adminEmail;

    @Value("${seeder.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception{
        seedAdminUser();
    }

    private void seedAdminUser() {
        if(userRepository.findByEmail(adminEmail).isEmpty()){
            UserModel adminUser = new UserModel();
            adminUser.setName("Admin");
            adminUser.setEmail(adminEmail);

            adminUser.setPassword(passwordEncoder.encode(adminPassword));

            adminUser.setRoles(Set.of(Role.ROLE_ADMIN));

            userRepository.save(adminUser);

            logger.info("Usuario ADMIN padrão criado com sucesso. Email: {}, senha: {}",adminEmail, adminPassword);
        } else {
            logger.info("Usuario ADMIN padrão já existe. Nenhuma ação necessária.{} - {}", adminEmail, adminPassword);
        }
    }

}
