
package electricity.billing.system;

import java.sql.*;

//============================ JDBC CONNECTIVITY =============================//

//WE WILL CREATE JDBC CONNECTION IN THIS ONE CLASS
//WE WILL USE IT BY CREATING OBJECT OF IT

// STEPS OF CONNECTIVITY :
/* STEP 1. Register the Driver Class
    - We will register out driver class using static method of 'Class' class
    - We will register mysql driver using forName method of 'Class' class
    - In latest syntax above step is not necessary

   STEP 2. Creating the connection
    - Use interface Connection to create variable c
    - Use DriverManager class to make connection using getConnection method
    - Connection String : interface(jdbc):database(mysql)://localhost:3306/nameOfDatabase

   STEP 3. Creating the statement
    - Use interface Statement to create variable s
    - use createStatement mathod

   STEP 4. Executing mysql queries
    - Create table in database
    - Create connection with database 
    - Execute the update using c, s and executeUpdate function

*/

//==============================================================================

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        
        //connection string stored in variable c of connection type
        //put c in try ot catch clause to remove error
        try{
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs","root","Rishabh@1997"); //step 2
        s = c.createStatement(); //step 3
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
