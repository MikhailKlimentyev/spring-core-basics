package service.impl;

import java.util.Collection;
import java.util.Collections;

import model.Event;
import service.api.IEventLogger;

public class CombinedEventLogger implements IEventLogger {

    private final Collection<IEventLogger> loggers;

    public CombinedEventLogger(Collection<IEventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (IEventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

    public Collection<IEventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
    }
}
