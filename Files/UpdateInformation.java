
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateInformation extends JFrame implements ActionListener {
    
    JButton cancel, update;
    JTextField tfaddress, tfcity, tfstate, tfemail, tfphone;
    String meter;
    
    //meter information is passed from login window through argument
    UpdateInformation(String meter){
        
        //title of the frame
        super("Update Information");
        
        //assigning local variable meter value to global variable meter
        this.meter = meter;
        
        //Layout
        setLayout(null);
        
        //creating frame
        setBounds(300,150,900,600);
        getContentPane().setBackground(Color.WHITE);
        
        //================ HEADING OF THE FRAME ================================
        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(320, 20, 300, 25);
        heading.setFont(new Font("sansserif",Font.PLAIN, 20));
        add(heading);
        
        //========= CREATING LABELS AND TEXTFIELDS =============================
        
        //We don't want user to change his/her details of name and meternumber
        //He/She is allowed to make changes in other details
        
        //------------------- NAME OF THE CUSTOMER -----------------------------        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 120, 100, 20);
        lblname.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(250, 120, 200, 20);
        name.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(name);
        
        //------------------- METER NUMBER OF THE CUSTOMER ---------------------
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100, 160, 100, 20);
        lblmeternumber.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 160, 200, 20);
        meternumber.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(meternumber);
        
        //------------------- ADDRESS OF THE CUSTOMER --------------------------
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 200, 100, 20);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(250, 200, 200, 20);
        tfaddress.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(tfaddress);
        
        //------------------- CITY OF THE CUSTOMER --------------------------
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100, 240, 100, 20);
        lblcity.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblcity);
        
        tfcity = new JTextField();
        tfcity.setBounds(250, 240, 200, 20);
        tfcity.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(tfcity);
        
        //------------------- STATE OF THE CUSTOMER ----------------------------
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100, 280, 100, 20);
        lblstate.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblstate);
        
        tfstate = new JTextField();
        tfstate.setBounds(250, 280, 200, 20);
        tfstate.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(tfstate);
        
        //------------------- EMAIL OF THE CUSTOMER ----------------------------
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 320, 100, 20);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(250, 320, 200, 20);
        tfemail.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(tfemail);
        
        //------------------- PHONE OF THE CUSTOMER --------------------------
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(100, 360, 100, 20);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(lblphone);
        
        tfphone = new JTextField("");
        tfphone.setBounds(250, 360, 200, 20);
        tfphone.setFont(new Font("Tahoma",Font.PLAIN, 14));
        add(tfphone);
        
        //=================== setting information of cutomer ===================
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        //================= SIGN UP IMAGE ON PANEL =============================
        
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icon/updateInformation.png"));
        Image scaleimage = image.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon imageicon = new ImageIcon(scaleimage);
        JLabel imagelabel = new JLabel(imageicon);
        imagelabel.setBounds(550, 150, 250, 250);
        add(imagelabel);
        
        //======================================================================
        
        //====================== BUTTON ========================================
        
        update = new JButton("Update");
        update.setBounds(140, 500, 90, 20);
        update.setFont(new Font("sansserif",Font.BOLD,13));
        update.setBackground(new Color(51,102,204));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(280, 500, 90, 20);
        cancel.setFont(new Font("sansserif",Font.BOLD,13));
        cancel.setBackground(new Color(255,57,57));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        new UpdateInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //if update button is clicked -> save it into database
        //How to save into database ?
        //Store updated information into string -> execute update query on databse
        //No need to get name and meternumber info bcs they are not editable 
        if(ae.getSource()==update){
           String lbladdress = tfaddress.getText();
           String lblcity = tfcity.getText();
           String lblstate = tfstate.getText();
           String lblemail = tfemail.getText();
           String lblphone = tfphone.getText();
           
           //since there is possibility of getting error -> add try or catch
           try{
             Conn c = new Conn();
             c.s.executeUpdate("update customer set address = '"+lbladdress+"', city = '"+lblcity+"', state = '"+lblstate+"', email = '"+lblemail+"', phone = '"+lblphone+"' where meter_no = '"+meter+"'");
             
             //after updating information -> show message
             JOptionPane.showMessageDialog(null, "Details Updated Successfully !");
             
             setVisible(false);
             
           } catch(Exception e){
               e.printStackTrace();
           }
        }
        
        //if cancel button pressed -> close current window
        else{
            setVisible(false);
        }
    }
    
}
