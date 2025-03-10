package Orion.Game.Room.Object.Item.Interaction.Floor;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Util.Position;
import Orion.Game.Room.Object.Item.Composition.InteractionName;
import Orion.Game.Room.Object.Item.Interaction.RoomItemInteraction;

@InteractionName("seat_floor")
public class SeatFloorItem extends RoomItemInteraction {
    private final IRoomFloorItem item;

    public SeatFloorItem(final IRoomFloorItem item) {
        this.item = item;
    }

    @Override
    public void onEntityEnter(IRoomEntity entity) {
        boolean sitting = false;

        for (final Position position : this.item.getAffectedPositions()) {
            if(!position.equals(entity.getPosition())) continue;

            entity.sit(item.getDefinition().getStackHeight(), item.getData().getRotation());
            sitting = true;
            break;
        }

        if(sitting) {
            entity.setNeedsUpdate(true);
        }
    }

    @Override
    public void onEntityLeave(IRoomEntity entity) {
        entity.removeStatus(RoomEntityStatus.SIT);
        entity.setNeedsUpdate(true);
    }
}
