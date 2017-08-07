package digital.distribute.games.websocket;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@ServerEndpoint("/newRevueWebSocket/{gameId}")
public class RevueWebSocket {
    @Inject
    private Logger logger;

    private static final Map<Session, Subscriber> test = new HashMap<Session, Subscriber>();
    private static final String TOPIC_NAME = "pub";
    private static final String PUB_NAME = "pub";
    private static final String SUB_NAME = "sub";
    private static final String NO_GREETING = "no greeting";

    private static int i = 0;

    private Publisher publisher;

    @OnOpen
    public void onOpen(@PathParam("gameId") String gameId, Session session) throws JMSException {
        logger.info(session.getId() + " has opened a connection");

        createPublisher(gameId);
        createSubscibers(session, gameId);
        i++;

        session.getAsyncRemote().sendText("Connection Established");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws JMSException, IOException {
        logger.info("Message from " + session.getId() + ": " + message);

        sendRevue(message);

        getMessageOnSubscribe();
    }

    @OnClose
    public void onClose(Session session) throws JMSException {
        logger.info("Session " +session.getId()+" has ended");

        closeAll(session);
    }

    private void createPublisher(String gameId) throws JMSException {
        publisher = new Publisher();
        publisher.create(PUB_NAME + i, gameId);
    }

    private void createSubscibers(Session session, String gameId) throws JMSException {
        Subscriber sub = new Subscriber();
        sub.create(SUB_NAME + i, gameId);

        test.put(session, sub);
    }

    private void sendRevue(String message) throws JMSException {
        publisher.sendName(message);
    }


    private void getMessageOnSubscribe() throws JMSException {
        for (Map.Entry<Session, Subscriber> entry : test.entrySet()) {
            Session session = entry.getKey();
            Subscriber subscriber = entry.getValue();
            String revue = subscriber.getGreeting(1000);
            logger.info("Session: " + session.getId() + " with message: " + revue);
            if(session.isOpen() && !revue.equals(NO_GREETING)){
                session.getAsyncRemote().sendText(revue);
            }
        }
    }

    private void closeAll(Session session) throws JMSException {
        publisher.closeConnection();

        for (Map.Entry<Session, Subscriber> entry : test.entrySet()) {
            Session s = entry.getKey();
            if(session == s){
                Subscriber sub = entry.getValue();
                sub.closeConnection();
                test.remove(entry.getKey());
            }
        }
    }
}
