package com.cometproject.server.network.messages.headers;

import javolution.util.FastMap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;


public class Composers {
    public static final short InitCryptoMessageComposer = 3523;
    public static final short SecretKeyMessageComposer = 893;
    public static final short AuthenticationOKMessageComposer = 97;
    public static final short UniqueMachineIDMessageComposer = 1790;
    public static final short HomeRoomMessageComposer = 315;
    public static final short MinimailCountMessageComposer = 1714;
    public static final short FavouriteRoomsMessageComposer = 1974;
    public static final short EffectsInventoryMessageComposer = 2687;
    public static final short UserClubRightsMessageComposer = 757;
    public static final short EnableNotificationsMessageComposer = 114;
    public static final short EnableTradingMessageComposer = 1086;
    public static final short ActivityPointsMessageComposer = 343;
    public static final short LoadModerationToolMessageComposer = 2851;
    public static final short UserObjectMessageComposer = 532;
    public static final short SendPerkAllowancesMessageComposer = 2903;
    public static final short CreditsBalanceMessageComposer = 2835;
    public static final short SubscriptionStatusMessageComposer = 518;
    public static final short LandingWidgetMessageComposer = 2684;
    public static final short LandingPromosMessageComposer = 3695;
    public static final short FlatCategoriesMessageComposer = 1531;
    public static final short OfficialRoomsMessageComposer = 1122;
    public static final short NavigatorListingsMessageComposer = 2825;
    public static final short CanCreateRoomMessageComposer = 2737;
    public static final short PopularRoomTagsMessageComposer = 3274;
    public static final short CatalogIndexMessageComposer = 151;
    public static final short CatalogPageMessageComposer = 415;
    public static final short CatalogOfferMessageComposer = 3381;
    public static final short CatalogueOfferConfigMessageComposer = 1732;
    public static final short PurchaseOKMessageComposer = 1475;
    public static final short CatalogLimitedItemSoldOutMessageComposer = 2668;
    public static final short SellablePetBreedsMessageComposer = 3770;
    public static final short GiftWrappingConfigurationMessageComposer = 1966;
    public static final short CatalogPromotionGetRoomsMessageComposer = 2555;
    public static final short AchievementPointsMessageComposer = 3110;
    public static final short HeightMapMessageComposer = 1454;
    public static final short FloorMapMessageComposer = 1768;
    public static final short HasOwnerRightsMessageComposer = 1204;
    public static final short SetRoomUserMessageComposer = 3753;
    public static final short UpdateUserStatusMessageComposer = 3128;
    public static final short RoomSpacesMessageComposer = 2251;
    public static final short RoomErrorMessageComposer = 487;
    public static final short RoomDataMessageComposer = 1231;
    public static final short RoomEnterErrorMessageComposer = 3820;
    public static final short RoomEventMessageComposer = 241;
    public static final short RoomFloorItemsMessageComposer = 529;
    public static final short RoomFloorWallLevelsMessageComposer = 103;
    public static final short RoomGroupMessageComposer = 1907;
    public static final short RoomOwnershipMessageComposer = 2101;
    public static final short RoomRatingMessageComposer = 1118;
    public static final short RoomRightsLevelMessageComposer = 2004;
    public static final short RoomForwardMessageComposer = 3581;
    public static final short GroupRoomMessageComposer = 3646;
    public static final short OnCreateRoomInfoMessageComposer = 1361;
    public static final short RoomUpdateMessageComposer = 1919;
    public static final short RoomWallItemsMessageComposer = 1276;
    public static final short RoomUserActionMessageComposer = 3685;
    public static final short RoomUserIdleMessageComposer = 2858;
    public static final short ApplyHanditemMessageComposer = 314;
    public static final short ChatMessageComposer = 803;
    public static final short ShoutMessageComposer = 1419;
    public static final short WhisperMessageComposer = 1430;
    public static final short DanceStatusMessageComposer = 1362;
    public static final short DoorbellMessageComposer = 3104;
    public static final short DoorbellNoOneMessageComposer = 2149;
    public static final short DoorbellOpenedMessageComposer = 2590;
    public static final short UserProfileMessageComposer = 3031;
    public static final short AddFloorItemMessageComposer = 3183;
    public static final short AddWallItemMessageComposer = 2400;
    public static final short DimmerDataMessageComposer = 3457;
    public static final short TypingStatusMessageComposer = 989;
    public static final short LoadInventoryMessageComposer = 37;
    public static final short ItemAnimationMessageComposer = 215;
    public static final short NewInventoryObjectMessageComposer = 1006;
    public static final short PickUpFloorItemMessageComposer = 1899;
    public static final short PickUpWallItemMessageComposer = 1596;
    public static final short UpdateInventoryMessageComposer = 3351;
    public static final short UpdateFloorItemExtraDataMessageComposer = 931;
    public static final short UpdateRoomItemMessageComposer = 1459;
    public static final short UpdateRoomWallItemMessageComposer = 853;
    public static final short UpdateUserDataMessageComposer = 3464;
    public static final short UserBadgesMessageComposer = 2711;
    public static final short UserLeftRoomMessageComposer = 658;
    public static final short RelationshipMessageComposer = 1485;
    public static final short RemoveInventoryObjectMessageComposer = 2369;
    public static final short LoadBadgesWidgetMessageComposer = 2569;
    public static final short ModerationRoomToolMessageComposer = 2067;
    public static final short ModerationToolRoomVisitsMessageComposer = 1587;
    public static final short ModerationToolUserChatlogMessageComposer = 3582;
    public static final short ModerationToolUserToolMessageComposer = 433;
    public static final short ModerationToolRoomChatlogMessageComposer = 3827;
    public static final short ConsoleChatMessageComposer = 793;
    public static final short ConsoleInvitationMessageComposer = 1551;
    public static final short ConsoleSearchFriendMessageComposer = 909;
    public static final short ConsoleSendFriendRequestMessageComposer = 1932;
    public static final short AlertNotificationMessageComposer = 1688;
    public static final short BotInventoryMessageComposer = 1346;
    public static final short BotSpeechListMessageComposer = 3323;
    public static final short ApplyEffectMessageComposer = 3074;
    public static final short FloodFilterMessageComposer = 3180;
    public static final short PetInfoMessageComposer = 830;
    public static final short PetInventoryMessageComposer = 649;
    public static final short LoadFriendsMessageComposer = 168;
    public static final short QuestListMessageComposer = 3562;
    public static final short OutOfRoomMessageComposer = 913;
    public static final short MOTDNotificationMessageComposer = 2534;
    public static final short RespectPetMessageComposer = 0;
    public static final short PetRespectNotificationMessageComposer = 443;
    public static final short LoadVolumeMessageComposer = 2967;
    public static final short LoadWardrobeMessageComposer = 1618;
    public static final short PingMessageComposer = 3972;
    public static final short PublishShopMessageComposer = 1639;
    public static final short LoadPostItMessageComposer = 3524;
    public static final short OpenGiftMessageComposer = 2272;
    public static final short RemoveRightsMessageComposer = 867;
    public static final short LoadRoomRightsListMessageComposer = 798;
    public static final short RoomBannedListMessageComposer = 3997;
    public static final short RoomSettingsDataMessageComposer = 1113;
    public static final short SaveWiredMessageComposer = 3283;
    public static final short FriendRequestsMessageComposer = 3389;
    public static final short FriendUpdateMessageComposer = 3335;
    public static final short GiveRespectsMessageComposer = 1447;
    public static final short GiveRoomRightsMessageComposer = 6;
    public static final short UpdateAvatarAspectMessageComposer = 2551;
    public static final short YouTubeLoadPlaylistsMessageComposer = 1225;
    public static final short YouTubeLoadVideoMessageComposer = 3247;
    public static final short WiredConditionMessageComposer = 1612;
    public static final short WiredEffectMessageComposer = 178;
    public static final short WiredTriggerMessageComposer = 3016;
    public static final short ChangeFavouriteGroupMessageComposer = 492;
    public static final short GroupDataEditMessageComposer = 1572;
    public static final short GroupDataMessageComposer = 2527;
    public static final short GroupFurniturePageMessageComposer = 1960;
    public static final short GroupMembersMessageComposer = 3717;
    public static final short GroupPurchasePageMessageComposer = 2768;
    public static final short GroupPurchasePartsMessageComposer = 3161;
    public static final short CheckPetNameMessageComposer = 1073;
    public static final short CitizenshipStatusMessageComposer = 1246;
    public static final short TradeAcceptMessageComposer = 1827;
    public static final short TradeCloseMessageComposer = 3670;
    public static final short TradeCompletedMessageComposer = 3757;
    public static final short TradeConfirmationMessageComposer = 3063;
    public static final short TradeStartMessageComposer = 2538;
    public static final short TradeUpdateMessageComposer = 1165;
    public static final short PollQuestionsMessageComposer = 2525;
    public static final short GetFloorPlanUsedCoordsMessageComposer = 3845;
    public static final short SetFloorPlanDoorMessageComposer = 1381;
    public static final short BuildersClubMembershipMessageComposer = 296;
    public static final short EnableRoomInfoMessageComposer = 1919;

    public static final short WiredRewardMessageComposer = 2640;
    public static final short MaintenanceNotificationMessageComposer = 431;
    public static final short AdvancedAlertMessageComposer = 2273;
    public static final short TradeErrorMessageComposer = 2483;
    public static final short RoomConnectionErrorMessageComposer = 3820;
    public static final short RoomPlayerUnbannedMessageComposer = 2215;
    public static final short UpdateIgnoreStatusMessageComposer = 1996;
    public static final short GroupFurnitureWidgetMessageComposer = 1674;
    public static final short CameraTokenMessageComposer = 2137;
    public static final short InitHelpToolMessageComposer = 1140;
    public static final short TicketSentMessageComposer = 1082;
    public static final short LogoutMessageComposer = 4000;
    public static final short GiftUserNotFoundMessageComposer = 2517;
    public static final short RoomNotificationMessageComposer = 2273;
    public static final short MutedMessageComposer = 3871;

    private static Map<Short, String> composerNames = new FastMap<>();

    static {
        try {
            for (Field field : Composers.class.getDeclaredFields()) {
                if (!Modifier.isPrivate(field.getModifiers()))
                    composerNames.put(field.getShort(field.getName()), field.getName());
            }
        } catch (Exception ignored) {

        }
    }

}
