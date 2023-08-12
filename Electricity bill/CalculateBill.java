import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class CalculateBill extends JFrame implements ActionListener {
    JTextField tfname ,tfaddress,tfunit,tfcity,tfemail,tfphoneno;
    JButton next,cancel;
     JLabel lblname,labeladress;
     Choice meternumber,cmonth;
    CalculateBill(){
    setSize(700, 500);
    setLocation(400,150);
    JPanel p= new JPanel();
    p.setLayout(null);
    p.setBackground(new Color(173,216,230));
    add(p);
    JLabel heading=new JLabel("Calculate Electricity Bill");
    heading.setBounds(100,10,400,25);
    heading.setFont(new Font("Tahoma",Font.PLAIN,24));
    p.add(heading);

    JLabel lblmeternumber=new JLabel("Meter Number");
    lblmeternumber.setBounds(100,80,100,20);
    p.add(lblmeternumber);

  meternumber=new Choice();
  try {
    Conn c=new Conn();
   ResultSet rs= c.s.executeQuery(" select * from customer");
   while(rs.next()){
      meternumber.add(rs.getString("meter_n"));
   }
  } catch (Exception e) {
    e.printStackTrace();
  }
   meternumber.setBounds(240,80,200,20);
    p.add(meternumber);
    
    
   JLabel lblmeterno=new JLabel("Name");
    lblmeterno.setBounds(100,120,100,20);
    p.add(lblmeterno);

     lblname =new JLabel("");
    lblname.setBounds(240,120,100,20);
    p.add(lblname);
    
    
    JLabel lbladdress=new JLabel("Address");
    lbladdress.setBounds(100,160,100,20);
    p.add(lbladdress);

   labeladress=new JLabel();
  labeladress.setBounds(240,160,200,20);
    p.add(labeladress);
  try {
    Conn c=new Conn();
    ResultSet rs=c.s.executeQuery("select * from customer where meter_n='"+meternumber.getSelectedItem()+"'");
    while(rs.next()){
      lblname.setText(rs.getString("name"));
      labeladress.setText(rs.getString("address"));
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
  meternumber.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent ie){
       try {
    Conn c=new Conn();
    ResultSet rs=c.s.executeQuery("select * from customer where meter_n='"+meternumber.getSelectedItem()+"'");
    while(rs.next()){
      lblname.setText(rs.getString("name"));
      labeladress.setText(rs.getString("address"));
    }
  } catch (Exception e) {
    e.printStackTrace();
  }
    }
  });
        JLabel lblstate=new JLabel("Unit Consumed");
    lblstate.setBounds(100,200,100,20);
    p.add(lblstate);

    tfunit =new JTextField();
  tfunit.setBounds(240,200,200,20);
    p.add(tfunit);

        JLabel lblcity=new JLabel("Month");
    lblcity.setBounds(100,240,100,20);
    p.add(lblcity);

   cmonth=new Choice();
    cmonth.setBounds(240,240,200,20);
    cmonth.add("January");
    cmonth.add("february");
    cmonth.add("march");
    cmonth.add("April");
    cmonth.add("May");
    cmonth.add("june");
    cmonth.add("july");
    cmonth.add("August");
    cmonth.add("September");
    cmonth.add("October");
    cmonth.add("November");
    cmonth.add("December");
    p.add(cmonth);

        
    
    next=new JButton("Submit");
    next.setBounds(120,350,100,25);
    next.setBackground(Color.BLACK);
    next.setForeground(Color.white);
    next.addActionListener(this);
    p.add(next);

     cancel=new JButton("Cancel");
    cancel.setBounds(250,350,100,25);
    cancel.setBackground(Color.BLACK);
    cancel.setForeground(Color.white);
    cancel.addActionListener(this);
    p.add(cancel);
   
    setLayout(new BorderLayout());
    add(p,"Center");
    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("hicon2.jpg"));
    Image i2=i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel image=new JLabel(i3);
    add(image,"West");
    getContentPane().setBackground(Color.WHITE);
    setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
      if(ae.getSource()==next){
   String meter=meternumber.getSelectedItem();
   String units=tfunit.getText();
   String month=cmonth.getSelectedItem();
   int totalbill=0;
   int unit_consumed=Integer.parseInt(units);
  String query="select * from tax";
   try {
    Conn c=new Conn();
     ResultSet rs=c.s.executeQuery(query);
     while(rs.next()){
    totalbill+= unit_consumed * Integer.parseInt(rs.getString("cost_per"));
    totalbill+=Integer.parseInt(rs.getString("meter_rent"));
    totalbill+=Integer.parseInt(rs.getString("service_charge"));
    totalbill+=Integer.parseInt(rs.getString("service_tax"));
    totalbill+=Integer.parseInt(rs.getString("swatch_bharat_cess"));
    totalbill+=Integer.parseInt(rs.getString("fix_tax"));
    }
    } catch (Exception e) {
    e.printStackTrace();
   }
   String query2="insert into ebill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not_paid')";
   try {
    Conn c=new Conn();
    c.s.executeUpdate(query2);
    JOptionPane.showMessageDialog(null,"Customer Bill Updated Succefully");
    setVisible(false);
   } catch (Exception e) {
         e.printStackTrace();
   }
      }else{
        setVisible(false);
      }
    }
  public static void main(String[] args) {
    new CalculateBill();
  }  
}
