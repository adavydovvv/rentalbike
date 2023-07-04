package com.example.bike_rent;

public class Shop {
    public static String name;
    public static int admin_id;

    public static void setName(String name) {
        Shop.name = name;
    }

    public static void setAdmin_id(int admin_id) {
        Shop.admin_id = admin_id;
    }

    public static String getName() {
        return name;
    }

    public static int getAdmin_id() {
        return admin_id;
    }
}
