
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project extends JFrame implements ActionListener{
    
    String atype, meter;
    Project(String atype, String meter){
        
        //Title of frame
        super("Beacon Power Plant");
        
        this.atype = atype;
        this.meter = meter;
        
        
        //frame bounds
        setBounds(180,50,1200,700);
        
        //setting image fit to frame
        ImageIcon oimage = new ImageIcon(ClassLoader.getSystemResource("icon/earth.jpg"));
        Image image = oimage.getImage().getScaledInstance(1200, 700,Image.SCALE_DEFAULT);
        ImageIcon imageicon = new ImageIcon(image);
        JLabel fimage = new JLabel(imageicon);
        add(fimage);
        
        //========================== MENU BAR ==================================
        /*
        - Create Menubar on the frame
        - Adding Menu in Menubar
        - Add MenuItems in Menu and setting mnemonics
        */
        
        //------------------- Creation of Menubar ------------------------------
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb); //setting menubar on frame
        
            
        //------------------- Menu for Master ----------------------------------
        
        //Menu "Master" created
        JMenu master = new JMenu("Master");
        master.setForeground(Color.black);
        master.setFont(new Font("sansserif",Font.BOLD, 13));
        //mb.add(master); --> we want to add these conditionally hence added later
        
        /*  Adding MenuItems in Menu and setting mnemonic for MenuItems added 
          -> WHAT IS MNEMONIC ? 
           - We press CTRL+S to save data.
           - Here 'S' is called mnemonic and 'CTRL' is called keystroke.
        */
        
        //ADDING MENUITEMS IN MENU
        
        //New Customer
        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("sansserif", Font.BOLD, 12));
        newcustomer.setBackground(Color.white);
        ImageIcon nc = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomer.png"));
        Image ncimage = nc.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(ncimage));
        newcustomer.setMnemonic('N');
        newcustomer.addActionListener(this);
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        master.add(newcustomer);
        
        
        //Customer Details
        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("sansserif", Font.BOLD, 12));
        customerdetails.setBackground(Color.white);
        ImageIcon cd = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        Image cdimage = cd.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerdetails.setIcon(new ImageIcon(cdimage));
        customerdetails.setMnemonic('I');
        customerdetails.addActionListener(this);
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        master.add(customerdetails);
        
        
        //Deposit Details
        JMenuItem depositdetails = new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("sansserif", Font.BOLD, 12));
        depositdetails.setBackground(Color.white);
        ImageIcon dd = new ImageIcon(ClassLoader.getSystemResource("icon/depositPayment.png"));
        Image ddimage = dd.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositdetails.setIcon(new ImageIcon(ddimage));
        depositdetails.setMnemonic('D');
        depositdetails.addActionListener(this);
        depositdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        master.add(depositdetails);
        
        
        //Calculate Bill
        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("sansserif", Font.BOLD, 12));
        calculatebill.setBackground(Color.white);
        ImageIcon cb = new ImageIcon(ClassLoader.getSystemResource("icon/calculate.png"));
        Image cbimage = cb.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(cbimage));
        calculatebill.setMnemonic('C');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        master.add(calculatebill);
        
        
        //---------------------------------------------------------------------- 
        //------------------- Menu for Information -----------------------------
        
        //Menu "Information" created
        JMenu information = new JMenu("Information");
        information.setForeground(Color.black);
        information.setFont(new Font("sansserif",Font.BOLD, 13));
        //mb.add(information); --> we want to add these conditionally hence added later
        
        
        //ADDING MENUITEMS IN MENU
        
        //Update Information
        JMenuItem updateinformation = new JMenuItem("Update Information");
        updateinformation.setFont(new Font("sansserif", Font.BOLD, 12));
        updateinformation.setBackground(Color.white);
        ImageIcon ui = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image uiimage = ui.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateinformation.setIcon(new ImageIcon(uiimage));
        updateinformation.setMnemonic('U');
        updateinformation.addActionListener(this);
        updateinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        information.add(updateinformation);
        
        
        //View Information
        JMenuItem viewinformation = new JMenuItem("View Information");
        viewinformation.setFont(new Font("sansserif", Font.BOLD, 12));
        viewinformation.setBackground(Color.white);
        ImageIcon vi = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image viimage = vi.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewinformation.setIcon(new ImageIcon(viimage));
        viewinformation.setMnemonic('V');
        viewinformation.addActionListener(this);
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        information.add(viewinformation);
        
        
        //----------------------------------------------------------------------  
        //------------------- Menu for User -----------------------------

        //Menu "User" created
        JMenu user = new JMenu("User");
        user.setForeground(Color.black);
        user.setFont(new Font("sansserif",Font.BOLD, 13));
        //mb.add(user); --> we want to add these conditionally hence added later
        
        
        //ADDING MENUITEMS IN MENU
        
        //Pay Bill
        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("sansserif", Font.BOLD, 12));
        paybill.setBackground(Color.white);
        ImageIcon pb = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image pbimage = pb.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        paybill.setIcon(new ImageIcon(pbimage));
        paybill.setMnemonic('P');
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        user.add(paybill);
        
        
        //Bill Details
        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("sansserif", Font.BOLD, 12));
        billdetails.setBackground(Color.white);
        ImageIcon bd = new ImageIcon(ClassLoader.getSystemResource("icon/billdetails.png"));
        Image bdimage = bd.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(bdimage));
        billdetails.setMnemonic('B');
        billdetails.addActionListener(this);
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        user.add(billdetails);
        
        
        //----------------------------------------------------------------------  
        //------------------- Menu for Reports ----------------------------------
        
        //Menu "Report" created
        JMenu report = new JMenu("Report");
        report.setForeground(Color.black);
        report.setFont(new Font("sansserif",Font.BOLD, 13));
        //mb.add(report); --> we want to add these conditionally hence added later
        
        
        //ADDING MENUITEMS IN MENU
        
        //Generate Bill
        JMenuItem generatebill = new JMenuItem("Generate Bill");
        generatebill.setFont(new Font("sansserif", Font.BOLD, 12));
        generatebill.setBackground(Color.white);
        ImageIcon gb = new ImageIcon(ClassLoader.getSystemResource("icon/generatebill.png"));
        Image gbimage = gb.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generatebill.setIcon(new ImageIcon(gbimage));
        generatebill.setMnemonic('G');
        generatebill.addActionListener(this);
        generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        report.add(generatebill);
        
        
         //----------------------------------------------------------------------  
        //------------------- Menu for Utility -----------------------------

        //Menu "Utility" created
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.black);
        utility.setFont(new Font("sansserif",Font.BOLD, 13));
        utility.addActionListener(this);
        //mb.add(utility); --> we want to add these conditionally hence added later
        
        
        //ADDING MENUITEMS IN MENU
        
        //Notepad
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("sansserif", Font.BOLD, 12));
        notepad.setBackground(Color.white);
        ImageIcon np = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image npimage = np.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(npimage));
        notepad.setMnemonic('T');
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        utility.add(notepad);
        
        
        //Calculator
        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("sansserif", Font.BOLD, 12));
        calculator.setBackground(Color.white);
        ImageIcon cal = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image calimage = cal.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calimage));
        calculator.setMnemonic('M');
        calculator.addActionListener(this);
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        utility.add(calculator);
        
        //======================================================================
        
        //================= ADDING MASTER MENU ITEMS CONDITIONALLY =============
        //We want 'master' to be visible only to admin and not to customer   
        
        if(atype.equals("Admin")){
            mb.add(master);
        }
        else{
            mb.add(information);
            mb.add(user);
            mb.add(report);
        }
        
        //utility should be available for both admin and customer
        mb.add(utility);
        
        //======================================================================
         
        setLayout(new FlowLayout());
        setResizable(false);
        setVisible(true); 
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        //TWO METHODS TO STORE VALUE IN STRING
        //1. getSource method
        //2. getActionCommand
        
        String msg = ae.getActionCommand();
        
        //if New Customer option is selected
        if(msg.equals("New Customer")){
            new NewCustomer();
            setVisible(false);
        }
        
        //if Customer Details is selected
        else if(msg.equals("Customer Details")){
            new CustomerDetails();
            setVisible(false);
        }
        
        //if Deposit Details is selected
        else if(msg.equals("Deposit Details")){
            new DepositDetails();
            setVisible(false);
        }
        
        //if Calculate Bill is selected
        else if(msg.equals("Calculate Bill")){
            new CalculateBill();
            setVisible(false);
        }
        
        //if view information is selected
        else if(msg.equals("View Information")){
            new ViewInformation(meter);
        }
        
        //if update information is selected
        else if(msg.equals("Update Information")){
            new UpdateInformation(meter);
        }
        
        //if billdetails is selected
        else if(msg.equals("Bill Details")){
            new BillDetails(meter);
        }
        
        //if notepad is selected
        else if(msg.equals("Notepad")){
            
            //there is possibility that system may not have notepad 
            //hence try or catch clause is compulsory
            try{
                //we can run exe files directly
                Runtime.getRuntime().exec("notepad.exe");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //if calculator is selected
        else if(msg.equals("Calculator")){
            
            //there is possibility that system may not have calcultor 
            //hence try or catch clause is compulsory
            try{
                //we can run exe files directly
                Runtime.getRuntime().exec("calc.exe");
                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //if paybill is selected
        else if(msg.equals("Pay Bill")){
            new PayBill(meter);
        }
        
        //if generate bill is selected
        else if(msg.equals("Generate Bill")){
            new GenerateBill(meter);
        }
    }
    
    public static void main(String[] args){
        new Project("","");
    }
    
}
