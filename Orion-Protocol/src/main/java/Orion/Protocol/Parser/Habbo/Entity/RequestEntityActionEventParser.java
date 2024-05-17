package Orion.Protocol.Parser.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.EntityActionType;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityActionEventParser implements IEventParser {
    public EntityActionType actionType;

    @Override
    public int getId() {
        return EventHeaders.RequestEntityActionEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.actionType = EntityActionType.fromValue(messageEvent.readInt());
    }
}
