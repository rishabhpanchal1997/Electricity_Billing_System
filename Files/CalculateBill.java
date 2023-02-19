
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {
    
    JTextField tfunits;
    JButton submit, cancel;
    JLabel lbname, lbaddress;
    Choice meternumber, cmonth;
    
    CalculateBill(){
        
        //title
        super("Calculate Bill");
        
        //setting frame size
        setBounds(380,120,800,600);
           
        // TO CREATE SECTIONS IN THE FRAME --> USE JPANEL
        
        //Creating white background of the frame
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        add(p);
        
        //======================= HEADING OF THE FRAME =========================
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(120, 35, 250, 20);
        heading.setFont(new Font("sansserif",Font.BOLD,18));
        p.add(heading);
        
        
        
        //--------------------- Meter Number -----------------------------------
        //WE WILL FATCH METER NUMBER FROM DATABASE DYNAMICALLY
        
        JLabel lblmeternnumber = new JLabel("Meter Number");
        lblmeternnumber.setBounds(60, 145, 100, 14);
        lblmeternnumber.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblmeternnumber);
        
        meternumber = new Choice();
        
        //1. ESTABLISH CONNECTION FIRST
        //2. CREATE QUERY TO SELECT EVERYTING FROM TABLE 
        //3. PUT IT IN EXECUTEQUERY METHOD
        //4. STORE RETURNED VALUE IN RESULTSET VARIABLE
        //5. CREATE LOOP TO EXTRACT ONE COLUMN
        
        //This will allow to select one of the meter number from database
        try{
            Conn c = new Conn();
            ResultSet res = c.s.executeQuery("select * from customer");
            
            while(res.next()){
                //it will fetch meter_no from database and add into meternumber dropdown
                meternumber.add(res.getString("meter_no"));
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        meternumber.setBounds(200,142,180,13);
        meternumber.setFont(new Font("sansserif",Font.PLAIN,13));
        p.add(meternumber);
        
        
        //WE WANT TO SET NAME AND ADDRESS ACCORDING TO METER NUMBER AUTOMATICALLY
        //WE WILL FETCH DATA FROM DATABASE -> SET TO RESPECTIVE FIELD AS LABELS
        
        //------------------------ Name --------------------------------
        JLabel lblname = new JLabel("Name"); 
        lblname.setBounds(60, 185, 100, 14);
        lblname.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblname);
        
        
        lbname = new JLabel("");
        lbname.setBounds(200,185,150,14);
        lbname.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbname);
                  
       
        //---------------------------- Address ---------------------------------
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 225, 100, 14);
        lbladdress.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbladdress);
        
        lbaddress = new JLabel();
        lbaddress.setBounds(200,225,180,15);
        lbaddress.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lbaddress);
        
        
        //THIS WILL SET VALUES OF NAME AND ADDRESS ACCORDING TO METER NUMBER
        //IT WILL NOT MAKE CHANGES IF ANOTHER METER NUMBER SELECTED FROM DROPDOWN 
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer where meter_no='"+meternumber.getSelectedItem()+"'");
            while(rs.next()){
                //get the name from result received and set it on the name label
                lbname.setText(rs.getString("name"));
                
                //get the address from result received and set it on the address label
                lbaddress.setText(rs.getString("address"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        //WE WANT DETAILS OF NAME AND ADDRESS TO BE CHANGED WHEN ANOTHER METER NUMBER SELECTED
        //ITEMLISTENER --> These are used with checkboxes, radio buttons, combo boxes kinds of stuff.
        //ACTIONLISTENER --> They are used with buttons or menu.
        meternumber.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM customer where meter_no='"+meternumber.getSelectedItem()+"'");
                    while(rs.next()){
                    //get the name from result received and set it on the name label
                    lbname.setText(rs.getString("name"));
                
                    //get the address from result received and set it on the address label
                    lbaddress.setText(rs.getString("address"));
                    }
                } catch(Exception e){
                e.printStackTrace();
                }
            }
            
        });
        
        
        
        //---------------------------- Units Consumed --------------------------
        JLabel lblunits = new JLabel("Units Consumed");
        lblunits.setBounds(60, 265, 120, 16);
        lblunits.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblunits);
        
        tfunits = new JTextField();
        tfunits.setBounds(200,265,180,18);
        tfunits.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(tfunits);
        
        
        //---------------------------- Month of the Bill -----------------------
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(60, 309, 100, 14);
        lblmonth.setFont(new Font("sansserif",Font.PLAIN,14));
        p.add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(200,305,180,12);
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
        p.add(cmonth);
        

        //========================== BUTTONS ===================================
        //Submit Button
        submit = new JButton("Submit");
        submit.setBounds(120,470,80,20);
        submit.setBackground(new Color(51,102,204));
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        p.add(submit);
        
        
        //Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(240,470,80,20);
        cancel.setBackground(new Color(255,57,57));
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        p.add(cancel);
        
        
        //=================== Layout Change ====================================
        setLayout(new BorderLayout());
        add(p,"Center");
        
        
        
        //======================= Image on pane ================================
        ImageIcon customerImage = new ImageIcon(ClassLoader.getSystemResource("icon/calculatebill.png"));
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
        if(ae.getSource()==submit){
            
            //Extracting Details from field
            String meter = meternumber.getSelectedItem();
            String units = tfunits.getText();
            String month = cmonth.getSelectedItem();
            
            //====================== CALCULATING BILL ==========================
            int totalbill = 0;
            int units_consumed = Integer.parseInt(units); //conversion in int
            String query = "select * from tax";
            
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query); 
              
                while(rs.next()){
                   
                   //cost of total units --> UNITS*COST_PER_UNIT
                   totalbill += (units_consumed)*(Integer.parseInt(rs.getString("cost_per_unit")));
                   
                   //adding other charges in totalbill
                   totalbill += Integer.parseInt(rs.getString("meter_rent"));
                   totalbill += Integer.parseInt(rs.getString("service_charge"));
                   totalbill += Integer.parseInt(rs.getString("service_tax"));
                   totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                   totalbill += Integer.parseInt(rs.getString("fixed_tax"));  
                   
                }
            }       
            //==================================================================
            
            catch(Exception e){
                e.printStackTrace();
            }
            
            //ADDING CALCULATED BILL IN THE NEW TABLE
            String query2 = "insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not Paid ')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                
                //if added --> show message
                JOptionPane.showMessageDialog(null, "Customer Bll Updated Successfully");
                setVisible(false);
            }
            catch(Exception e){
                
            }
        }
        else {
            setVisible(false);
        }
        
    }
    
    public static void main(String[] args){
        new CalculateBill();
    }
}
