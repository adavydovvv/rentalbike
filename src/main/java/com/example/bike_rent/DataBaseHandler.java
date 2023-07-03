package com.example.bike_rent;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class DataBaseHandler extends Configs{
    Connection dbconnection;
    private static class DataBaseHandlerHolder {
        public static final DataBaseHandler HOLDER_INSTANCE = new DataBaseHandler();
    }

    public static Connection getInstanceConnection() {
        return ConnectionHolder.HOLDER_CONNECTION;
    }
    public Connection getDbconnection() throws ClassNotFoundException, SQLException{

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName ;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbconnection;
    }
    private static class ConnectionHolder {
        public static final Connection HOLDER_CONNECTION;

        static {
            try {
                HOLDER_CONNECTION = getInstance().getDbconnection();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void signUpUser(String name, String address, String series,
                           String number, String login, String password,
                           String email){

        String insert_client = "INSERT INTO " + Const.CLIENT_TABLE + "(" +
                Const.CLIENT_AUTHORIZATION + ","  + Const.CLIENT_NAME + "," + Const.CLIENT_ADDRESS
                + "," + Const.CLIENT_PASSPORTDATA + ")" + "VALUES(?,?,?,?)";

        String insert_passport = "INSERT INTO " + Const.PASSPORT_TABLE + "(" +
                Const.PASSPORT_SERIES + ","  + Const.PASSPORT_NUMBER + ")" +  "VALUES(?,?)";

        String insert_authorization = "INSERT INTO " + Const.AUTHORIZATION_TABLE + "(" +
                Const.AUTHORIZATION_LOGIN + "," + Const.AUTHORIZATION_EMAIL + "," + Const.AUTHORIZATION_PASSWORD
                + ")" + "VALUES(?,?,?)";

        try{
            PreparedStatement ps_auth = getInstanceConnection().prepareStatement(insert_authorization);
            ps_auth.setString(1, login);
            ps_auth.setString(2, email);
            ps_auth.setString(3, Integer.toString(password.hashCode()));
            ps_auth.executeUpdate();

            PreparedStatement ps_pas = getInstanceConnection().prepareStatement(insert_passport);
            ps_pas.setString(1, series);
            ps_pas.setString(2, number);
            ps_pas.executeUpdate();



            String query_pas = "SELECT * FROM Passport ORDER BY id DESC LIMIT 0, 1";
            String query_auth = "SELECT * FROM Authorization ORDER BY auth_id DESC LIMIT 0, 1";


            PreparedStatement ps_client = getInstanceConnection().prepareStatement(insert_client);
            ps_client.setInt(1, getidnum(query_auth));
            ps_client.setString(2, name);
            ps_client.setString(3, address);
            ps_client.setInt(4, getidnum(query_pas));
            ps_client.executeUpdate();
        } catch (SQLException e){
            //System.out.println("АПШИВКА");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка регистрации:");
            alert.setContentText("Пользователь с таким логином уже существует!");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
    private int getidnum(String query) throws SQLException {
        Statement statement = getInstanceConnection().createStatement();
            int value = 0;
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                value = result.getInt(1);
            }
        return value;
    }
    public ResultSet getUser(String login, String password) throws SQLException {
        ResultSet result = null;
        String query = "SELECT * FROM " + Const.AUTHORIZATION_TABLE + " WHERE " + Const.AUTHORIZATION_LOGIN +
                "=? AND " + Const.AUTHORIZATION_PASSWORD + "=?";
        PreparedStatement pr = getInstanceConnection().prepareStatement(query);
        pr.setString(1, login);
        pr.setString(2, Integer.toString(password.hashCode()));

        result = pr.executeQuery();
        return result;
    }
    public ObservableList<String> getColumnBoxData(String table_name, String column_name) throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList();
        String query = "SELECT " + column_name + " FROM " + table_name;
        Statement statement = getInstanceConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            String value = result.getString(column_name);
            data.add(value);
        }
        return data;
    }
    public int getMinBikeId(String model) throws SQLException {
        int value = 0;
        String query = "SELECT MIN(id) as min_id FROM " + Const.BIKE_TABLE + " WHERE " + Const.BIKE_MODEL +
                " = '" + model + "' AND " + Const.BIKE_STATUS + " != 'Занят'" ;
        Statement statement = getInstanceConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            value = result.getInt(1);
        }
        return value;
    }
    public int getClientID(String login) throws SQLException {
        int value = 0;
        int res = 0;
        String query = "SELECT auth_id FROM " + Const.AUTHORIZATION_TABLE + " WHERE " + Const.AUTHORIZATION_LOGIN +
                " = '" + login + "'";
        Statement statement = getInstanceConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            value = result.getInt(1);
        }
        String query2 = "SELECT client_id FROM " + Const.CLIENT_TABLE + " WHERE " + Const.CLIENT_AUTHORIZATION +
                " = " + value;
        Statement statement2 = getInstanceConnection().createStatement();
        ResultSet result2 = statement2.executeQuery(query2);
        while (result2.next()){
            res = result2.getInt(1);
        }
        return res;
    }
    public String getClientName() throws SQLException {
        String value = null;
        String query = "SELECT name FROM " + Const.CLIENT_TABLE + " WHERE " + Const.CLIENT_ID +
                " = " +  getClientID(Authorization.getLogin());
        Statement statement = getInstanceConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            value = result.getString(1);
        }
        return value;
    }
    public void reserve_bike(String model, String shop, LocalDate date) throws SQLException {
        String minbikeid = Integer.toString(getMinBikeId(model));
        String insert_reserve = "INSERT INTO " + Const.RESERVATION_TABLE + "(" +
                Const.RESERVATION_CLIENTID + ","  + Const.RESERVATION_BIKEID + "," + Const.RESERVATION_ISDATE
                + "," + Const.RESERVATION_ISSHOP + ")" + "VALUES(?,?,?,?)";
        String update_biketable = "UPDATE " + Const.BIKE_TABLE + " SET " + Const.BIKE_STATUS + " = 'Занят' WHERE id = " +
                minbikeid;

        try{
            PreparedStatement ps = getInstanceConnection().prepareStatement(insert_reserve);
            ps.setInt(1, getClientID(Authorization.getLogin()));
            ps.setInt(2, getMinBikeId(model));
            ps.setDate(3, Date.valueOf(date));
            ps.setString(4, shop);

            ps.executeUpdate();

            PreparedStatement ps_b = getInstanceConnection().prepareStatement(update_biketable);
            ps_b.executeUpdate();
        } catch (SQLException e){
            //System.out.println("Ошибка молодости.....ошибка молодости");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка бронирования:");
            alert.setContentText("К сожалению, на данный момент выбранная модель велосипеда в данном магазине недоступна");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
    public ObservableList<String> getColumnBoxShops(String bike_model) throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList();
        String query = "SELECT DISTINCT " + Const.BIKE_SHOP + " FROM " + Const.BIKE_TABLE + " WHERE " + Const.BIKE_MODEL + " = '" + bike_model + "'";
        Statement statement = getInstanceConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            String value = result.getString(Const.BIKE_SHOP);
            data.add(value);
        }
        return data;
    }
    public ObservableList<Integer> getClientResBikeId() throws SQLException {
        ObservableList<Integer> data = FXCollections.observableArrayList();
        int client_id = getClientID(Authorization.getLogin());
        String query = "SELECT " + Const.RESERVATION_BIKEID + " FROM " + Const.RESERVATION_TABLE + " WHERE client_id = " + client_id + " AND return_date is NULL";
        Statement statement = getInstanceConnection().createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            int value = result.getInt(Const.RESERVATION_BIKEID);
            data.add(value);
        }
        return data;
    }
    public void return_bike(int bikeid, LocalDate date) throws SQLException {
        String update_biketable = "UPDATE " + Const.BIKE_TABLE + " SET " + Const.BIKE_STATUS + " = 'Свободен' WHERE id = " +
                bikeid;
        String update_reserve = "UPDATE " + Const.RESERVATION_TABLE + " SET " + Const.RESERVATION_RETURNDATE + " = '" + date + "' WHERE bike_id = " +
                bikeid + " AND return_date is NULL";
        try{
            PreparedStatement ps_b = getInstanceConnection().prepareStatement(update_biketable);
            ps_b.executeUpdate();
            PreparedStatement ps_res = getInstanceConnection().prepareStatement(update_reserve);
            ps_res.executeUpdate();
        } catch (SQLException e){
            System.out.println("Ошибка молодости.....ошибка молодости");
            throw new RuntimeException(e);
        }
    }
    public static DataBaseHandler getInstance() {
        return DataBaseHandlerHolder.HOLDER_INSTANCE;
    }
}
