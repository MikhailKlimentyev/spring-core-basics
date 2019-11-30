package service.api;

import model.Event;

public interface IEventLogger {

    void logEvent(Event event);
}
