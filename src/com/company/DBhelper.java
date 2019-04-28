package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBhelper {

    private Connection conn;
    private String host;
    private String dbName;
    private String user;
    private String pass;


    public DBhelper(String host, String dbName, String user, String pass) {
        this.host = host;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }


    public boolean connect() throws SQLException, ClassNotFoundException {
        if (host.isEmpty() || dbName.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            throw new SQLException("Database credentials missing");
        }

        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(
                this.host + this.dbName,
                this.user, this.pass);
        System.out.println("Database connected");
        return true;
    }


    //добавление записи
    public int insert(String receiver, String title, String text) throws SQLException {

        String query = String.format(
                "INSERT INTO requests (receiver, title, text) VALUES ('%s', '%s', '%s')",
                receiver, title, text);
        System.out.println(query);

        return this.conn.createStatement().executeUpdate(query);
    }


    //удаление записи по условию
    public int remove(String receiver, String title, String text) throws SQLException {

        String query = String.format(
                "DELETE FROM requests WHERE receiver='%s' AND title='%s' AND text='%s'",
                receiver, title, text);
        System.out.println(query);

        return this.conn.createStatement().executeUpdate(query);
    }


    // получение первой записи в бд
    public ResultSet getFirstRecord() throws SQLException {

        String query = "SELECT * FROM requests LIMIT 1";
        System.out.println(query);

        return this.conn.createStatement().executeQuery(query);
    }


    //выполнение сторонних запросов
    public ResultSet execQuery(String query) throws SQLException {
        return this.conn.createStatement().executeQuery(query);
    }

}