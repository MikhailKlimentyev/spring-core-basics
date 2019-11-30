package service.impl;

import service.api.IEventLogger;

public class ConsoleEventLogger implements IEventLogger {

    @Override
    public void eventLogger(String msg) {
        System.out.println(msg);
    }
}
