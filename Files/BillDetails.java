
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class BillDetails extends JFrame {
    
    String meter;
    BillDetails(String meter){
       
        //setting title of the frame
        super("Bill Details");
        
        this.meter=meter;
        
        //creating frame
        setBounds(400,150,700,650);
        getContentPane().setBackground(Color.WHITE);
        
        //creating table
        JTable table = new JTable();
        
        //getting information from database
        try{
            Conn c = new Conn();
            String query = "select * from bill where meter_no = '"+meter+"'";
            
            //storing result brought from database in resultset object
            ResultSet rs = c.s.executeQuery(query);
            
            //giving data of resultset object to table
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        //setting bounds of table in the frame
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);
        
        setVisible(true); 

    }
    
    public static void main(String[] args){
        new BillDetails("");
    }
    
}
