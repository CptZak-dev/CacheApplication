package cache;

import cache.services.CacheService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    @Test
    void testCache() throws InterruptedException {
        CacheService cacheService = new CacheService();
        cacheService.put("testKey", "testValue", 1000);
        assertEquals("testValue", cacheService.get("testKey"));

        Thread.sleep(1100);
        assertNull(cacheService.get("testKey"));
    }
}
