import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import model.Client;
import model.Event;
import model.EventTypes;
import service.api.IEventLogger;

public class App {
    //TODO: Define Context using Java code; Configuration app with annotations.
    //https://www.youtube.com/watch?v=cIYUAKDnFo4&list=PL6jg6AGdCNaWF-sUH2QDudBRXo54zuN1t&index=9

    private Client client;

    private IEventLogger defaultLogger;

    private Map<EventTypes, IEventLogger> eventTypeLoggerMap;

    private String startupMessage;

    public App(Client client, IEventLogger eventLogger, Map<EventTypes, IEventLogger> eventTypeLoggerMap) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.eventTypeLoggerMap = eventTypeLoggerMap;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvents(ctx);
        ctx.close();
    }

    private void logEvents(ApplicationContext ctx) {
        Event event = ctx.getBean(Event.class);
        logEvent(EventTypes.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventTypes.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventTypes.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventTypes.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        logEvent(null, event, "Some event for 3");
    }

    private void logEvent(EventTypes eventTypes, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        IEventLogger logger = eventTypeLoggerMap.get(eventTypes);
        if (null == logger) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    public IEventLogger getDefaultLogger() {
        return defaultLogger;
    }

    public void setStartupMessage(String startupMessage) {
        this.startupMessage = startupMessage;
    }
}
