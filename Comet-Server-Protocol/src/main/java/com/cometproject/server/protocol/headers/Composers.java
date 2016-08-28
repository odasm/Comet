package com.cometproject.server.protocol.headers;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class Composers {
    public static final short HeightMapMessageComposer = 1801;
    public static final short CallForHelpPendingCallsMessageComposer = 1114;
    public static final short ChatMessageComposer = 3816;
    public static final short GroupMembersMessageComposer = 316;
    public static final short OpenBotActionMessageComposer = 1260;
    public static final short UserObjectMessageComposer = 40;
    public static final short ActionMessageComposer = 2165;
    public static final short ManageGroupMessageComposer = 3294;
    public static final short FloodControlMessageComposer = 96;
    public static final short FlatControllerAddedMessageComposer = 3388;
    public static final short TradingClosedMessageComposer = 906;
    public static final short FlatCreatedMessageComposer = 362;
    public static final short ScrSendUserInfoMessageComposer = 284;
    public static final short CheckPetNameMessageComposer = 1285;
    public static final short QuestAbortedMessageComposer = 1797;
    public static final short RespectPetNotificationMessageComposer = 123;
    public static final short PromotableRoomsMessageComposer = 3814;
    public static final short CloseConnectionMessageComposer = 3422;
    public static final short CfhTopicsInitMessageComposer = 946;
    public static final short WiredEffectConfigMessageComposer = 3113;
    public static final short FriendListUpdateMessageComposer = 504;
    public static final short ObjectAddMessageComposer = 459;
    public static final short NavigatorCollapsedCategoriesMessageComposer = 3928;
    public static final short RoomRightsListMessageComposer = 1728;
    public static final short TradingUpdateMessageComposer = 1719;
    public static final short CarryObjectMessageComposer = 3176;
    public static final short NewGroupInfoMessageComposer = 910;
    public static final short RoomForwardMessageComposer = 2082;
    public static final short GroupFurniSettingsMessageComposer = 1922;
    public static final short CreditBalanceMessageComposer = 2866;
    public static final short CatalogUpdatedMessageComposer = 3185;
    public static final short UserTypingMessageComposer = 3991;
    public static final short ObjectRemoveMessageComposer = 3219;
    public static final short RoomEntryInfoMessageComposer = 2659;
    public static final short CatalogOfferMessageComposer = 3576;
    public static final short CatalogIndexMessageComposer = 2596;
    public static final short ThreadsListDataMessageComposer = 1517;
    public static final short GroupFurniConfigMessageComposer = 48;
    public static final short HabboUserBadgesMessageComposer = 959;
    public static final short FlatAccessibleMessageComposer = 237;
    public static final short ModeratorInitMessageComposer = 2162;
    public static final short FloorPlanSendDoorMessageComposer = 3877;
    public static final short SleepMessageComposer = 1059;
    public static final short FlatControllerRemovedMessageComposer = 3812;
    public static final short UniqueMachineIDMessageComposer = 3302;
    public static final short ItemAddMessageComposer = 2267;
    public static final short GroupForumDataMessageComposer = 3684;
    public static final short UpdateFreezeLivesMessageComposer = 151;
    public static final short NavigatorSettingsMessageComposer = 2469;
    public static final short ItemUpdateMessageComposer = 3671;
    public static final short AchievementsMessageComposer = 1155;
    public static final short LatencyResponseMessageComposer = 2261;
    public static final short RoomReadyMessageComposer = 729;
    public static final short HabboActivityPointNotificationMessageComposer = 886;
    public static final short BuddyListMessageComposer = 650;
    public static final short YoutubeDisplayPlaylistsMessageComposer = 354;
    public static final short TradingCompleteMessageComposer = 668;
    public static final short PetInformationMessageComposer = 3263;
    public static final short ModeratorRoomChatlogMessageComposer = 2079;
    public static final short MOTDNotificationMessageComposer = 1402;
    public static final short GroupInfoMessageComposer = 43;
    public static final short SlideObjectBundleMessageComposer = 3188;
    public static final short FurniListRemoveMessageComposer = 2648;
    public static final short FurniListNotificationMessageComposer = 519;
    public static final short RoomInfoUpdatedMessageComposer = 3044;
    public static final short AvatarEffectMessageComposer = 3351;
    public static final short OpenConnectionMessageComposer = 224;
    public static final short FurniListMessageComposer = 1814;
    public static final short PostUpdatedMessageComposer = 2148;
    public static final short UserFlatCatsMessageComposer = 1952;
    public static final short ObjectUpdateMessageComposer = 3902;
    public static final short ThreadUpdatedMessageComposer = 2257;
    public static final short HabboSearchResultMessageComposer = 3102;
    public static final short RespectNotificationMessageComposer = 3472;
    public static final short PetHorseFigureInformationMessageComposer = 308;
    public static final short MessengerInitMessageComposer = 3973;
    public static final short ModeratorUserInfoMessageComposer = 2328;
    public static final short YouAreControllerMessageComposer = 2951;
    public static final short RoomRatingMessageComposer = 2837;
    public static final short RefreshFavouriteGroupMessageComposer = 2546;
    public static final short AvailabilityStatusMessageComposer = 1957;
    public static final short AchievementUnlockedMessageComposer = 2654;
    public static final short FlatAccessDeniedMessageComposer = 595;
    public static final short NavigatorFlatCatsMessageComposer = 1228;
    public static final short UsersMessageComposer = 779;
    public static final short SecretKeyMessageComposer = 707;
    public static final short TradingStartMessageComposer = 1151;
    public static final short RoomSettingsDataMessageComposer = 3340;
    public static final short NewBuddyRequestMessageComposer = 1185;
    public static final short DoorbellMessageComposer = 3464;
    public static final short OpenGiftMessageComposer = 349;
    public static final short FloorHeightMapMessageComposer = 419;
    public static final short SellablePetBreedsMessageComposer = 1073;
    public static final short AchievementScoreMessageComposer = 1181;
    public static final short BuildersClubMembershipMessageComposer = 1308;
    public static final short PetTrainingPanelMessageComposer = 1788;
    public static final short QuestCompletedMessageComposer = 1410;
    public static final short UserRightsMessageComposer = 71;
    public static final short ForumsListDataMessageComposer = 2760;
    public static final short UserChangeMessageComposer = 2662;
    public static final short ModeratorUserChatlogMessageComposer = 2200;
    public static final short GiftWrappingConfigurationMessageComposer = 3976;
    public static final short FloorPlanFloorMapMessageComposer = 772;
    public static final short ThreadReplyMessageComposer = 801;
    public static final short GroupCreationWindowMessageComposer = 532;
    public static final short GetGuestRoomResultMessageComposer = 887;
    public static final short RoomNotificationMessageComposer = 619;
    public static final short InitCryptoMessageComposer = 1711;
    public static final short SoundSettingsMessageComposer = 3960;
    public static final short WiredTriggerConfigMessageComposer = 2623;
    public static final short ItemsMessageComposer = 745;
    public static final short PurchaseOKMessageComposer = 2445;
    public static final short BadgeEditorPartsMessageComposer = 2732;
    public static final short NewConsoleMessageMessageComposer = 2128;
    public static final short HideWiredConfigMessageComposer = 2270;
    public static final short CatalogPageMessageComposer = 472;
    public static final short AddExperiencePointsMessageComposer = 1940;
    public static final short AvatarEffectsMessageComposer = 3940;
    public static final short QuestListMessageComposer = 2074;
    public static final short UnbanUserFromRoomMessageComposer = 3585;
    public static final short WiredConditionConfigMessageComposer = 2944;
    public static final short StickyNoteMessageComposer = 1280;
    public static final short ObjectsMessageComposer = 1495;
    public static final short RoomVisualizationSettingsMessageComposer = 1180;
    public static final short PromoArticlesMessageComposer = 2352;
    public static final short MaintenanceStatusMessageComposer = 1434;
    public static final short BuddyRequestsMessageComposer = 455;
    public static final short AuthenticationOKMessageComposer = 3063;
    public static final short QuestStartedMessageComposer = 1581;
    public static final short BotInventoryMessageComposer = 1324;
    public static final short PerkAllowancesMessageComposer = 3722;
    public static final short RoomEventMessageComposer = 3875;
    public static final short MuteAllInRoomMessageComposer = 2015;
    public static final short ModeratorSupportTicketResponseMessageComposer = 596;
    public static final short YouTubeDisplayVideoMessageComposer = 1626;
    public static final short RoomPropertyMessageComposer = 336;
    public static final short ModeratorSupportTicketMessageComposer = 2392;
    public static final short RoomInviteMessageComposer = 2349;
    public static final short FurniListUpdateMessageComposer = 489;
    public static final short BadgesMessageComposer = 3337;
    public static final short NavigatorSearchResultSetMessageComposer = 328;
    public static final short IgnoreStatusMessageComposer = 372;
    public static final short ShoutMessageComposer = 139;
    public static final short MoodlightConfigMessageComposer = 2684;
    public static final short FurnitureAliasesMessageComposer = 1688;
    public static final short LoveLockDialogueCloseMessageComposer = 3892;
    public static final short TradingErrorMessageComposer = 3627;
    public static final short ProfileInformationMessageComposer = 515;
    public static final short ModeratorRoomInfoMessageComposer = 3197;
    public static final short CampaignMessageComposer = 2052;
    public static final short LoveLockDialogueMessageComposer = 1080;
    public static final short PopularRoomTagsResultMessageComposer = 3936;
    public static final short GiftWrappingErrorMessageComposer = 589;
    public static final short WhisperMessageComposer = 2571;
    public static final short CatalogItemDiscountMessageComposer = 1008;
    public static final short HabboGroupBadgesMessageComposer = 3390;
    public static final short CanCreateRoomMessageComposer = 491;
    public static final short ThreadDataMessageComposer = 2750;
    public static final short TradingFinishMessageComposer = 1976;
    public static final short DanceMessageComposer = 1707;
    public static final short GenericErrorMessageComposer = 3781;
    public static final short NavigatorPreferencesMessageComposer = 2911;
    public static final short MutedMessageComposer = 3213;
    public static final short BroadcastMessageAlertMessageComposer = 385;
    public static final short YouAreOwnerMessageComposer = 3588;
    public static final short ModeratorTicketChatlogMessageComposer = 2157;
    public static final short BadgeDefinitionsMessageComposer = 1271;
    public static final short UserRemoveMessageComposer = 1925;
    public static final short RoomSettingsSavedMessageComposer = 2402;
    public static final short ModeratorUserRoomVisitsMessageComposer = 401;
    public static final short RoomErrorNotifMessageComposer = 1122;
    public static final short NavigatorLiftedRoomsMessageComposer = 2876;
    public static final short NavigatorMetaDataParserMessageComposer = 2867;
    public static final short GetRelationshipsMessageComposer = 3823;
    public static final short ItemRemoveMessageComposer = 936;
    public static final short ThreadCreatedMessageComposer = 66;
    public static final short EnforceCategoryUpdateMessageComposer = 1186;
    public static final short AchievementProgressedMessageComposer = 2741;
    public static final short ActivityPointsMessageComposer = 1036;
    public static final short PetInventoryMessageComposer = 2913;
    public static final short GetRoomBannedUsersMessageComposer = 1930;
    public static final short UserUpdateMessageComposer = 2798;
    public static final short FavouritesMessageComposer = 2652;
    public static final short WardrobeMessageComposer = 1533;
    public static final short LoveLockDialogueSetLockedMessageComposer = 3892;
    public static final short TradingAcceptMessageComposer = 3125;
    public static final short SongInventoryMessageComposer = 11;
    public static final short SongIdMessageComposer = 511;
    public static final short SongDataMessageComposer = 2925;
    public static final short PlaylistMessageComposer = 932;
    public static final short PlayMusicMessageComposer = 3089;
    public static final short QuickPollMessageComposer = 3252;
    public static final short QuickPollResultMessageComposer = 3100;
    public static final short QuickPollResultsMessageComposer = 2053;
    public static final short InitializePollMessageComposer = 348;
    public static final short PollMessageComposer = 1182;
    public static final short AvatarAspectUpdateMessageComposer = 2662;
    public static final short YouAreSpectatorMessageComposer = 1494;
    public static final short UpdateStackMapMessageComposer = 2158;
    public static final short CameraPhotoPreviewMessageComposer = 2521;
    public static final short CameraBuyPhotoMessageComposer = 1000;
    public static final short CameraPriceMessageComposer = 2605;

    private static Map<Short, String> composerPacketNames = new HashMap<>();

    static {
        try {
            for (Field field : Composers.class.getDeclaredFields()) {
                if (!Modifier.isPrivate(field.getModifiers()))
                    composerPacketNames.put(field.getShort(field.getName()), field.getName());
            }
        } catch (Exception ignored) {

        }
    }

    public static String valueOfId(short packetId) {
        if (composerPacketNames.containsKey(packetId)) {
            return composerPacketNames.get(packetId);
        }

        return "UnknownMessageComposer";
    }
}
