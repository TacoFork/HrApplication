package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void run(String...args){
        User admin = new User("admin", "admin", true);
        Role adminRole = new Role("admin", "ROLE_ADMIN");

        userRepository.save(admin);
        roleRepository.save(adminRole);

        User user = new User("user", "user", true);
        Role userRole = new Role("user", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);


//        Employee employee1 = new Employee("rick@domain.com", "Rick", "Sanchez", "Human Relations");
//        Employee employee2 = new Employee("morty@domain.com", "Morty", "Smith", "Human Relations");
//        Employee employee3 = new Employee("summer@domain.com", "Summer", "Smith", "Human Relations");
//
//        Department hr = new Department("Human Relations");
//        hr.addEmployee(employee1);
//        hr.addEmployee(employee2);
//        hr.addEmployee(employee3);

    }
}
