package cache.controller;

import cache.services.CacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/put")
    public void put(@RequestParam String key, @RequestParam String value, @RequestParam long ttl) {
        cacheService.put(key, value, ttl);
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return cacheService.get(key);
    }

    @DeleteMapping("/remove")
    public void remove(@RequestParam String key) {
        cacheService.remove(key);
    }
}
