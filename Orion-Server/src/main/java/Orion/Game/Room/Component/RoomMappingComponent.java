package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Room.Component.IRoomMappingComponent;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Util.Position;
import Orion.Game.Room.Data.Model.RoomTile;

public class RoomMappingComponent implements IRoomMappingComponent {
    private final IRoom room;

    private IRoomTile[][] tiles;

    public RoomMappingComponent(
            final IRoom room
    ) {
        this.room = room;
    }

    @Override
    public void initialize() {
        if(this.room.getModel() == null) {
            System.out.println("Room model is null");
            return;
        }

        final int sizeX = this.room.getModel().getMapSizeX();
        final int sizeY = this.room.getModel().getMapSizeY();

        this.tiles = new IRoomTile[sizeX][sizeY];

        for(int x = 0; x < sizeX; x++) {
            final IRoomTile[] xRow = new RoomTile[sizeY];

            for(int y = 0; y < sizeY; y++) {
                final IRoomTile tile = new RoomTile(this, new Position(x, y));

                // TODO: init tile

                xRow[y] = tile;
            }

            this.tiles[x] = xRow;
        }
    }

    @Override
    public IRoomTile getTile(final Position position) {
        return this.tiles[position.getX()][position.getY()];
    }

    @Override
    public IRoomTile getTile(final int x, final int y) {
        return this.tiles[x][y];
    }

    @Override
    public void dispose() {

    }
}
