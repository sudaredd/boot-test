package app.boot.boottest.service;

import app.boot.boottest.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    Map<String, User> map = new ConcurrentHashMap<>();

    public List<User> getAllUsers() {
        return new ArrayList<>(map.values());
    }

    public User getUserByUsername(String username) {
        return map.get(username);
    }

    public void storeNewUser(User user) {
       map.put(user.getUsername(), user);
    }
}
