package com.rca.studies.spring.soapws.services;

import org.springframework.stereotype.Service;
import studies.rca.com.soap.spring.soapws.User;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static final Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void initialize() {

        User user1 = new User();
        user1.setId(1);
        user1.setName("User 1");
        user1.setSalary(12000);

        User user2 = new User();
        user2.setId(2);
        user2.setName("User 2");
        user2.setSalary(32000);

        User user3 = new User();
        user3.setId(3);
        user3.setName("User 3");
        user3.setSalary(16000);

        users.put(user1.getName(), user1);
        users.put(user2.getName(), user2);
        users.put(user3.getName(), user3);
    }
    public User getUsers(String name) {
        return users.get(name);
    }
}
