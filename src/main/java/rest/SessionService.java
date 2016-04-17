package rest;

import rest.UserProfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
    private Map<String, String> currentIpSessions = new HashMap<>();

    public SessionService() {

    }

    public void openSession(String id, UserProfile user, String ip) {
        Pair<UserProfile, String> pair = new Pair<UserProfile, String>(user, ip);
        if (currentIpSessions.get(ip) == null) {
            currentIpSessions.put(ip, id);
            currentSessions.put(id, pair);
        } else {
            currentIpSessions.remove(ip);
            currentSessions.remove(id);
            currentIpSessions.put(ip, id);
            currentSessions.put(id, pair);
        }
    }

    public void closeSession(String id) {
        currentIpSessions.remove(currentSessions.get(id).getSecond());
        currentSessions.remove(id);
    }

    public Pair<UserProfile, String> getSessionData(String id) {
        return currentSessions.get(id);
    }
}
