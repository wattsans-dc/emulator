package Orion.Protocol.Message.Event;

public abstract class EventHeaders {
    // Handshake
    public static final int ClientHelloEvent = 4000;
    public static final int MachineIDEvent = 2490;
    public static final int SSOTicketEvent = 2419;

    // Lifecycle
    public static final int PingEvent = 295;
    public static final int PongEvent = 2596;
    public static final int MemoryPerformanceEvent = 3230;

    // Habbo
    public static final int RequestHabboDataEvent = 357;
    public static final int RequestHabboProfileEvent = 3265;
    public static final int RequestHabboCurrenciesEvent = 273;
    public static final int RequestNavigatorSettingsEvent = 1782;
    public static final int RequestEntityWalkEvent = 3320;

    // Messenger
    public static final int InitializeMessengerEvent = 2781;
    public static final int RequestMessengerFriendRequestsEvent = 2448;

    // Achievement
    public static final int RequestAchievementsEvent = 219;

    // Camera
    public static final int RequestCameraConfigurationEvent = 796;

    // HotelView
    public static final int RequestPromoArticlesEvent = 1827;

    // Navigator
    public static final int RequestNavigatorDataEvent = 2110;
    public static final int RequestNavigatorRoomsEvent = 249;
    public static final int RequestCanCreateRoomEvent = 2128;

    // Room
    public static final int JoinRoomEvent = 685;
    public static final int RequestRoomLoadEvent = 2312;
    public static final int RequestRoomCategoriesEvent = 3027;
    public static final int RequestRoomDataEvent = 2230;
    public static final int RequestHeightmapEvent = 3898;
    public static final int RequestRoomHeightmapEvent = 2300;
}
