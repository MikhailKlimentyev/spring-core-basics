package service.impl;

import java.util.ArrayList;
import java.util.List;

import model.Event;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            this.writeEventsFromCache();
            cache.clear();
        }
    }

    private void destroy() {
        if (!cache.isEmpty()) {
            this.writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }
}
