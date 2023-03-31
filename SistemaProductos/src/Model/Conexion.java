/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author camil
 */
public class Conexion {
    public Connection con = null;
    
    public static Connection Connect(){
        Connection cn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Especializacion_DS_Entrega_JAVA?Timezone=UTF","root","");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return cn;
    }
    
    
    public Connection getConnection(){
        Connection con;
        try {
            String myDB = "jdbc:mysql://localhost:3306/Especializacion_DS_Entrega_JAVA?serverTimezone=UTC";
            con = DriverManager.getConnection(myDB, "root", "");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
}
