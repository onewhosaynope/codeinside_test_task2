package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        DBhelper client = new DBhelper(
                DBdata.HOST,
                DBdata.DB_NAME,
                DBdata.USERNAME,
                DBdata.PASSWORD);

        try {

            if (client.connect()) {

                Mailer.add_to_queue(
                        Generator.randomEmail("gmail", "com"),
                        Generator.randomText(10),
                        Generator.randomText(30),
                        client);

                Mailer.send_message(client);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


}