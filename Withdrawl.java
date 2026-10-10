
package bank.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    
    
    JTextField amount;
    JButton Withdraw, Back;
    String pinnumber;
    
    Withdrawl(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
    
                       
           JLabel text = new JLabel("Enter amount you want to Withdraw");
           text.setForeground(Color.WHITE);
           text.setFont(new Font("System", Font.BOLD, 16));
           text.setBounds(170, 300, 400, 20);
           image.add(text);
           
           
            amount = new JTextField();
           amount.setFont(new Font("raleway", Font.BOLD, 22));
           amount.setBounds(170, 350, 320, 25);
           image.add(amount);
           
            Withdraw = new JButton("Withdraw");
           Withdraw.setBounds(355, 485, 150, 30);
           Withdraw.addActionListener(this);
          image.add(Withdraw);
           
            Back = new JButton("Back");
           Back.setBounds(355, 520, 150, 30);
           Back.addActionListener(this);

           image.add(Back);
           
           
           
           
        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Withdraw){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "please enter the amount you want to Withdraw");
                
            }else {
                try{
                Conn conn = new Conn();
               String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+number+"')";
               conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "₹" + number + " Withdraw Successfully");
                     setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                  } catch (Exception e) {
                    System.out.println(e);
            }
            }
        }else if (ae.getSource() == Back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Withdrawl("");     
    }
}
    

  

