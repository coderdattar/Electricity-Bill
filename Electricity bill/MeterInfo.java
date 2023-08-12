import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class MeterInfo extends JFrame implements ActionListener {
    JTextField tfname ,tfaddress,tdstate,tfcity,tfemail,tfphoneno;
    JButton next,cancel;
     JLabel lblmeter;
     Choice meterlocation,metertype,phasecode,billtype;
     String meternumber;
   MeterInfo(String meternumber){
    this.meternumber=meternumber;
    setSize(700, 500);
    setLocation(400,200);
    JPanel p= new JPanel();
    p.setLayout(null);
    p.setBackground(new Color(173,216,230));
    add(p);
    JLabel heading=new JLabel("Meter Information");
    heading.setBounds(180,10,200,25);
    heading.setFont(new Font("Tahoma",Font.PLAIN,24));
    p.add(heading);

    JLabel lblname=new JLabel("Meter Number");
    lblname.setBounds(100,80,100,20);
    p.add(lblname);

   JLabel lblmeternumber=new JLabel(meternumber);
    lblmeternumber.setBounds(240,80,100,20);
    p.add(lblmeternumber);

    
   JLabel lblmeterno=new JLabel("Meter Location");
    lblmeterno.setBounds(100,120,100,20);
    p.add(lblmeterno);
    meterlocation=new Choice();
    meterlocation.add("Outside");
    meterlocation.add("Inside");
    meterlocation.setBounds(240,120,200,20);
    p.add(meterlocation);
    
  
    JLabel lbladdress=new JLabel("Meter Type");
    lbladdress.setBounds(100,160,100,20);
    p.add(lbladdress);

    metertype=new Choice();
    metertype.add("Electric Meter");
    metertype.add("Solar Meter");
    metertype.add("Smart meter");
    metertype.setBounds(240,160,200,20);
    p.add(metertype);
   
        JLabel lblstate=new JLabel("Phase Code");
    lblstate.setBounds(100,200,100,20);
    p.add(lblstate);

    phasecode=new Choice();
    phasecode.add("011");
    phasecode.add("022");
    phasecode.add("033");
    phasecode.add("044");
    phasecode.add("055");
    phasecode.add("066");
    phasecode.add("077");
    phasecode.add("088");
    phasecode.add("099");
    phasecode.setBounds(240,200,200,20);
    p.add(phasecode);
        JLabel lblcity=new JLabel("Bill Type");
    lblcity.setBounds(100,240,100,20);
    p.add(lblcity);

    billtype=new Choice();
    billtype.add("Normal");
    billtype.add("Industrial");
    billtype.setBounds(240,240,200,20);
    p.add(billtype);
   

    JLabel lblemail=new JLabel("Days");
    lblemail.setBounds(100,280,100,20);
    p.add(lblemail);
    JLabel lblemails=new JLabel(" 30 Days");
    lblemails.setBounds(240,280,100,20);
    p.add(lblemails);
     JLabel lblphonenum=new JLabel("Note");
    lblphonenum.setBounds(100,320,100,20);
    p.add(lblphonenum);

     JLabel lblphonenums=new JLabel("By default bill is calculated for 30 days only");
    lblphonenums.setBounds(240,320,500,20);
    p.add(lblphonenums);

    
    next=new JButton("Submit");
    next.setBounds(220,390,100,25);
    next.setBackground(Color.BLACK);
    next.setForeground(Color.white);
    next.addActionListener(this);
    p.add(next);

    
   
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
   String meter =meternumber;
   String location=meterlocation.getSelectedItem();
   String type=metertype.getSelectedItem();
   String code=phasecode.getSelectedItem();
   String btype=billtype.getSelectedItem();
   String days="30 ";
   String query="insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+btype+"','"+days+"')";
  
   try {
    Conn c=new Conn();
    c.s.executeUpdate(query);
     JOptionPane.showMessageDialog(null, "Meter Information Added SuccessFully");
     setVisible(false);

   } catch (Exception e) {
    e.printStackTrace();
   }
      }else{
        setVisible(false);
      }
    }
  public static void main(String[] args) {
    new MeterInfo("") ;
  }  
}

