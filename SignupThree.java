package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel i1 = new JLabel("Page 3: Account Details");
        i1.setFont(new Font("Raleway", Font.BOLD, 20));
        i1.setBounds(280, 40, 400, 40);
        add(i1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        JRadioButton[] radios = {r1, r2, r3, r4};
        int y = 180;

        ButtonGroup group = new ButtonGroup();
        for (JRadioButton r : radios) {
            r.setFont(new Font("Raleway", Font.BOLD, 16));
            r.setBackground(Color.WHITE);
            r.setBounds(100, y, 300, 25);
            add(r);
            group.add(r);
            y += 40;
        }

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 350, 300, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("Email & SMS Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declare that the above details are correct");

        JCheckBox[] checks = {c1, c2, c3, c4, c5, c6};
        y = 400;

        for (JCheckBox c : checks) {
            c.setFont(new Font("Raleway", Font.BOLD, 16));
            c.setBackground(Color.WHITE);
            c.setBounds(100, y, 250, 30);
            add(c);
            y += 40;
        }

        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBackground(Color.WHITE);
        c7.setBounds(100, y + 20, 600, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBounds(250, y + 70, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(400, y + 70, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 750);
        setLocation(350, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {

            String accountType = null;
            if (r1.isSelected()) accountType = "Saving Account";
            else if (r2.isSelected()) accountType = "Fixed Deposit Account";
            else if (r3.isSelected()) accountType = "Current Account";
            else if (r4.isSelected()) accountType = "Recurring Deposit Account";

            Random random = new Random();
            String cardnumber = "50409360" + (10000000 + random.nextInt(90000000));
            String pinnumber = "" + (1000 + random.nextInt(9000));

            String facility = "";
            if (c1.isSelected()) facility += "ATM Card ";
            if (c2.isSelected()) facility += "Internet Banking ";
            if (c3.isSelected()) facility += "Mobile Banking ";
            if (c4.isSelected()) facility += "Email & SMS Alerts ";
            if (c5.isSelected()) facility += "Cheque Book ";
            if (c6.isSelected()) facility += "E-Statement ";

            try {
                Conn c = new Conn();

                String query1 = "INSERT INTO signupthree VALUES('" +
                        formno + "','" + accountType + "','" +
                        cardnumber + "','" + pinnumber + "','" +
                        facility + "')";

                String query2 = "INSERT INTO login VALUES('" +
                        formno + "','" + cardnumber + "','" +
                        pinnumber + "')";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,
                        "Card Number: " + cardnumber + "\nPIN: " + pinnumber);

                setVisible(false);
                new Deposite(pinnumber).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SignupThree("1234");
    }
}
