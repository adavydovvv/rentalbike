package com.example.bike_rent;

public class Authorization {

    public static String login;
    public static int auth_id;
    public static String email;
    public static String password;

    public Authorization() {
//        this.login = login;
//        this.email = email;
//        this.password = password;
    }

    public static String getLogin() {
        return login;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }
    public static int getAuthid() {
        return auth_id;
    }
    public static void setLogin(String login) {
        Authorization.login = login;
    }

    public static void setEmail(String email) {
        Authorization.email = email;
    }

    public void setPassword(String password) {
        Authorization.password = password;
    }
}
