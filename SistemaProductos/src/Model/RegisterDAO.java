/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author camil
 */
public class RegisterDAO {
    
    public static boolean RegisterUser(User user){
        Connection cn = Conexion.Connect();
        PreparedStatement ps=null;
        
        String sql = "insert into users(name, email, password)values (?,?,?)";
        
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            cn.close();
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
    
}
