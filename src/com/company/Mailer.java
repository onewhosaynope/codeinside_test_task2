package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mailer {

    public static void send_message(DBhelper client) throws SQLException {

        ResultSet first_record = client.getFirstRecord();
        first_record.next();
        String mail = first_record.getString(1);
        String title = first_record.getString(2);
        String text = first_record.getString(3);

        System.out.print(
                "==========" +
                "\nПисьмо отправлено. " +
                "\nПолучатель: " + mail +
                "\nЗаголовок письма: " + title +
                "\nТекст письма: " + text + "\n");

        client.remove(mail, title, text);

    }


    public static void add_to_queue(String mail, String title, String text, DBhelper client) throws SQLException {

        client.insert(mail, title, text);

        System.out.print(
                "==========" +
                "\nПисьмо Добавлено в очередь. " +
                "\nПолучатель: " + mail +
                "\nЗаголовок письма: " + title +
                "\nТекст письма: " + text + "\n");

    }

}
