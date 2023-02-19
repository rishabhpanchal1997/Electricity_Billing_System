
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NewCustomer extends JFrame implements ActionListener {
    
    JTextField tfname, tfaddress, tfcity, tfstate, tfemail, tfphone;
    JButton next, cancel;
    JLabel lbmeter;
    NewCustomer(){
        
        //title
        super("New Customer");
        
        //setting frame size
        setBounds(380,120,800,600);
           
        // TO CREATE SECTIONS IN THE FRAME --> USE JPANEL
        
        //Creating white background of the frame
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        add(p);
        
        //======================= HEADING OF THE FRAME =========================
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(350, 15, 200, 20);
        heading.setFont(new Font("sansserif",Font.BOLD,16));
        p.add(heading);
        
        
        //=================== ASKING DETAILS FROM CUSTOMER =====================
        
        //--------------------- Name of customer -------------------------------
        JLabel lbname = new JLabel("Name");
        lbname.setBounds(60, 145, 100, 14);
        lbname.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbname);
        
        tfname = new JTextField();
        tfname.setBounds(200,145,180,18);
        tfname.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(tfname);
        
        
        //------------------------ Meter Number --------------------------------
        JLabel lbmeternumber = new JLabel("Meter Number"); 
        lbmeternumber.setBounds(60, 185, 100, 14);
        lbmeternumber.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbmeternumber);
        
        //We want meter number to be auto generated
        //We want meter number to be non editable --> use label to display
        //Use RANDOM class which is inside util package to create random number
        
        //creating label to store meter number generated from random generator
        lbmeter = new JLabel("");
        lbmeter.setBounds(200,185,100,14);
        lbmeter.setFont(new Font("sansserif",Font.PLAIN,15));
        p.add(lbmeter);
        
        //setting label with value got from random generator
        Random ran = new Random();
        long number = ran.nextLong()%1000000;
        lbmeter.setText(""+Math.abs(number));
        
                
       
        //---------------------------- Address ---------------------------------
        JLabel lbaddress = new JLabel("Address");
        lbaddress.setBounds(60, 225, 100, 14);
        lbaddress.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbaddress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(200,225,180,18);
        tfaddress.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(tfaddress);
        
        
        //---------------------------- City ---------------------------------
        JLabel lbcity = new JLabel("City");
        lbcity.setBounds(60, 265, 100, 16);
        lbcity.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbcity);
        
        tfcity = new JTextField();
        tfcity.setBounds(200,265,180,18);
        tfaddress.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(tfcity);
        
        
        //---------------------------- State ---------------------------------
        JLabel lbstate = new JLabel("State");
        lbstate.setBounds(60, 305, 100, 14);
        lbstate.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbstate);
        
        tfstate = new JTextField();
        tfstate.setBounds(200,305,180,18);
        tfstate.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(tfstate);
        
        
        //---------------------------- EMAIL ---------------------------------
        JLabel lbemail = new JLabel("Email");
        lbemail.setBounds(60, 345, 100, 14);
        lbemail.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200,345,180,18);
        tfemail.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(tfemail);
        
        
        //---------------------------- Phone Number ----------------------------
        JLabel lbphone = new JLabel("Phone Number");
        lbphone.setBounds(60, 385, 100, 14);
        lbphone.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200,385,180,18);
        tfphone.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(tfphone);
        
        
        //========================== BUTTONS ===================================
        
        //Next Button
        next = new JButton("Next");
        next.setBounds(120,470,80,20);
        next.addActionListener(this);
        p.add(next);
        
        
        //Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(240,470,80,20);
        cancel.addActionListener(this);
        p.add(cancel);
        
        //=================== Layout Change ====================================
        setLayout(new BorderLayout());
        add(p,"Center");
        
        
        
        //======================= Image on pane ================================
        ImageIcon customerImage = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomerframe2.jpg"));
        Image cImage = customerImage.getImage().getScaledInstance(320, 330, Image.SCALE_DEFAULT);
        ImageIcon cicon = new ImageIcon(cImage);
        JLabel image = new JLabel(cicon);
        add(image,"East");
        
        getContentPane().setBackground(Color.white);
        
        setResizable(false);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            
            //Extracting Details from text field
            String name = tfname.getText();
            String meter = lbmeter.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            
            //Creating query String 
            /*
            - We add our value by breading and adding value using + +
              e.g. String name = "Rishabh";
                   System.out.println("My name is "+name+" and i am postman");
            */
            
            //If admin has crated meter number for user --> It should be added in login table also
            //Because when user wants to signup --> user must have meter number to get password
            String query1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String query2 = "insert into login values('"+meter+"','','"+name+"','','')";
            
            try{
               Conn c = new Conn();
               c.s.executeUpdate(query1);
               c.s.executeUpdate(query2);
               
               JOptionPane.showMessageDialog(null, "Customer Details Added Successfully !");
               
               //setting all textfield blank after successfull details submission
               tfname.setText("");
               tfaddress.setText("");
               tfcity.setText("");
               tfstate.setText(""); 
               tfemail.setText("");
               tfphone.setText("");
               
               //calling next frame of meter_info to add meter information
               //passing meter number generated to next frame to auto show meternumber for same customer
               new MeterInfo(meter); //program flow transfered to new class with argument passed
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
        new NewCustomer();
    }
}
