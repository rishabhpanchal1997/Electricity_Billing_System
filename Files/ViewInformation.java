
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewInformation extends JFrame implements ActionListener{
    
    String meter;
    JButton cancel;
    
    ViewInformation(String meter){
        
        //setting title of frame
        super("View Information");
        
        this.meter=meter;
        
        //frame creation
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        //================ HEADING OF THE FRAME ================================
        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(305, 20, 300, 20);
        heading.setFont(new Font("sansserif",Font.PLAIN, 20));
        add(heading);
        
        //==================== CONTENET OF THE FRAME ===========================
        
        //------------------- NAME OF THE CUSTOMER -----------------------------
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(300, 120, 100, 20);
        lblname.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(450, 120, 200, 20);
        name.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(name);
        
        //------------------- METER NUMBER OF THE CUSTOMER ---------------------
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(300, 160, 100, 20);
        lblmeternumber.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(450, 160, 200, 20);
        meternumber.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(meternumber);
        
        //------------------- ADDRESS OF THE CUSTOMER --------------------------
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(300, 200, 100, 20);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lbladdress);
        
        JLabel address = new JLabel("");
        address.setBounds(450, 200, 200, 20);
        address.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(address);
        
        //------------------- CITY OF THE CUSTOMER --------------------------
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(300, 240, 100, 20);
        lblcity.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblcity);
        
        JLabel city = new JLabel("");
        city.setBounds(450, 240, 200, 20);
        city.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(city);
        
        //------------------- STATE OF THE CUSTOMER ----------------------------
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(300, 280, 100, 20);
        lblstate.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblstate);
        
        JLabel state = new JLabel("");
        state.setBounds(450, 280, 200, 20);
        state.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(state);
        
        //------------------- EMAIL OF THE CUSTOMER ----------------------------
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(300, 320, 100, 20);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblemail);
        
        JLabel email = new JLabel("");
        email.setBounds(450, 320, 200, 20);
        email.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(email);
        
        //------------------- PHONE OF THE CUSTOMER --------------------------
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(300, 360, 100, 20);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblphone);
        
        JLabel phone = new JLabel("");
        phone.setBounds(450, 360, 200, 20);
        phone.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(phone);
        
        //=================== setting information of cutomer ===================
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                phone.setText(rs.getString("phone"));
                email.setText(rs.getString("email"));
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        //====================== BUTTON ========================================
        cancel = new JButton("Cancel");
        cancel.setBounds(370, 500, 90, 20);
        cancel.setFont(new Font("sansserif",Font.BOLD,13));
        cancel.setBackground(new Color(255,57,57));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
       
        
        setVisible(true);
        setResizable(false);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        try{
            if(ae.getSource()==cancel){
                setVisible(false);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public static void main(String[] args){
        new ViewInformation("");
    }
}
