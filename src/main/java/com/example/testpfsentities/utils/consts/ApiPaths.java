package com.example.testpfsentities.utils.consts;

public final class ApiPaths {

    public final static String SLASH = "/";
    public final static String SLASH_ID = "/{id}";


    public final static String PLAYERS = "/players";
    public final static String GET_PLAYERS = PLAYERS + SLASH;
    public final static String GET_PLAYER_ID = PLAYERS + SLASH_ID;
    public final static String SAVE_PLAYER = "/add";


    public final static String ADMINS = "/admins";
    public final static String GET_ADMINS = SLASH;
    public static final String NOTIF_ADMINS = "/notifAdmins";
    public final static String GET_NOTIF_ADMINS = SLASH;
    public static final String UPDATE_NOTIF_ADMIN_READ_STATUS = "/updateNotification";
    public static final String UPDATE_STATUS_OWNER_TRUE = "/updateOwnerTrue";
    public static final String UPDATE_STATUS_OWNER_FALSE = "/updateOwnerFalse";


    public final static String MATCHES = "/matches";
    public final static String GET_MATCHES = MATCHES;
    public final static String CREATE_MATCH = "/create-match";
    public final static String JOIN_MATCH_AS_PLAYER = "/join-match-player";
    public final static String JOIN_MATCH_AS_TEAM = "/join-match-team";
    public static final String DELETE_MATCH = MATCHES + "/delete-match";
    public static final String EVALUATE_MATCH = MATCHES + "/evaluate-match";
    public static final String GET_CURRENT_NUMBER_TEAMS = "/number-teams";
    public static final String GET_NUMBER_OWNER_MATCHES = "/number-owner-matches";


    public final static String TEAMS = "/teams";
    public final static String GET_TEAMS = TEAMS + SLASH;
    public final static String CREATE_TEAM = "/create-team";

    public final static String NOTIFICATIONS = "/notifications";
    public final static String GET_NOTIFICATIONS = NOTIFICATIONS + SLASH;

    public final static String STATISTICS = "/statistics";
    public final static String GET_STATISTICS = STATISTICS + SLASH;


    public static final String OWNERS = "/owners";
    public final static String GET_OWNERS = OWNERS + SLASH;
    public static final String SAVE_OWNER = "/save";
    public static final String GET_NUMBER_OWNER_GROUNDS = "/number-owner-grounds";
    public static final String GET_NUMBER_OWNER_GROUNDS_OPEN = "/number-owner-grounds-open";
    public static final String GET_NUMBER_OWNER_GROUNDS_CLOSED = "/number-owner-grounds-closed";



    public static final String GROUNDS = "/grounds";
    public static final String SAVE_GROUND = "/save-ground";
    public static final String DELETE_GROUND = "/delete-ground";
    public static final String UPDATE_STATUS_GROUND = "/update-status-ground";
    public static final String GET_NUMBER_GROUND_MATCHES = "/number-ground-matches";
    public final static String GET_GROUNDS = GROUNDS + SLASH;
    public final static String UPDATE_GROUND = "/update-ground";

}
