
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Signup extends JFrame implements ActionListener{
    JButton create ,back;
    Choice accountType;
    JTextField meter,username,name, password;
    Signup(){
        

setBounds(450,150,700,400);
getContentPane().setBackground(Color.pink);
setLayout(null);
JPanel panel=new JPanel();
panel.setBounds(30,30,650,300);
panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216, 230,2)),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230)));
panel.setBackground(Color.white);
panel.setLayout(null);
panel.setForeground(new Color(34,139, 34));
add(panel);
JLabel heading=new JLabel("Create Account");
heading.setBounds(100,50,140,20);
heading.setForeground(Color.GRAY);
heading.setFont(new Font("Tahoma",Font.BOLD,14));
panel.add(heading);
 accountType=new Choice();
accountType.add("Admin");
accountType.add("Customer");
accountType.setBounds(260,50,150,20);
panel.add(accountType);

JLabel lbmeter=new JLabel("Meter Number");
lbmeter.setBounds(100,90,140,20);
lbmeter.setForeground(Color.GRAY);
lbmeter.setFont(new Font("Tahoma",Font.BOLD,14));
lbmeter.setVisible(false);
panel.add(lbmeter);
meter=new JTextField();
meter.setBounds(260,90,150,20);
meter.setVisible(false);
panel.add(meter);

JLabel lbusername=new JLabel("Username");
 lbusername.setBounds(100,130,140,20);
 lbusername.setForeground(Color.GRAY);
 lbusername.setFont(new Font("Tahoma",Font.BOLD,14));
panel.add( lbusername);
 username=new JTextField();
username.setBounds(260,130,150,20);
panel.add(username);
JLabel lbname=new JLabel("Name");
 lbname.setBounds(100,170,140,20);
 lbname.setForeground(Color.GRAY);
 lbname.setFont(new Font("Tahoma",Font.BOLD,14));
panel.add( lbname);
 name=new JTextField();
name.setBounds(260,170,150,20);
panel.add(name);

meter.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent fe){}
    @Override
    public void focusLost(FocusEvent fe){
        try {
        Conn c=new Conn();
        ResultSet rs=c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"'");
        while(rs.next()){
            name.setText(rs.getString("name"));
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

});

JLabel lbpassword=new JLabel("Password");
 lbpassword.setBounds(100,210,140,20);
 lbpassword.setForeground(Color.GRAY);
 lbpassword.setFont(new Font("Tahoma",Font.BOLD,14));
panel.add( lbpassword);
 password=new JTextField();
password.setBounds(260,210,150,20);
panel.add(password);

accountType.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent ae){
        String user=accountType.getSelectedItem();
        if(user.equals("Customer")){
           lbmeter.setVisible(true);
           meter.setVisible(true);
        }else{
           lbmeter.setVisible(false);
           meter.setVisible(false);
        }
    }
});

 create =new JButton("Create");
create.setBackground(Color.BLACK);
create.setForeground(Color.white);
create.setBounds(140,260,120,25);
create.addActionListener(this);
panel.add(create);
 back =new JButton("Back ");
back.setBackground(Color.BLACK);
back.setForeground(Color.white);
back.setBounds(300,260,120,25);
back.addActionListener(this);
panel.add(back);
ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("signupImage.png"));
Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
ImageIcon i3=new ImageIcon(i2);
JLabel image=new JLabel(i3);
image.setBounds(420,30,250,250);
panel.add(image);
setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==create){
            String atype=accountType.getSelectedItem();
            String susername=username.getText();
            String sname=name.getText();
            String spassword=password.getText();
            String smeter=meter.getText();
try {
 Conn c=new Conn();
 String query=null;
 if(atype.equals("Admin")){
    query="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"') ";
 }else{
    query="update login set  usernmae='"+susername+"',password='"+spassword+"',user='"+atype+"' where meter_no='"+smeter+"'";
 }
   c.s.executeUpdate(query);
   JOptionPane.showMessageDialog(null,"Account Create Successfully");
   new Login();
} catch (Exception e) {
    e.printStackTrace();
}
        }else if(ae.getSource()==back){
        setVisible(false);
        new Login();
        }

    }
   public static void main(String[] args) {
    new Signup();
   } 
}
