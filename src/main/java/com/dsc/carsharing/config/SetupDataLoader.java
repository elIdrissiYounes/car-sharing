package com.dsc.carsharing.config;

import com.dsc.carsharing.model.Administrator;
import com.dsc.carsharing.model.User;
import com.dsc.carsharing.repositories.AdministratorRepository;
import com.dsc.carsharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        System.out.println("okok");
        if (alreadySetup) {
            System.out.println("vu");
            return;
        }
        System.out.println("dada");
        createAdmin();
        createUser();
        alreadySetup = true;
    }

    private void createAdmin() {
        String username = "app-admin";
        Administrator administrator = administratorRepository.findByUsername(username);
        if (administrator == null) {
            Administrator admin = new Administrator();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setEmail("admin@admin.com");
            admin.setUsername(username);
            admin.setEnabled(true);
            admin.setPassword(passwordEncoder.encode("administrator12345"));
            administratorRepository.save(admin);
        }
    }

    private void createUser() {
        String username = "toto";
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setFirstName("toto");
            user.setLastName("toto");
            user.setEmail("toto@carsharing.com");
            user.setUsername(username);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode("toto"));
            userRepository.save(user);
        }
    }

}