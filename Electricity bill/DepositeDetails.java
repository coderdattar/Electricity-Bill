import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
public class DepositeDetails extends JFrame implements ActionListener {
    Choice meternumber,cmonth;
    JTable table;
    JButton search, print;
    DepositeDetails(){
    super("Deposite Details");
    setSize(700,700);
    setLocation(400,100);
    setLayout(null);
    getContentPane().setBackground(Color.white);
    JLabel lblmeternumber=new JLabel("Search By Meter Number");
    lblmeternumber.setBounds(20,20,150,20);
    add(lblmeternumber);
    meternumber=new Choice();
    meternumber.setBounds(180,20,150,20);
    add(meternumber);
    try {
        Conn c=new Conn();
        ResultSet rs= c.s.executeQuery(" select * from customer");
   while(rs.next()){
      meternumber.add(rs.getString("meter_n"));
   }} catch (Exception e) {
        e.printStackTrace();
    }
    
  JLabel lblmonth=new JLabel("Search By Month");
    lblmonth.setBounds(400,20,100,20);
    add(lblmonth);

    cmonth=new Choice();
    cmonth.setBounds(520,20,150,20);
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
    
    table=new JTable();
    try {
        Conn c=new Conn();
       ResultSet rs= c.s.executeQuery("select* from ebill");
       table.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
        e.printStackTrace();
    }
    JScrollPane sp=new JScrollPane(table);
    sp.setBounds(0,100,700,600);
    add(sp);

    search=new JButton("Search");
    search.setBounds(20,70,80,20);
    search.addActionListener(this);
    add(search);

   print=new JButton("Print");
    print.setBounds(120,70,80,20);
    print.addActionListener(this);
    add(print);


    setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
          String query="select * from ebill where meter_no='"+meternumber.getSelectedItem()+"' and  month='"+cmonth+"'";
           try {
            Conn c=new Conn();
            ResultSet rs= c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
           } catch (Exception e) {
            e.printStackTrace();
           }

        }else{
         try {
            table.print();
         } catch (Exception e) {
            e.printStackTrace();
         }
        }
    }
    public static void main(String[] args) {
        new DepositeDetails();
    }
}
