import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import model.Client;
import model.Event;
import model.EventTypes;
import service.api.IEventLogger;

public class App {

    private Client client;

    private IEventLogger defaultLogger;

    private Map<EventTypes, IEventLogger> eventTypeLoggerMap;

    public App(Client client, IEventLogger eventLogger, Map<EventTypes, IEventLogger> eventTypeLoggerMap) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.eventTypeLoggerMap = eventTypeLoggerMap;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventTypes.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventTypes.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventTypes.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventTypes.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
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
}
