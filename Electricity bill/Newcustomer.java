import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Newcustomer extends JFrame implements ActionListener {
    JTextField tfname ,tfaddress,tdstate,tfcity,tfemail,tfphoneno;
    JButton next,cancel;
     JLabel lblmeter;
    Newcustomer(){
    setSize(700, 500);
    setLocation(400,200);
    JPanel p= new JPanel();
    p.setLayout(null);
    p.setBackground(new Color(173,216,230));
    add(p);
    JLabel heading=new JLabel("New Customer");
    heading.setBounds(180,10,200,25);
    heading.setFont(new Font("Tahoma",Font.PLAIN,24));
    p.add(heading);

    JLabel lblname=new JLabel("Customer Name");
    lblname.setBounds(100,80,100,20);
    p.add(lblname);

    tfname =new JTextField();
    tfname.setBounds(240,80,200,20);
    p.add(tfname);

    
   JLabel lblmeterno=new JLabel("Meter Number");
    lblmeterno.setBounds(100,120,100,20);
    p.add(lblmeterno);

     lblmeter =new JLabel("");
    lblmeter.setBounds(240,120,100,20);
    p.add(lblmeter);
    
    Random ran =new Random();
    long number=ran.nextLong()%1000000;
    lblmeter.setText(" "+ Math.abs(number));

    JLabel lbladdress=new JLabel("Address");
    lbladdress.setBounds(100,160,100,20);
    p.add(lbladdress);

    tfaddress =new JTextField();
   tfaddress.setBounds(240,160,200,20);
    p.add(tfaddress);

        JLabel lblstate=new JLabel("State Name");
    lblstate.setBounds(100,200,100,20);
    p.add(lblstate);

    tdstate =new JTextField();
  tdstate.setBounds(240,200,200,20);
    p.add(tdstate);

        JLabel lblcity=new JLabel("City Name");
    lblcity.setBounds(100,240,100,20);
    p.add(lblcity);

   tfcity =new JTextField();
    tfcity.setBounds(240,240,200,20);
    p.add(tfcity);

        JLabel lblemail=new JLabel("Email");
    lblemail.setBounds(100,280,100,20);
    p.add(lblemail);

    tfemail =new JTextField();
    tfemail.setBounds(240,280,200,20);
    p.add(tfemail);

        JLabel lblphonenum=new JLabel("Phone Number");
    lblphonenum.setBounds(100,320,100,20);
    p.add(lblphonenum);

    tfphoneno=new JTextField();
  tfphoneno.setBounds(240,320,200,20);
    p.add(tfphoneno);
    
    next=new JButton("Next");
    next.setBounds(120,390,100,25);
    next.setBackground(Color.BLACK);
    next.setForeground(Color.white);
    next.addActionListener(this);
    p.add(next);

     cancel=new JButton("Cancel");
    cancel.setBounds(250,390,100,25);
    cancel.setBackground(Color.BLACK);
    cancel.setForeground(Color.white);
    cancel.addActionListener(this);
    p.add(cancel);
   
    setLayout(new BorderLayout());
    add(p,"Center");
    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("hicon1.jpg"));
    Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel image=new JLabel(i3);
    add(image,"West");
    getContentPane().setBackground(Color.WHITE);
    setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
      if(ae.getSource()==next){
   String name=tfname.getText();
   String meter=lblmeter.getText();
   String address=tfaddress.getText();
   String city=tfcity.getText();
   String state=tdstate.getText();
   String email=tfemail.getText();
   String phone=tfphoneno.getText();
    
   String query1="insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
   String query2="insert into login value('"+meter+"','','"+name+"','','')";
   try {
    Conn c=new Conn();
    c.s.executeUpdate(query1);
     c.s.executeUpdate(query2);
     JOptionPane.showMessageDialog(null, "Customer Details Added SuccessFully");
     new MeterInfo(meter);
     setVisible(false);
   } catch (Exception e) {
    e.printStackTrace();
   }
      }else{
        setVisible(false);
      }
    }
  public static void main(String[] args) {
    new Newcustomer();
  }  
}
