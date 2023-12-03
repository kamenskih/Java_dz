package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private static Map<Class<Event>, List<EventListener>> listeners = new HashMap<>();

    private static volatile EventManager instance;

//    private EventManager() {
//    }
//
//    public static EventManager getInstance() {
//        if (instance == null) {
//            synchronized (EventManager.class) {
//                if (instance == null) {
//                    instance = new EventManager();
//                }
//            }
//        }
//        return instance;
//    }

    public static <T extends Event> void subscribe(Class<T> eventType, EventListener listener) {
        fillIfEmpty((Class<Event>) eventType);
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public static <T extends Event> void unsubscribe(Class<T> eventType, EventListener listener) {
        fillIfEmpty((Class<Event>) eventType);
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public static void notify(Event eventType) {
        fillIfEmpty((Class<Event>) eventType.getClass());
        List<EventListener> users = listeners.get(eventType.getClass());
        for (EventListener listener : users) {
            listener.update(eventType);
        }
    }

    private static void fillIfEmpty(Class<Event> eventType) {
        if (!listeners.containsKey(eventType)) {
            listeners.put(eventType, new ArrayList<>());
        }
    }
}
