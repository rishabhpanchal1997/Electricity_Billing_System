
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class PaymentGateway extends JFrame implements ActionListener{
    
    String meter;
    JButton gpay, paytm, phonepe, back;
    PaymentGateway(String meter){
        
        super("Payment Gateway");
        
        this.meter=meter;
        
        setLayout(null);
        setBounds(500,200,500,500);
        
        //============== HEADING ===============================================
        JLabel heading = new JLabel("Pay Your BillS Easily");
        heading.setBounds(150, 20, 200, 25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        //=========================== BUTTONS ==================================
        gpay = new JButton("Gpay");
        gpay.setBounds(60,250, 100, 30);
        gpay.setBackground(new Color(0,102,204));
        gpay.setForeground(Color.WHITE);
        gpay.setFont(new Font("Tahoma", Font.PLAIN,18));
        gpay.addActionListener(this);
        add(gpay);
        
        
        paytm = new JButton("Paytm");
        paytm.setBounds(180,250, 100, 30);
        paytm.setBackground(new Color(0,102,204));
        paytm.setForeground(Color.WHITE);
        paytm.setFont(new Font("Tahoma", Font.PLAIN,18));
        paytm.addActionListener(this);
        add(paytm);
        
        phonepe = new JButton("PhonePe");
        phonepe.setBounds(300,250, 110, 30);
        phonepe.setBackground(new Color(0,102,204));
        phonepe.setForeground(Color.WHITE);
        phonepe.setFont(new Font("Tahoma", Font.PLAIN,18));
        phonepe.addActionListener(this);
        add(phonepe);
        
        back = new JButton("Back");
        back.setBounds(200, 400, 80, 25);
        back.setBackground(new Color(240,231,204));
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==gpay){
            try{
               Desktop.getDesktop().browse(new URL("https://pay.google.com/").toURI());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==phonepe){
            try{
               Desktop.getDesktop().browse(new URL("https://www.phonepe.com/how-to-pay/pay-by-phonepe/web/").toURI());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==paytm){
            try{
               Desktop.getDesktop().browse(new URL("https://paytm.com/login").toURI());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==back){
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new PaymentGateway("");
    }
}
