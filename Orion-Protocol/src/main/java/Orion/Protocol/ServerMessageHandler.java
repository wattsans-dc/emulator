package Orion.Protocol;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Api.Protocol.Message.Event.IMessageEventProvider;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Annotations.HandshakeEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class ServerMessageHandler implements IServerMessageHandler {
    private final Logger logger = LogManager.getLogger();

    private final IMessageEventProvider messageEventProvider;

    private final ISessionManager sessionManager;

    @Inject
    public ServerMessageHandler(
            final IMessageEventProvider messageEventProvider,
            final ISessionManager sessionManager
    ) {
        this.messageEventProvider = messageEventProvider;
        this.sessionManager = sessionManager;
    }

    @Override
    public void handle(ISession session, IMessageEvent message) {
        final short headerId = message.getId();

        try {
            final IMessageEventHandler messageEventHandler = this.messageEventProvider.getMessageEventByHeaderId(headerId);

            if(messageEventHandler == null) {
                //this.logger.warn(STR."[\{headerId}] Received message with unknown header.");
                return;
            }

            if(!session.isAuthenticated() && !messageEventHandler.getClass().isAnnotationPresent(HandshakeEvent.class)) {
                //this.logger.warn(STR."[\{headerId}] Received handshake event without being authenticated from [\{session.getIpAddress()}] address.");
                this.sessionManager.disposeSession(session);
                return;
            }

            //this.logger.debug(STR.">> Handling Event [\{message.getId()}] \{messageEventHandler.getClass().getSimpleName()}");

            messageEventHandler.handle(message, session);
        } catch (Exception e) {
            this.logger.error(STR."Error handling message: \{message} with header: \{headerId}", e);
        } finally {
            message.getBuffer().release();
        }
    }
}
