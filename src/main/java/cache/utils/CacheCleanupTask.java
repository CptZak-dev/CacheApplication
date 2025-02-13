package cache.utils;

import cache.services.CacheService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheCleanupTask {
    private final CacheService cacheService;

    public CacheCleanupTask(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Scheduled(fixedRate = 60000) // Exécute toutes les 60 secondes
    public void cleanUpCache() {
        cacheService.clearExpiredEntries();
    }
}
