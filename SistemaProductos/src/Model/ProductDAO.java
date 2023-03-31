/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.List;




/**
 *
 * @author camil
 */
public class ProductDAO {
    
    public static ResultSet ListProducts(){ 
        Connection cn = Conexion.Connect();
        Statement sql;
        ResultSet data = null;
        
        try {
            sql = cn.createStatement();
            data = sql.executeQuery("SELECT * FROM product");
            
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
    
    public static boolean RegisterProduct(Product product){
        Connection cn = Conexion.Connect();
        PreparedStatement ps=null;        
        
        String sql = "insert into product(name, description, price, type)values (?,?,?,?)";
        
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getType());
            ps.execute();
            cn.close();
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
}
