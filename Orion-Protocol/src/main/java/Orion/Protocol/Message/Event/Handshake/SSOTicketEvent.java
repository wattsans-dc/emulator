package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Habbo.Provider.IHabboLoginProvider;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Hanshake.SSOTicketEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.concurrent.locks.ReentrantLock;

@Singleton
@HandshakeEvent
public class SSOTicketEvent implements IMessageEventHandler {
    @Inject
    private SSOTicketEventParser parser;

    @Inject
    private IHabboLoginProvider loginProvider;

    @Inject
    private IThreadManager threadManager;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public int getId() {
        return EventHeaders.SSOTicketEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        this.threadManager.getHabboLoginExecutor().submit(() -> {
            if(!this.loginProvider.canLogin(session, this.parser.ticket)) {
                return;
            }

            this.loginProvider.attemptLogin(session, this.parser.ticket);
        });
    }
}
