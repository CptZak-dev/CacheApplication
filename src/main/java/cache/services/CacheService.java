package cache.services;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public void put(String key, Object value, long ttlMillis) {
        long expiryTime = System.currentTimeMillis() + ttlMillis;
        cache.put(key, new CacheEntry(value, expiryTime));
    }

    public Object get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null || System.currentTimeMillis() > entry.expiryTime) {
            cache.remove(key);
            return null;
        }
        return entry.value;
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clearExpiredEntries() {
        long now = System.currentTimeMillis();
        cache.entrySet().removeIf(entry -> entry.getValue().expiryTime < now);
    }

    private static class CacheEntry {
        Object value;
        long expiryTime;

        CacheEntry(Object value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }
}
