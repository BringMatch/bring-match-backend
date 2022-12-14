package com.example.testpfsentities.utils.consts;

public final class ApiPaths {

    public final static String SLASH = "/";
    public final static String SLASH_ID = "/{id}";


    public final static String PLAYERS = "/players";
    public final static String KEYCLOAK = "/keycloak";
    public final static String GET_PLAYERS = SLASH;
    public final static String GET_PLAYER_ID = PLAYERS + SLASH_ID;
    public final static String SAVE_PLAYER = "/save";
    public final static String GET_GOALS_SCORED = "/player/get-goals-scored";
    public final static String SEARCH_PLAYERS = "/search";
    public final static String GET_NOTIFICATIONS_PLAYER = "/notification-player";
    public final static String GET_LAST_FIVE_NOTIFICATIONS_PLAYER = "/notification-player/5";


    public final static String ADMINS = "/admins";
    public final static String GET_ADMINS = SLASH;
    public static final String NOTIF_ADMINS = "/notifAdmins";
    public final static String GET_NOTIF_ADMINS = SLASH;
    public final static String ACCEPT_NOTIF_ADMIN = "/accept-notification";
    public final static String REFUSE_NOTIF_ADMIN = "/refuse-notification";
    public static final String UPDATE_NOTIF_ADMIN_READ_STATUS = "/updateNotification";
    public static final String UPDATE_STATUS_OWNER_TRUE = "/updateOwnerTrue";
    public static final String UPDATE_STATUS_OWNER_FALSE = "/updateOwnerFalse";
    public static final String GET_NUMBER_ACCEPTED_OWNERS = "/number-accepted-owners";
    public static final String GET_NUMBER_REFUSED_OWNERS = "/number-refused-owners";
    public static final String GET_NUMBER_ALL_OWNERS = "/number-owners";
    public static final String GET_NUMBER_PLAYERS_SAVED = "/number-players-saved";


    public final static String MATCHES = "/matches";
    public final static String GET_MATCHES_HISTORY = "/matches-history";
    public final static String GET_MATCHES = MATCHES + SLASH;
    public final static String GET_MATCHES_GROUND = "/ground";
    public final static String GET_NUMBER_MATCHES_OF_A_PLAYER = "/all-matches";
    public final static String GET_NUMBER_MATCHES_WIN_OF_A_PLAYER = "/player/win-matches";
    public final static String GET_NUMBER_MATCHES_LOSE_OF_A_PLAYER = "/player/lose-matches";
    public final static String GET_NUMBER_MATCHES_DRAW_OF_A_PLAYER = "/player/draw-matches";
    public final static String GET_LENGTH_TEAM_IN_A_MATCH = "/length-team-in-a-match";
    public final static String GET_MATCHES_OF_OWNER_GROUNDS = "/matches-of-owner-grounds";
    public final static String GET_MATCH_CODE_IF_PRIVATE = "/match-code";
    public final static String CREATE_MATCH = "/create-match";
    public final static String JOIN_MATCH_AS_PLAYER = "/join-match-player";
    public final static String JOIN_MATCH_AS_TEAM = "/join-match-team";
    public static final String DELETE_MATCH = MATCHES + "/delete-match";
    public static final String GET_CURRENT_NUMBER_TEAMS = "/number-teams";
    public static final String GET_NUMBER_OWNER_MATCHES = "/owner/number-owner-matches";
    public final static String SEARCH_MATCH = "/search";
    public final static String SEARCH_MATCH_BY_DATE = "/search-date";
    public final static String EVALUATE_MATCH = "/evaluate-match";


    public final static String TEAMS = "/teams";
    public final static String GET_TEAMS = SLASH;
    public final static String GET_FREE_POSITIONS_IN_A_TEAM = "/free-positions";
    public final static String GET_LENGTH_REMAINING = "/length-remaining";
    public final static String CREATE_TEAM = "/create-team";

    public final static String NOTIFICATIONS = "/notifications";

    public final static String STATISTICS = "/statistics";


    public static final String OWNERS = "/owners";
    public final static String GET_OWNERS = SLASH;
    public final static String DELETE_OWNER = "/delete";
    public static final String SAVE_OWNER = "/save";
    public static final String OWNER_CONNECTED = "/connected";
    public static final String GET_NUMBER_OWNER_GROUNDS = "/owner/number-owner-grounds";
    public static final String GET_NUMBER_OWNER_GROUNDS_OPEN = "/owner/number-owner-grounds-open";
    public static final String GET_NUMBER_OWNER_GROUNDS_CLOSED = "/owner/number-owner-grounds-closed";


    public static final String GROUNDS = "/grounds";
    public static final String SAVE_GROUND = "/save-ground";
    public static final String DELETE_GROUND = "/delete-ground";
    public static final String UPDATE_STATUS_GROUND = "/update-status-ground";
    public final static String GET_GROUNDS = SLASH;
    public final static String UPDATE_GROUND = "/update-ground";
    public final static String SEARCH_GROUND = "/search";
    public final static String GET_OPEN_AND_FREE_GROUNDS = "/free-and-open";

    public static final String GLOBAL_STATS = "/globalStats";
    public static final String SAVE_GLOBAL_STATS = "/save";
    public static final String GET_GLOBAL_STATS = "/get-global-stats";


    public static final String PLAYER_STATS = "/player-stats";

    public static final String GET_PLAYER_STATS = SLASH;
}
