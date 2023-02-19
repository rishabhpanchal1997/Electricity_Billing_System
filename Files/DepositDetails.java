
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener{
    
    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;
    
    DepositDetails(){
        //to set frame title
        super("Deposit Details");
        
        //setting frame 
        setBounds(400,100,800,700);
        
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
        //=============== SEARCH BY METER NUMBER ===============================
        
        //CREATING LABEL
        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(50, 30, 160, 20);
        lblmeternumber.setFont(new Font("sansserif",Font.BOLD, 13));
        add(lblmeternumber);
        
        //CREATING DROPDOWN 
        meternumber = new Choice();
        meternumber.setBounds(220,30,120,20);
        add(meternumber);
        
        //code to get meter number from database
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM CUSTOMER");
            
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }
            
            
        } catch(SQLException e){
            
        }
        
        //======================================================================
        
        //====================== SEARCH BY MONTH ===============================
        
        //CREATING LABEL
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(420, 30, 110, 20);
        lblmonth.setFont(new Font("sansserif",Font.BOLD, 13));
        add(lblmonth);
        
        //CREATING DROPDOWN 
        cmonth = new Choice();
        cmonth.setBounds(540,30,120,20);
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
        
        //======================================================================
        
        //====================== CREATING TABLE ================================
       
        // We need to import rs2xml jar file to add details of database into table
        //We also need to add scroll bar for scrolling in the frame
        
        //object creation of jtable
        table = new JTable();
        
        //adding details in table from database
        try{
            Conn c = new Conn();
            
            ResultSet rs = c.s.executeQuery("select * from bill");
            
            //to convert rs object details intp xml file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        //to add scrollbar in window
        //we need to write code for scroll explicity in java (UNLLIKE IN HTML)        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 150, 800, 700);
        add(sp);
        
        
        //==================== BUTTONS FOR SEARCH ==============================
        
        //search button
        search = new JButton("Search");
        search.setBounds(280,100,80,20);
        search.addActionListener(this);
        add(search);
        
        //print button
        print = new JButton("Print");
        print.setBounds(400,100,80,20);
        print.addActionListener(this);
        add(print);
        
        
        
        //======================================================================
        
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        //if button pressed is search button
        //search for the values of meter number and month selected
        if(ae.getSource()==search){
            String query = "select * from bill where meter_number = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";
            
            //executing query
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch(SQLException e){
                
            }
        }
        
        //if button pressed is not search button 
        //we need to take care about print command bcs system may not have print command
        else{
            
            try{
                //this will open printing option of system
                //if system will have printer -> we can print the bill details
                //we can also create pdf if option is available
                table.print();
                
            } catch(PrinterException e){
                
            }
            
            
        }
    }
    
    public static void main(String[] args){
        new DepositDetails();
    }
    
}
