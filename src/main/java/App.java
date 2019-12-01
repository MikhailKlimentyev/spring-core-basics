import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import model.Client;
import model.Event;
import model.EventType;
import service.api.IEventLogger;

public class App {

    private Client client;

    private IEventLogger defaultLogger;

    private Map<EventType, IEventLogger> eventTypeLoggerMap;

    public App(Client client, IEventLogger eventLogger, Map<EventType, IEventLogger> eventTypeLoggerMap) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.eventTypeLoggerMap = eventTypeLoggerMap;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        IEventLogger logger = eventTypeLoggerMap.get(eventType);
        if (null == logger) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
