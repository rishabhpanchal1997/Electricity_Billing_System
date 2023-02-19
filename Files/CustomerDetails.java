
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class CustomerDetails extends JFrame implements ActionListener{
    
    JTable table;
    JButton print;
    
    CustomerDetails(){
        //to set frame title
        super("Customer Details");
        
        //setting frame 
        setBounds(200,80,1200,700);
        
        //====================== CREATING TABLE ================================
       
        // We need to import rs2xml jar file to add details of database into table
        //We also need to add scroll bar for scrolling in the frame
        
        //object creation of jtable
        table = new JTable();
        
        //adding details in table from database
        try{
            Conn c = new Conn();
            
            //extracting data from customer table in database
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            //to convert rs object details intp xml file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setFont(new Font("sansserif", Font.PLAIN, 12));
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        setLayout(null);
        
        //to add scrollbar in window
        //we need to write code for scroll explicity in java (UNLLIKE IN HTML)
        //since layout is not mentioned -> border layout taken as default layout
        //hence no need to mention setBounds since it will set as per borderlayout locations        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 1200, 600);
        add(sp);
        
        
        //==================== BUTTONS FOR Print ==============================
        
        //print button
        //since layout is not mentioned -> border layout taken as default layout
        //hence no need to mention setBounds since it will set as per borderlayout locations
        print = new JButton("Print");
        print.addActionListener(this);
        print.setBounds(550,630,100,25);
        add(print);
         
        //======================================================================
        
        setVisible(true);
        setResizable(false);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        //if button pressed is not search PRINT 
        //we need to take care about print command bcs system may not have print command
        
            try{
                //this will open printing option of system
                //if system will have printer -> we can print the bill details
                //we can also create pdf if option is available
                table.print();
                
            } catch(PrinterException e){
                
            }
    }
    
    public static void main(String[] args){
        new CustomerDetails();
    }
    
}
