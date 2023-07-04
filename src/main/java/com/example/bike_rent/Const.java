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
    public static final String CLIENT_MTAG = "m_tag";

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
    //-------------------------------------------------
    // Таблица Reservation
    public static final String RESERVATION_TABLE = "Reservation";

    public static final String RESERVATION_ID = "id";
    public static final String RESERVATION_CLIENTID = "client_id";
    public static final String RESERVATION_BIKEID = "bike_id";
    public static final String RESERVATION_RETURNDATE = "return_date";
    public static final String RESERVATION_ISDATE = "issue_date";
    public static final String RESERVATION_ISSHOP = "issue_shop";

    //-------------------------------------------------
    // Таблица Model

    public static final String MODEL_TABLE = "Model";

    public static final String MODEL_NAME = "name";
    public static final String MODEL_TYPEID = "typeid";
    public static final String MODEL_GCOUNT = "gear_count";

    //-------------------------------------------------
    // Таблица Bike

    public static final String BIKE_TABLE = "Bike";

    public static final String BIKE_ID = "id";
    public static final String BIKE_MODEL = "model";
    public static final String BIKE_SHOP = "shop";
    public static final String BIKE_STATUS = "status";

    //-------------------------------------------------
    // Таблица Shop
    public static final String SHOP_TABLE = "Shop";

    public static final String SHOP_NAME = "name";
    public static final String SHOP_ADDRESS = "address";
    public static final String SHOPADMIN_ID = "admin_id";

}
