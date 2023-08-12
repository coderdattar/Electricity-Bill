import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class Billdetails extends JFrame {
    String meter;
    Billdetails( String meter){
        this.meter=meter;
setSize(700, 650);
setLocation(400,150);
getContentPane().setBackground(Color.white);
JTable table =new JTable();
try {
    Conn c=new Conn();
    String query="select*from ebill where meter_no='"+meter+"'";
    ResultSet rs=c.s.executeQuery(query);
    table.setModel(DbUtils.resultSetToTableModel(rs));
} catch (Exception e) {
    e.printStackTrace();
}
JScrollPane sp=new JScrollPane(table);
sp.setBounds(0,0,700,650);
add(sp);
setVisible(true);
    }
    public static void main(String[] args) {
        new Billdetails("");
    }
}
