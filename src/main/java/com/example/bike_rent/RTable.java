package com.example.bike_rent;

import java.time.LocalDate;

public class RTable {
    private int id;
    private int client_id;
    private int bike_id;
    private LocalDate return_date;
    private LocalDate issue_date;
    private String issue_shop;
    private String model;
    private String status;

    public RTable(LocalDate issue_date, String issue_shop, String model) {
        this.issue_date = issue_date;
        this.issue_shop = issue_shop;
        this.model = model;
    }

    public RTable(LocalDate return_date, LocalDate issue_date, String issue_shop, String model) {
        this.return_date = return_date;
        this.issue_date = issue_date;
        this.issue_shop = issue_shop;
        this.model = model;
    }

    public RTable(int bike_id, String model, String status) {
        this.bike_id = bike_id;
        this.model = model;
        this.status = status;
    }

    public RTable(int id, int client_id, int bike_id, LocalDate return_date, LocalDate issue_date) {
        this.id = id;
        this.client_id = client_id;
        this.bike_id = bike_id;
        this.return_date = return_date;
        this.issue_date = issue_date;
    }

    public RTable(int id, int client_id, int bike_id, LocalDate issue_date) {
        this.id = id;
        this.client_id = client_id;
        this.bike_id = bike_id;
        this.issue_date = issue_date;
    }

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getBike_id() {
        return bike_id;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public LocalDate getIssue_date() {
        return issue_date;
    }

    public String getIssue_shop() {
        return issue_shop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setBike_id(int bike_id) {
        this.bike_id = bike_id;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public void setIssue_date(LocalDate issue_date) {
        this.issue_date = issue_date;
    }

    public void setIssue_shop(String issue_shop) {
        this.issue_shop = issue_shop;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
