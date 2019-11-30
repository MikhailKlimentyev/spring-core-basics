package service.impl;

import model.Event;
import service.api.IEventLogger;

public class ConsoleEventLogger implements IEventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
