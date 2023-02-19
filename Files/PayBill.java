
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{
    
    Choice cmonth;
    String meter;
    JButton pay, back;
    
    PayBill(String meter){
        
        super("Bill Payment");
        
        setLayout(null);
        
        this.meter = meter;
        
        setBounds(350,150,850,600);
        
        
        //========================= HEADING LABEL ==============================
        JLabel heading = new JLabel("Bill Payment");
        heading.setFont(new Font("Tahoma",Font.PLAIN,18));
        heading.setBounds(380, 10, 100, 22);
        add(heading);
        
        //========================= METER NUMBER ==============================
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblmeternumber.setBounds(50, 100, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setFont(new Font("Tahoma",Font.PLAIN,14));
        meternumber.setBounds(200, 100, 100, 20);
        add(meternumber);
        
        
        //=========================== NAME =====================================
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblname.setBounds(50, 150, 100, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setFont(new Font("Tahoma",Font.PLAIN,14));
        name.setBounds(200, 150, 200, 20);
        add(name);
        
        
        //============================= MONTH ==================================
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblmonth.setBounds(50, 200, 100, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(200,200,120,12);
        cmonth.setFont(new Font("sansserif",Font.PLAIN,13));
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        
        //=========================== UNITS =====================================
        JLabel lblunits = new JLabel("Units ");
        lblunits.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblunits.setBounds(50, 250, 100, 20);
        add(lblunits);
        
        JLabel units = new JLabel("");
        units.setFont(new Font("Tahoma",Font.PLAIN,14));
        units.setBounds(200, 250, 100, 20);
        add(units);
        
        
        //========================= TOTAL BILL =================================
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setFont(new Font("Tahoma",Font.PLAIN,14));
        lbltotalbill.setBounds(50, 300, 100, 20);
        add(lbltotalbill);
        
        JLabel totalbill = new JLabel("");
        totalbill.setFont(new Font("Tahoma",Font.PLAIN,14));
        totalbill.setBounds(200, 300, 100, 20);
        add(totalbill);
        
        
        //========================= STATUS =================================
        JLabel lblstatus = new JLabel("Payment Status");
        lblstatus.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblstatus.setBounds(50, 350, 100, 20);
        add(lblstatus);
        
        JLabel status = new JLabel("");
        status.setFont(new Font("Tahoma",Font.PLAIN,14));
        status.setBounds(200, 350, 100, 20);
        add(status);
        
        //===============SETTING INFORMATION AGAINST FIELDS ====================
        
        //Name and Meter number can be retrieved from customer table
        //but Units, Total bill and staus is not available in customer table

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                meternumber.setText(meter);
                name.setText(rs.getString("name"));
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        cmonth.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+cmonth.getSelectedItem()+"'");
                    
                    //if resultset is empty -> set below fields empty
                    if(!rs.isBeforeFirst()){
                        units.setText("");
                        totalbill.setText("");
                        status.setText("");
                    }
                    
                    //if resultset is not empty -> set fields with proper values
                    while(rs.next()){
                        units.setText(rs.getString("units"));
                        totalbill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }
            
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
        
        //======================== BUTTONS =====================================
        
        pay = new JButton("Pay");
        pay.setBackground(new Color(30,171,96));
        pay.setFont(new Font("Tahoma",Font.BOLD, 14));
        pay.setBounds(320, 500, 80, 22);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("Back");
        back.setBackground(new Color(128,128,128));
        back.setForeground(Color.WHITE);
        back.setBounds(450, 500,80,22);
        back.setFont(new Font("Tahoma",Font.BOLD,14));
        back.addActionListener(this);
        add(back);
        
        //==================== set white background ============================
        getContentPane().setBackground(Color.white);
        
        
        //===================== SETTIGN IMAGE ON FRAME =========================
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/billpayment.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 130, 200, 200);
        add(image);
        
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==pay){
            
            //payment gateway
           new PaymentGateway(meter);
            
           try{
               
               Conn c = new Conn();
               c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+cmonth.getSelectedItem()+"'");
               
           } catch(Exception e){
               e.printStackTrace();
           }
        }
        else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new PayBill("");
    }
    
}
