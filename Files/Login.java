
package electricity.billing.system;

import javax.swing.*;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
        
public class Login extends JFrame implements ActionListener{
    
    //Globally declared 
    JButton login,cancel,signup;
    JTextField username;
    JPasswordField password;
    Choice loggingin;
    Login(){
        
        //we can give frame heading using super
        //super must be the first statement in constructor
        super("Login");
        
        //to select whole frame to make edits in frame --> getContentPane
        //it will set backgroud color white
        getContentPane().setBackground(Color.WHITE);
        
        //to make other layouts disable --> make them null
        //only after that, you will be able to create custom layout
        setLayout(null);
        
        
        //========================== USERNAME SECTION ==========================
        
        //to write anything on a frame --> use jlabel
        //first create an object and then add on the frame using add function
        JLabel lblusername = new JLabel("Username");
        
        //custom layout can be created using setBounds of setLayout
        //note that x and y are location of component respect to frame not screen
        lblusername.setBounds(300, 20, 100,20 );
        add(lblusername);
        
        //TEXTFIELD FOR USERNAME
        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);
        
        //========================== PASSWORD SECTION ==========================
        
        //LABEL FOR PASSWORD
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 60, 100,20 );
        add(lblpassword);
        
        //TEXTFIELD FOR PASSWORD 
        password = new JPasswordField();
        password.setBounds(400, 60, 150, 20);
        add(password);
        
        
        //========================== LOGIN AS SECTION ==========================
        //LABEL FOR LOGIN AS
        JLabel logginginas = new JLabel("Login as");
        logginginas.setBounds(300, 100, 100,20 );
        add(logginginas);
        
        //DROPDOWN FOR LOGIN AS
        //we have two options : Choice(AWT) and JComboBox(SWING)
        loggingin = new Choice(); //creates empty dropdown
        loggingin.add("Admin");
        loggingin.add("Customer");
        loggingin.setBounds(400, 100, 150, 20);
        add(loggingin);

        //=========================== BUTTON SECTION ===========================
                //LOGIN BUTTON
        login = new JButton("Login");
        login.setBounds(330, 160, 75, 20);
        login.setBackground(new Color(60,179,113));
        login.setForeground(Color.WHITE);
        login.addActionListener(this); //to track action made on button
        add(login);
        
        
        //CANCEL BUTTON
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 160, 75, 20);
        cancel.setBackground(new Color(255,57,57));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this); //to track action made on button
        add(cancel);
        
        //SIGNUP BUTTON
        signup = new JButton("SignUp");
        signup.setBounds(380, 210, 85, 20);
        signup.setBackground(new Color(0,102,204));
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        //========================== LOGIN PAGE IMAGE SECTION ==================
        
        //to add image on the login page
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image imageconv = image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(imageconv);
        JLabel imageicon = new JLabel(icon);
        imageicon.setBounds(40, 30, 200, 200);
        add(imageicon);
        
        //======================== FRAME DIMENSIONS ============================
         
        setSize(640,300);
        setLocation(400,200);
        setResizable(false);
        setVisible(true);
    }
    
    //=================== IMPLEMENTING ACTIONLISTENER =========================
    
    //ActionListener Interface has abstract method named 'actionPerfomed'
    //wich must be implemented to remove error
    //implementation of this method will detect action made on button
    
    //DECLARATION OF BUTTON:
    //Ensure that buttons are globally declared because if not declared globally
    //it will give error due to out of scope declaration
    
    //NOTE : we need to tell specifically on which button we need action to be 
    //perfomed --> to do that apply method to that button
    //eg. submit.acionListener(this) 
    
    //HOW TO DIFFERENTIATE WHICH BUTTON IS PRESSED ?
    //Find the source of action by using below method (getSource())

    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        //if login pressed --> verify details
        //if details are matched -> redirect to cutomer/admin portal
        if(ae.getSource()==login){
            
            //extracting information from fields and storing into string
            String susername = username.getText();
            String spassword = password.getText();
            String user = loggingin.getSelectedItem();
            
            //we are going to work with database -> create connection
            try{
                Conn c = new Conn();
                
                //creating query to match username and password with database
                String query = "select *  from login where username ='"+susername+"' and password ='"+spassword+"' and user='"+user+"'";
                
                //Here we are not updating table details
                //since it is not dml command --> not using 'executeUpdate' function
                //Here we will use ddl command --> use 'executeQuery'
                //executeQuery returns object of ResultSet class -> store returned value in that class object
                ResultSet rs = c.s.executeQuery(query);
                
                //if details matched -> redirect to admin page
                //Open window based on user passed in argument of  Project Cnstructor
                //We need meter number in the next frame -> passing as arguments 
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter); 
                }
                //if data not found -> we get null object -> show popup
                //meke username and password filed empty -> set that field empty
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!");
                    username.setText("");
                    password.setText("");
                }
                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //if cancel pressed --> close the window
        else if(ae.getSource()==cancel){
            setVisible(false);
        }
        
        //if signup pressed --> redirect to signup page
        //first close current frame
        //then create object of login frame to pop up login page
        else if(ae.getSource()==signup){
            setVisible(false);
            new Signup();
        }
        
    }
    
    public static void main(String[] args){
        new Login();
    }
    
}
