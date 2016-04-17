package rest;

import rest.UserProfile;

import java.util.*;

class Pair<T1, T2> {
    T1 object1;
    T2 object2;

    Pair(T1 one, T2 two) {
        object1 = one;
        object2 = two;
    }

    public T1 getFirst() { return object1; }

    public T2 getSecond() {
        return object2;
    }
}

public class SessionService {
    private Map<String, Pair<UserProfile, String>> currentSessions = new HashMap<>();
    private Map<UserProfile, ArrayList<String>> userSessions = new HashMap<>();
    public SessionService() {

    }

    public void openSession(String id, UserProfile user, String ip) {
        Pair<UserProfile, String> pair = new Pair<UserProfile, String>(user, ip);
        if (!userSessions.containsKey(user)) {
            userSessions.put(user, new ArrayList<String>());
        }
        for (int i = 0; i < userSessions.get(user).size(); ++i) {
            if (currentSessions.get(userSessions.get(user).get(i)).getSecond() != ip )
                closeSession(userSessions.get(user).get(i));
        }
        userSessions.get(user).add(id);
        currentSessions.put(id, pair);
    }

    public void closeSession(String id) {
        if (currentSessions.get(id) != null) {
            userSessions.get(currentSessions.get(id).getFirst()).remove(id);
            currentSessions.remove(id);

        }
    }


    public Pair<UserProfile, String> getSessionData(String id) {
        return currentSessions.get(id);
    }
}
