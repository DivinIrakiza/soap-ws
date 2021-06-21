package com.rca.studies.spring.soapws.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    private static final Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void initialize() {

        User user1 = new User();
        user1.setName("Peter");
        user1.setEmpId(1111);
        user1.setSalary(12000);

        User user2 = new User();
        user2.setName("Sam");
        user2.setEmpId(1112);
        user2.setSalary(32000);

        User user3 = new User();
        user3.setName("Ryan");
        user3.setEmpId(1113);
        user3.setSalary(16000);

        users.put(user1.getName(), user1);
        users.put(user2.getName(), user2);
        users.put(user3.getName(), user3);
    }
}
