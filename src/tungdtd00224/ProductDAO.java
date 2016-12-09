/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungdtd00224;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tungdt12
 */
public class ProductDAO {
    
    
    private PreparedStatement buildSQL(String sql) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433; databaseName = test_120616";
        String user = "sa";
        String pass = "123456aA@";
        Connection con = DriverManager.getConnection(url, user, pass);
        return con.prepareStatement(sql);
    }
    
    public void addProduct(Product newProduct) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = buildSQL("insert into Product(name,category,unit) values (?,?,?)");
        stm.setString(1, newProduct.name);
        stm.setString(2, newProduct.category);
        stm.setString(3, newProduct.unit);
        stm.executeUpdate();
        stm.getConnection().close();
        
        
    }
    
    public void modifyProduct(Product modifiedProduct) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = buildSQL("update Product set name = ?, category = ?, unit = ? where id = ?");
        stm.setString(1, modifiedProduct.name);
        stm.setString(2, modifiedProduct.category);
        stm.setString(3, modifiedProduct.unit);
        stm.setInt(4, modifiedProduct.id);
        stm.executeUpdate();
        stm.getConnection().close();
                
    }
    
    public void deleteProduct(int id) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = buildSQL("delete from Product where id = " + id);
        

        stm.executeUpdate();
        stm.getConnection().close();
    }
    
    public Product searchProductById(int id) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = buildSQL("select * from Product where id = " + id);
        ResultSet rs = stm.executeQuery();
        Product queriedProduct = new Product();
        while(rs.next()){
            queriedProduct.id = rs.getInt("id");
            queriedProduct.name = rs.getString("name");
            queriedProduct.category = rs.getString("category");
            queriedProduct.unit = rs.getString("unit");
        }
        stm.getConnection().close();
        return queriedProduct;
    }
    
}
