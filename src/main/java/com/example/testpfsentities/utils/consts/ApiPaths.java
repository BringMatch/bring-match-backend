package com.example.testpfsentities.utils.consts;

public final class ApiPaths {

    public final static String SLASH = "/";
    public final static String SLASH_ID = "/{id}";

    public final static String PLAYERS = "/players";
    public final static String GET_PLAYERS = PLAYERS + SLASH;
    public final static String GET_PLAYER_ID = PLAYERS + SLASH_ID;
    public final static String SAVE_PLAYER = "/add";


    public final static String ADMINS = "/admins";
    public final static String GET_ADMINS = ADMINS + SLASH;

    public final static String MATCHES = "/matches";
    public final static String GET_MATCHES = MATCHES + SLASH;

    public final static String TEAMS = "/teams";
    public final static String GET_TEAMS = TEAMS + SLASH;

    public final static String NOTIFICATIONS = "/notifications";
    public final static String GET_NOTIFICATIONS = NOTIFICATIONS + SLASH;

    public final static String STATISTICS = "/statistics";
    public final static String GET_STATISTICS = STATISTICS + SLASH;
    public static final String OWNERS = "/owners";
    public static final String SAVE_OWNER = "/save";
}
