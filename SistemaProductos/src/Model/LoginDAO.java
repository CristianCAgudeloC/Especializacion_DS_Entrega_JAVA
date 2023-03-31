/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    
    
    public static boolean authentication(String Email, String Password){
        Connection cn = Conexion.Connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT email,password from users where email=? and password= ?";
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, Email);
            ps.setString(2, Password);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cn.close();
                return true;
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
        
    }
    
    
}
