package Orion.Game.Room.Object.Item;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.Data.IRoomItemData;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.Interaction.IRoomItemInteraction;
import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Room.Object.Item.Data.RoomItemData;
import Orion.Writer.Room.Object.Item.RoomFloorItemWriter;
import gnu.trove.map.hash.THashMap;

public class RoomFloorItem implements IRoomFloorItem {
    private final IRoom room;

    private final int virtualId;

    private THashMap<String, Object> attributes;

    private final IRoomItemData data;

    private final IRoomItemInteraction interaction;

    private final IItemDefinition definition;

    public RoomFloorItem(
            final int virtualId,
            final IRoom room,
            final IConnectionResult data,
            final IRoomItemInteraction interaction,
            final IItemDefinition definition
    ) {
        this.virtualId = virtualId;

        this.room = room;
        this.definition = definition;
        this.interaction = interaction;

        this.data = new RoomItemData(data);
    }

    @Override
    public IRoom getRoom() {
        return this.room;
    }

    @Override
    public int getVirtualId() {
        return this.virtualId;
    }

    @Override
    public Position getPosition() {
        return this.data.getPosition();
    }

    @Override
    public void setPosition(Position position) {
        // TODO: Implement this
    }

    @Override
    public IRoomItemData getData() {
        return this.data;
    }

    @Override
    public IItemDefinition getDefinition() {
        return this.definition;
    }

    @Override
    public int getRotation() {
        return this.data.getRotation();
    }

    @Override
    public void setRotation(int rotation) {
        // Implement this
    }

    @Override
    public void setAttribute(String key, Object value) {
        this.startAttributes();

        if(this.attributes.containsKey(key)) {
            this.attributes.replace(key, value);
            return;
        }

        this.attributes.put(key, value);
    }

    @Override
    public Object getAttribute(String key) {
        this.startAttributes();

        return this.attributes.get(key);
    }

    @Override
    public boolean hasAttribute(String key) {
        this.startAttributes();

        return this.attributes.containsKey(key);
    }

    @Override
    public void removeAttribute(String key) {
        this.startAttributes();

        this.attributes.remove(key);
    }

    private void startAttributes() {
        if(this.attributes != null) return;

        this.attributes = new THashMap<>();
    }

    @Override
    public void write(final IMessageComposer composer) {
        RoomFloorItemWriter.write(this, composer);
    }
}
