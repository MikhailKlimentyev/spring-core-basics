import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Client;
import model.Event;
import service.api.IEventLogger;

public class App {

    private Client client;
    private IEventLogger eventLogger;

    public App(Client client, IEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent(ctx, "Some event for user 1");
        app.logEvent(ctx, "Some event for user 2");
    }

    private void logEvent(ApplicationContext ctx, String msg) {
        Event event = ctx.getBean(Event.class);
        String newMsg = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(newMsg);
        eventLogger.eventLogger(event);
    }
}
