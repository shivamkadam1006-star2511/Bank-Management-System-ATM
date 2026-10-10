package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {

        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 60, 300, 20);
        add(card);

        JLabel mini = new JLabel();
        mini.setBounds(20, 100, 400, 300);
        add(mini);
        
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        // Card Number
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery(
                "select * from login where pin = '" + pinnumber + "'"
            );

            if (rs.next()) {
                String cardNumber = rs.getString("cardnumber");
                card.setText(
                    "Card Number: " + cardNumber.substring(0, 4) +
                    "XXXXXXXX" + cardNumber.substring(12)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mini Statement & Balance
        try {
            Conn conn = new Conn();
            int bal = 0;

            ResultSet rs = conn.s.executeQuery(
                "select * from bank where pin = '" + pinnumber + "'"
            );

            String statement = "<html>";

            while (rs.next()) {
                statement += rs.getString("date") + 
                             "&nbsp;&nbsp;&nbsp;" +
                             rs.getString("type") + 
                             "&nbsp;&nbsp;&nbsp;" +
                             rs.getString("amount") + "<br>";

                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }

            statement += "</html>";
            mini.setText(statement);

            balance.setText("Your current account balance is Rs " + bal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 600);
        setLocation(300, 0);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("123");
    }
}
