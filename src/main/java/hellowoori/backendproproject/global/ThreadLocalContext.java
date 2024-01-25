package hellowoori.backendproproject.global;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT_DATA = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public static void initContext(String type) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", type);
        CONTEXT_DATA.set(data);
    }

    public static void put(String key, Object value) {
        CONTEXT_DATA.get().put(key, value);
    }

    public static Object get(String key) {
        return CONTEXT_DATA.get().get(key);
    }

    public static void clearContext() {
        CONTEXT_DATA.set(new HashMap<>());
    }
}