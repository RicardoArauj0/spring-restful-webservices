package com.springboot.restfullwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();

    private static int  usersCount = 3;

    static  {
        users.add(new User(1,"Aram", new Date()));
        users.add(new User(2,"Eva", new Date()));
        users.add(new User(3,"Jhon", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> i = users.iterator();

        while (i.hasNext()) {
            User user = i.next();
            if (user.getId() == id) {
                i.remove();
                return user;
            }
        }
        return null;
    }
}
