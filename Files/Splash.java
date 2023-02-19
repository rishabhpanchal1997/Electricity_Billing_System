
package electricity.billing.system;

//this class is used to popup a window which will be opened when app is

//Swing is present inside extended version of java written as javax
import javax.swing.*;

//we can use awt functionality also in the same project which is using swing
import java.awt.*;

//This class will open a first window before the login window gets open
//We need some halt before we give view of login window to user
//for that, we use multithreadig --> for that we use runnable interface
//we need to implement method of runnable interface
public class Splash extends JFrame implements Runnable {
    
    Thread t; //we need to create thread object to call run method from runnable
    Splash(){
        
       //To load image in usable variable
       ImageIcon lamp = new ImageIcon(ClassLoader.getSystemResource("icon/lamp.jpg"));
       
       //we can set dimensions of image imported in ImageIcon variable
       //we are setting dimension to fit the image in out frame
       Image lampimage = lamp.getImage().getScaledInstance(600, 800,Image.SCALE_DEFAULT);
       
       //to add in jlabel --> converting image into imageicon
       ImageIcon lampicon = new ImageIcon(lampimage);
        
       //to add image on the frame --> we can't directly add image on frame
       //We can't pass object of image but can pass object of imageicon
       JLabel lamplabel = new JLabel(lampicon);
        
       //adding jlabel in the frame
       add(lamplabel);
       
       
       //frame is invisible by default hence needed
       //if this setvisible is put after for loop, we will not be able to
       //see dymaic change since changes will be made first then it will be visible
       setVisible(true);
       
       //to make popup window dynamic rather then staic 
       //we create for loop to make size and location variable
       //it will give us dynamic popup
       for(int i=-40,j=0; i<600&&j<800; i++,j++){
           //a frame of size specified length and width resepectively
       setSize(i,j);
       
       //default location is origin(top-left of screen) 
       //to change location of frame
       setLocation(i-150,j-500);  
       }
       
       t = new Thread(this); //object of thread created
       t.start(); //it internally calls run method
    }
    
    //it will make frame invisible after defined time
    public void run(){
        try{
            Thread.sleep(5000);
            setVisible(false);
            
            new Login();
            
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        
        //anonymous object
        new Splash();
    }
    
}
