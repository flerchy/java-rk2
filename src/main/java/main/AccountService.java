package main;

import rest.UserProfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Map<String, UserProfile> users = new HashMap<>();

    public AccountService() {
        users.put("admin", new UserProfile("admin", "12345", "admin@mail.ru"));
        users.put("guest", new UserProfile("guest", "12345", "guest@mail.ru"));
    }

    public Collection<UserProfile> getAllUsers() {
        return users.values();
    }

    public boolean addUser(String userName, UserProfile userProfile) {
        if (users.containsKey(userName))
            return false;
        users.put(userName, userProfile);
        return true;
    }

    public UserProfile getUser(String userName) {
        return users.get(userName);
    }
}
