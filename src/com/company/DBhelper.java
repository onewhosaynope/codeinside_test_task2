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

    /**
     * установка соединения с базой данных.
     * @return - true
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * добавление в базу данных письма на очередь.
     * @param receiver  - получатель сообщения.
     * @param title - загловок письма.
     * @param text - содержимое письма.
     * @return - sql запрос для добавления письма с заданными параметрами в бд на очередь.
     * @throws SQLException
     */
    public int insert(String receiver, String title, String text) throws SQLException {

        if (receiver.isEmpty()) {
            throw new SQLException("Receiver parameter is empty");
        }

        String query = String.format(
                "INSERT INTO requests (receiver, title, text) VALUES ('%s', '%s', '%s')",
                receiver, title, text);
        System.out.println("Выполненный sql запрос:" + query);

        return this.conn.createStatement().executeUpdate(query);
    }

    /**
     * удаление из бд письма с заданными параметрами.
     * @param receiver - получатель сообщения.
     * @param title - загловок письма.
     * @param text - содержимое письма.
     * @return - sql запрос для удаления письма с заданными параметрами.
     * @throws SQLException
     */
    public int remove(String receiver, String title, String text) throws SQLException {

        if (receiver.isEmpty()) {
            throw new SQLException("Receiver parameter is empty");
        }

        String query = String.format(
                "DELETE FROM requests WHERE receiver='%s' AND title='%s' AND text='%s'",
                receiver, title, text);
        System.out.println("Выполненный sql запрос:" + query);

        return this.conn.createStatement().executeUpdate(query);
    }

    // подтягивает из бд первую запись
    public ResultSet getFirstRecord() throws SQLException {

        String query = "SELECT * FROM requests LIMIT 1";
        System.out.println("Выполненный sql запрос:" + query);

        return this.conn.createStatement().executeQuery(query);
    }

    //выполнение сторонних запросов
    public ResultSet execQuery(String query) throws SQLException {
        return this.conn.createStatement().executeQuery(query);
    }
}