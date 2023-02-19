
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class GenerateBill extends JFrame implements ActionListener{
    
    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;
            
    GenerateBill(String meter){
        
        super("Bill Receipt");
        
        this.meter = meter;
        
        setBounds(450,20,600,750);
        setLayout(null);
        
        
        //heading
        JLabel heading = new JLabel("Payment Receipt");
        heading.setBounds(200,10,300,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        //select month label
        JLabel select = new JLabel("Select Month : ");
        select.setBounds(110, 72, 110, 20);
        select.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(select);
        
        //------------------ To select Month -----------------------------------
        cmonth = new Choice();
        cmonth.setBounds(230, 70, 100, 20);
        cmonth.setFont(new Font("sansserif",Font.PLAIN,14));
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
        
        area = new JTextArea();
        JScrollPane pane = new JScrollPane(area);
        pane.setBounds(0, 100, 600, 1000);
        add(pane);
        
        //Generate Bill Button
        bill = new JButton("Generate");
        bill.setBounds(370, 70, 100, 24);
        bill.setFont(new Font("Tahoma",Font.PLAIN,14));
        bill.setBackground(new Color(0,102,204));
        bill.addActionListener(this);
        bill.setForeground(Color.WHITE);
        add(bill);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==bill){
            try{
               Conn c = new Conn();
               
               String month = cmonth.getSelectedItem();
               
               area.setText("\t\tBeacon Power Limited\n\tElectricity Bill Generated for the month of "+month+".\n\n\n");
               area.setFont(new Font("Tahoma", Font.PLAIN,15));
               
               ResultSet rs1 = c.s.executeQuery("SELECT * FROM bill where meter_no = '"+meter+"' and month = '"+cmonth.getSelectedItem()+"'");
               
               
               if(rs1.next() == false){
                   area.append("\t                 No data found for selected details.");
               }
               else{
                   ResultSet rs2 = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
                   while(rs2.next()){
                   area.append("\t             Customer Name :  "+rs2.getString("name")+"\n\n");
                   area.append("\t             Meter Number   :  "+rs2.getString("meter_no")+"\n\n");
                   area.append("\t             Address            :  "+rs2.getString("address")+"\n\n");
                   area.append("\t             City                  :  "+rs2.getString("city")+"\n\n");
                   area.append("\t             State                :  "+rs2.getString("state")+"\n\n");
                   area.append("\t             Email                :  "+rs2.getString("email")+"\n\n");
                   area.append("\t             Phone               :  "+rs2.getString("phone")+"\n\n");
                   }
                   
                   rs2 = c.s.executeQuery("select * from meter_info where meter_no = '"+meter+"'");
                   while(rs2.next()){
                   area.append("\t             Meter Location   :  "+rs2.getString("meter_location")+"\n\n");
                   area.append("\t             Meter Type        :  "+rs2.getString("meter_type")+"\n\n");
                   area.append("\t             Phase Code        :  "+rs2.getString("phase_code")+"\n\n");
                   area.append("\t             Bill Type            :  "+rs2.getString("bill_type")+"\n\n");
                   area.append("\t             Days                 :  "+rs2.getString("days")+"\n\n");
                   }
                   
                   rs2 = c.s.executeQuery("select * from bill where meter_no = '"+meter+"'");
                   while(rs2.next()){
                   area.append("\t             Units Consumed :  "+rs2.getString("units")+"\n\n");
                   area.append("\t             Total Bill            :  "+rs2.getString("totalbill")+"\n\n");
                   
                   }
               }
               
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        new GenerateBill("");
    }
    
}
