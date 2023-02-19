
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MeterInfo extends JFrame implements ActionListener {
   
    JButton submit;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;
    
    //argument passed is meter number received from previous class
    MeterInfo(String meternumber){
        
        //title
        super("Meter Information");
        
        //saving local variable in class level variable
        this.meternumber = meternumber;
        
        //setting frame size
        setBounds(380,120,800,600);
           
        // TO CREATE SECTIONS IN THE FRAME --> USE JPANEL
        
        //Creating white background of the frame
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        add(p);
        
        //======================= HEADING OF THE FRAME =========================
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(320, 15, 300, 20);
        heading.setFont(new Font("sansserif",Font.BOLD,16));
        p.add(heading);
        
        
        //=================== ASKING DETAILS FROM CUSTOMER =====================
        
        //--------------------- Meter Number -------------------------------
        JLabel lbnumber = new JLabel("Meter Number");
        lbnumber.setBounds(60, 145, 100, 15);
        lbnumber.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbnumber);
        
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(200,145,180,15);
        lblmeternumber.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblmeternumber);
        
        
        //------------------------ Meter Location ------------------------------
        JLabel lblocation = new JLabel("Meter Location"); 
        lblocation.setBounds(60, 188, 100, 15);
        lblocation.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblocation);
        
        meterlocation = new Choice();
        meterlocation.setBounds(200, 185, 180, 13);
        meterlocation.setFont(new Font("sansserif",Font.PLAIN,13));
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        p.add(meterlocation);
        
       
        //---------------------------- Meter Type ------------------------------
        JLabel lblmetertype = new JLabel("Meter Type"); 
        lblmetertype.setBounds(60, 228, 100, 17);
        lblmetertype.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblmetertype);
        
        metertype = new Choice();
        metertype.setBounds(200, 225, 180, 15);
        metertype.setFont(new Font("sansserif",Font.PLAIN,13));
        metertype.add("Conventional Meter");
        metertype.add("Smart Meter");
        p.add(metertype);
        
        
        //---------------------------- Phase Code ------------------------------
        JLabel lblphasecode = new JLabel("Phase Code"); 
        lblphasecode.setBounds(60, 268, 100, 15);
        lblphasecode.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblphasecode);
        
        phasecode = new Choice();
        phasecode.setBounds(200, 265, 180, 15);
        phasecode.setFont(new Font("sansserif",Font.PLAIN,13));
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        p.add(phasecode);
        
        
        //---------------------------- Bill Type -------------------------------
        JLabel lblbilltype = new JLabel("Bill Type");
        lblbilltype.setBounds(60, 308, 100, 17);
        lblbilltype.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblbilltype);
        
        billtype = new Choice();
        billtype.setBounds(200,305,180,15);
        billtype.setFont(new Font("sansserif",Font.PLAIN,13));
        billtype.add("Domestic");
        billtype.add("Industrial");
        p.add(billtype);
        
        
        //---------------------------- Meter Cycle Days ------------------------
        JLabel lbdays = new JLabel("Bill Cycle Days");
        lbdays.setBounds(60, 348, 100, 17);
        lbdays.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbdays);
        
        JLabel days = new JLabel("30");
        days.setBounds(200, 350, 100, 14);
        days.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(days);
        
        
        //---------------------------- Note ------------------------------------
        JLabel lbnote = new JLabel("Note");
        lbnote.setBounds(60, 388, 100, 14);
        lbnote.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbnote);
        
        JLabel note = new JLabel("By default, the Bill is calculated for 30 days only.");
        note.setBounds(200, 388, 500, 17);
        note.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(note);
        
        
        //========================== BUTTONS ===================================
        
        //Submit Button
        submit = new JButton("Submit");
        submit.setBounds(170,470,80,20);
        submit.addActionListener(this);
        p.add(submit);
     
        
        //=================== Layout Change ====================================
        setLayout(new BorderLayout());
        add(p,"Center");
        
        
        
        //======================= Image on pane ================================
        ImageIcon customerImage = new ImageIcon(ClassLoader.getSystemResource("icon/meter.png"));
        Image cImage = customerImage.getImage().getScaledInstance(280, 300, Image.SCALE_DEFAULT);
        ImageIcon cicon = new ImageIcon(cImage);
        JLabel image = new JLabel(cicon);
        add(image,"East");
        
        getContentPane().setBackground(Color.white);
        
        setResizable(false);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            
            //Extracting Details from fields
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";
            
            //String to execute query and add data in meter_info table
            String query1 = "insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
            
            try{
               Conn c = new Conn();
               c.s.executeUpdate(query1);
               
               JOptionPane.showMessageDialog(null, "Meter Information Added Successfully !");
               setVisible(false);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
        
    }
    
    public static void main(String[] args){
        new MeterInfo("");
    }
}
