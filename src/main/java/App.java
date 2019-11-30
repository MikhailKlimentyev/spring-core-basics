import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Client;
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
        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

    private void logEvent(String msg) {
        String newMsg = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.eventLogger(newMsg);
    }
}
