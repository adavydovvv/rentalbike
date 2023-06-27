package com.example.bike_rent;

public class Const {
    //-------------------------------------------------
    // Таблица Client
    public static final String CLIENT_TABLE = "Client";

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_AUTHORIZATION = "id_authoriz";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_PASSPORTDATA = "passport_data";

    //-------------------------------------------------
    // Таблица Passport
    public static final String PASSPORT_TABLE = "Passport";

    public static final String PASSPORT_ID = "id";
    public static final String PASSPORT_SERIES = "series";
    public static final String PASSPORT_NUMBER = "number";

    //-------------------------------------------------
    // Таблица Authorization
    public static final String AUTHORIZATION_TABLE = "Authorization";

    public static final String AUTHORIZATION_ID = "auth_id";
    public static final String AUTHORIZATION_LOGIN = "login";
    public static final String AUTHORIZATION_EMAIL = "email";
    public static final String AUTHORIZATION_PASSWORD = "pass_word";
}
