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

public class PayBill extends JFrame implements ActionListener{
    Choice cmonth;
    String meter;
    JButton pay ,back;

    PayBill( String meter){
        this.meter=meter;
        setLayout(null);
        setBounds(300,150,900,600);

        JLabel heading=new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoms",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);

         JLabel lblmeternumber=new JLabel("Meter Number");
       lblmeternumber.setBounds(35,80,200,20);
        add(lblmeternumber);

         JLabel meternumber=new JLabel("");
       meternumber.setBounds(300,80,200,20);
        add(meternumber);

         JLabel lblname=new JLabel("Name");
       lblname.setBounds(35,140,200,20);
       add(lblname);

         JLabel labelname=new JLabel("");
       labelname.setBounds(300,140,200,20);
        add(labelname);
        
         JLabel lblmonth=new JLabel("Month");
         lblmonth.setBounds(35,200,200,20);
         add(lblmonth);

         cmonth=new Choice();
         cmonth.setBounds(300,200,200,20);
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
         add(cmonth);
     
          JLabel lblunits=new JLabel("Units");
       lblunits.setBounds(35,260,200,20);
       add(lblunits);

         JLabel labelunits=new JLabel("");
       labelunits.setBounds(300,260,200,20);
        add(labelunits);

         JLabel lbltotalbill=new JLabel("Total Bill");
       lbltotalbill.setBounds(35,320,200,20);
       add(lbltotalbill);

         JLabel labeltotalbill=new JLabel("");
       labeltotalbill.setBounds(300,320,200,20);
        add(labeltotalbill);
        
         JLabel lblstatus=new JLabel("Status");
      lblstatus.setBounds(35,380,200,20);
       add(lblstatus);

         JLabel labelstatus=new JLabel("");
      labelstatus.setBounds(300,380,200,20);
      labelstatus.setForeground(Color.red);
        add(labelstatus);

        try {
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select *from customer where meter_n='"+meter+"'");
            while(rs.next()){
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
           rs=c.s.executeQuery("select *from ebill where meter_no='"+meter+"'and month='"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
               labeltotalbill.setText(rs.getString("totalbill"));
               labelstatus.setText(rs.getString("status"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae){
                try {
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select *from ebill where meter_no='"+meter+"'and month='"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
               labeltotalbill.setText(rs.getString("totalbill"));
               labelstatus.setText(rs.getString("status"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }   
            }
        });
        pay=new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.white);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);

    back=new JButton("Back");
      back.setBackground(Color.BLACK);
      back.setForeground(Color.white);
      back.setBounds(230,460,100,25);
       back.addActionListener(this);
        add(back);
        getContentPane().setBackground(Color.white);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bill.png"));
        Image i2=i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==pay){
            try {
                Conn c=new Conn();
                c.s.executeUpdate("update ebill set status='paid' where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
            } catch (Exception e) {
                e.printStackTrace();
            }
         setVisible(false);
         new Paytm(meter);
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new PayBill("");
    }
}
