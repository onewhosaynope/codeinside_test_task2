package com.company;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class MailerTest {

    DBhelper client = new DBhelper(
            DBdata.HOST,
            DBdata.DB_NAME,
            DBdata.USERNAME,
            DBdata.PASSWORD);

    @Test
    public void send_message_test() {

    }

    @Test
    public void add_to_queue_test() throws SQLException {

        String mail = Generator.randomEmail("gmail", "com");
        String title = Generator.randomText(8);
        String text = Generator.randomText(20);

        Mailer.add_to_queue(mail, title, text, client);
        client.execQuery(
                "SELECT receiver, title, text " +
                "FROM requests" +
                "WHERE receiver='" + mail + "' AND " +
                "title='" + title + "' AND" +
                "text='" + text + "'");

    }

}