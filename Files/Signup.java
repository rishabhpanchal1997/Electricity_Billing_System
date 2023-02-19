
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

//when one package is imported in java using *, sub packages are not imported
//hence sub packages are needed to be imported manually
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


//============================ FUNCTIONALITY ===================================

/*
=> If Account Type is ADMIN 
- Make Meter Number will not be visible (No details shown/non editable)
- Keep username editable
- Show name  in the name label and it will not be aditable

=> If Account Type is Customer
- Auto-Filling of name field : As soon as customer enters meter number and 
  get out of meter number field, name of the customer should be fetched from 
  database and shown against name field.
- 

*/


//==============================================================================

public class Signup extends JFrame implements ActionListener{
    
    //Globally declared to use outside the signup method
    JButton create,back;
    Choice accountType;
    JTextField meter, username, name, password;
    
    Signup(){
        super("Sign Up");
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        //===================== PANEL FOR MAIN CONTENT ========================
        
        //JPanel is used to organise/group and hold components inside frame
        //it is used to divide frame into different parts
        JPanel panel = new JPanel(); //object created
        panel.setBounds(25,20,640,315); //dimensions of panel
        
        //to set panel background colour
        panel.setBackground(Color.white);
        panel.setLayout(null); //setting panel layout null
        
        //=========================== PANEL BORDER =============================
        
        //to set border of panel
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0,102,204),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(0,102,204)));
               
        //to set panel foreground(text in panel) colour
        panel.setForeground(new Color(51,102,255));
        
        add(panel); //adding panel on the frame
     
        //========================== ADDING COMPONENTS ON PANEL ================
        
        //-------- ADDING LABEL AND DROPDOWN OF CREATE ACCOUNT AS --------------
        
        //LABEL FOR CREATE ACCOUNT AS :
        
        //to add component on panel --> you need to call panel then add
        //panel.add(component_name);
        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(70, 50, 140, 20);
        createAs.setForeground(Color.BLACK);
        createAs.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(createAs);
        
        //DROPDOWN MENU FOR ADMIN AND CUSTOMER SELECTION :
        //We need to add actionlistener here to detect type of account
        //based on type of account selected -> functionality will be decided
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(230,50,150,20);
        //if done add(accountType) it will add on frame, not on the panel 
        //if added on frame then panel will hide it and it will not be visible
        panel.add(accountType);
        
        
        //---------- ADDING METER NUMBER LABEL AND TEXT FIELD ------------------
        
        /*
        NOTE : Account type is Admin selected by default and we don't want 
        meter label and field to be visible. We want it to be visible only
        when account type is Customer. So make meter invisible for default (Admin)
        and make it appear when account type is customer.
        */
        
        //LABEL FOR METER NUMBER :
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(70, 90, 140, 20);
        lblmeter.setForeground(Color.BLACK);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,12));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        //TEXTFIELD FOR ENTERING METER NUMBER :
        //As soon as focus is get out of this field -> name of the customer should be autofilled
        meter = new JTextField();
        meter.setBounds(230, 90, 150, 20);
        meter.setVisible(false);
        panel.add(meter);
        
        
        //----------- ADDING USERNAME LABEL AND TEXTFIELD ----------------------
        
        //LABEL FOR USERNAME :
        JLabel lblusername = new JLabel("User Name");
        lblusername.setBounds(70, 130, 150, 20);
        lblusername.setForeground(Color.BLACK);
        lblusername.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(lblusername);
        
        //TEXTFIELD FOR USERNAME :
        username = new JTextField();
        username.setBounds(230, 130, 150, 20);
        panel.add(username);
        
        //------------ ADDING NAME LABEL AND TEXTFIELD -------------------------
        
        //LABEL FOR NAME :
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 170, 150, 20);
        lblname.setForeground(Color.BLACK);
        lblname.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(lblname);
        
        //TEXTFIELD FOR NAME : 
        name = new JTextField();
        name.setBounds(230, 170, 150, 20);
        panel.add(name);
        
        //------------------ FILLING NAME FIELD AUTOMATICALLY ------------------
        
        //this will help to automatically fill name field for given meter number
        //here we will override methods of this abstract method
        //Name should be visible 
        meter.addFocusListener(new FocusListener(){
            
        @Override
        public void focusGained(FocusEvent fe){
                
        }
            //In database, Blank meternumber field denotes Admin and 
            //vice versa, for admin -> meternumber field must be blank
            //When Admin is selected -> no meter number textfield -> meter number will not be visible
        @Override
        public void focusLost(FocusEvent fe){
            
            //IF CUSTOMER IS SELECTED AND METERNUMBER NOT ENTERED ==============
            //If meternumber field left empty by mistake by customer -> it must not show 
            //the name of person with no meternumber (in short Admin) in databasr
            if(meter.getText().equals("")){
                name.setText("");
                return;
            }
            
            
            //IF CUSTOMER IS SELECTED AND METERNUMBER ENTERED ==================
            //if meter number is entered properly than compare it with database
            //show the name of customer matching with meternumber entered 
            //when focus is taken off from meternumber field -> execute it
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                while(rs.next()){
                    name.setText(rs.getString("name"));
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

            
        });
        
        //----------------------------------------------------------------------
        
        //---------- ADDING PASSWORD LABEL AND TEXTFIELD -----------------------
        
        //LABEL FOR PASSWORD :
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(70, 210, 150, 20);
        lblpassword.setForeground(Color.BLACK);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(lblpassword);
        
        //TEXTFIELD FOR PASSWORD :
        password = new JTextField();
        password.setBounds(230, 210, 150, 20);
        panel.add(password);
        
        
        //=========== MAKING METER FIELD VISIBLE FOR CUSTOMER ==================
        
        //meter field should be visible for customers only
        //name field should not be editable by customer
        //name field should be editable in case of Admin
        //if state of Customer and Admin changed while filling details -> field shoud reset
        accountType.addItemListener(new ItemListener(){
            
            @Override
            public void itemStateChanged(ItemEvent ae){
                String user = accountType.getSelectedItem();
                 
                if(user.equals("Customer")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else{
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    if(!name.getText().equals("")){
                        name.setText("");
                    }
                    name.setEditable(true);
                    
                }
            }
        });   
        
        //======================================================================
        
        
        //=================== BUTTONS FOR BACK AND CREATE ======================
        
        //BUTTON FOR BACK
        back = new JButton("Back");
        back.setBounds(100, 270, 100, 25);
        back.setBackground(new Color(128,128,128));
        back.setForeground(Color.white);
        back.addActionListener(this);
        panel.add(back);
         
        //BUTTON FOR SIGNUP
        create = new JButton("Create");
        create.setBounds(240, 270, 100, 25);
        create.setBackground(new Color(51,102,204));
        create.setForeground(Color.white);
        create.addActionListener(this); //to track action made on button
        panel.add(create);
        
        
        //================= SIGN UP IMAGE ON PANEL =============================
        
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icon/new.png"));
        Image scaleimage = image.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon imageicon = new ImageIcon(scaleimage);
        JLabel imagelabel = new JLabel(imageicon);
        imagelabel.setBounds(420, 50, 180, 180);
        panel.add(imagelabel);
        
        //======================================================================
        
        
        setVisible(true);
    }
    
    //=============== OVERRIDING METHOD OF ActionListener class ================
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //======================================================================
        //if pressed create --> add details to database
        /* Step 1 : Retrieve information from the textfiels of signup page using
                    getSelectedItem and store it in the String format
           Step 2 : Store fetched details in table of databse
        */
        
        if(ae.getSource()==create){
            
            //--------- Storing filled details in the String format ------------
            String atype = accountType.getSelectedItem(); //dropdown type -> getitem
            String susername = username.getText(); //text -> gettext
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();
            
            
            //------- Storig fetched details into tabel of our database -------- 
            //Since mysql is external entity -> possibility of getting error
            //hence use try or catch
            
            //WHENEVER WE WANT TO WORK WITH DATABASE --> ESTABLISH CONNECTION WITH MYSQL
            //TO MAKE CONNECTION --> CREATE OBJECT OF CONNECTION CLASS
            
            //Step 1 : Create object of connection class to establish connection
            //Step 2 : Create query
            //Step 3 : Hit query using statement and query created
            try{
                Conn c = new Conn(); //step 1
                
                //step 2 --> sequence of arguments same as order of variables in table column/table schema
                
                /*
                --> Why do we need string structure of query created as below to insert values ? 
                  - We need to create whole query in string form.
                  - We have to enter values in the varchar form (between '') 
                  - Whole string created must represent structure of MySql query (all values to be added) 
                    e.g. INSERT INTO table_name VALUES ('value1', 'value2', 'value3', ...);)
                  - Since our extracted values are to be added in this string of query, we need to concat
                    in such way that it represent query
                  - We add our value by breading and adding value using + +
                    e.g. String name = "Rishabh";
                         System.out.println("My name is "+name+" and i am postman");
                */
                
                //Query to be executed depends on type of user
                //If Admin -> All details to be added since Admin can signup only
                //If Customer -> Table needs to be updated bcs some information of customer is added by admin
                
                
                String query;
                
                if(atype.equals("Admin")){
                    query = "insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                }
                else {
                    query = "update login set username = '"+susername+"', password = '"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
                }
                
                //step 3 --> access statement which will further be useful for executing query created
                c.s.executeUpdate(query);
                
                
                //if query successfully executed --> show popup, close the window, open login window
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //======================================================================\
        //if pressed back --> go back to login page
        else if(ae.getSource()==back){
            setVisible(false);
            new Login();
        }
    }
    
    
    public static void main(String[] args){
        new Signup();
    }
    
}
