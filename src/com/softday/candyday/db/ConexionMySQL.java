
package com.softday.candyday.db;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author pasit
 */
public class ConexionMySQL {
    Connection conn;
    
    public Connection open(){
       /* String user = "4hnev730rwvptma0530g";
        String password = "pscale_pw_8cAqQuZ7qlNxQmiQqAlftxy2T0n4VdWpKGWAIzIjZLx";
        String url = "jdbc:mysql://us-east.connect.psdb.cloud/aplication1?sslMode=VERIFY_IDENTITY";
        */

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://us-east.connect.psdb.cloud/aplication1?sslMode=VERIFY_IDENTITY"
                    ,"4hnev730rwvptma0530g"
                    ,"pscale_pw_8cAqQuZ7qlNxQmiQqAlftxy2T0n4VdWpKGWAIzIjZLx");
            return conn;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void close(){
        if (conn != null) {
            try{
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Exception Controlada");
            }
        }
    }
}
