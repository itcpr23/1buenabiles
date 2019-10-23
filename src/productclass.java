
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer Aspire E 15
 */
public class productclass {

connections cn = new connections(); 
productframe pr = new productframe();

    public int addpro(String prod_name, int prod_quantity, Float prod_price){
     int x = 0;
     try{
         Class.forName(cn.driver);
         Connection cnn = DriverManager.getConnection(cn.local, cn.ps, cn.sr);
         PreparedStatement pr = cnn.prepareStatement("Insert into products values (null,?,?,?)");
         pr.setString(1, prod_name);
         pr.setInt(2, prod_quantity);
         pr.setObject(3,prod_price);
         x = pr.executeUpdate();
         
         
     } catch (ClassNotFoundException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    }return x;   
    }

    
    public int checkprodname(String prodname,int prodquant,Float prodprice){
        int x = 0;
        
        try{
         Class.forName(cn.driver);
         Connection cnn = DriverManager.getConnection(cn.local, cn.ps, cn.sr);
         PreparedStatement pr = cnn.prepareStatement("Select pquantity from products where pname = ? and pprice= ?");
         pr.setString(1, prodname);
         pr.setFloat(2, prodprice);
        ResultSet res = pr.executeQuery();
        
        if(res.next()){
           
         String oqty = res.getString("pquantity"); int s = Integer.parseInt(oqty);
         PreparedStatement per = cnn.prepareStatement("update products set pquantity = ? where pname = ? and pprice = ?");
         per.setInt(1, prodquant+s);
         per.setString(2, prodname);
         per.setFloat(3, prodprice);
         x = per.executeUpdate();
        }else{
            x=0;
        }
         
     } catch (ClassNotFoundException ex) { 
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    } return x;
    }
 public void deleteproduct(int id){

    try{
        Class.forName(cn.driver);
        Connection con = DriverManager.getConnection(cn.local,cn.ps,cn.sr);
        PreparedStatement prep = con.prepareStatement("delete from products where id = ?");
        prep.setInt(1, id);
        prep.executeUpdate();
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public int editProduct(int id, String pname, Float pprice){
int h=0;
    try{
        Class.forName(new connections().driver);
        Connection con = DriverManager.getConnection(new connections().local,new connections().ps,new connections().sr);
        PreparedStatement prep = con.prepareStatement("update products set pname = ?, pprice = ? where id = ?");
        prep.setString(1, pname);
        prep.setFloat(2, pprice);
        prep.setInt(3, id);
        h=prep.executeUpdate();
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    }return h;
    }
public int addQuantity(int id, int quantity){
int h=0;
    try{
        Class.forName(new connections().driver);
        Connection con = DriverManager.getConnection(new connections().local,new connections().ps,new connections().sr);
        PreparedStatement prep = con.prepareStatement("update products set pquantity = pquantity + ? where id = ? ");
        prep.setInt(1, quantity);
        prep.setInt(2, id);
        
        h=prep.executeUpdate();
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(productclass.class.getName()).log(Level.SEVERE, null, ex);
    }return h;
    }


}
