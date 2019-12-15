
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer Aspire E 15
 */
public class refreshtable {
    connections con = new connections();
    
     public void showProducts(int bcode,JTable tab){
     try {
         Class.forName(new connections().driver);
         Connection conn = DriverManager.getConnection(new connections().local,new connections().ps,new connections().sr);
         PreparedStatement ps = conn.prepareStatement("select * from products where id = ?");
         ps.setInt(1, bcode);
         
         ResultSet rs = ps.executeQuery();
         DefaultTableModel jtab = (DefaultTableModel) tab.getModel(); 
         if(rs.next()){
             jtab.addRow(new Object[]{rs.getString("id"),rs.getString("pname"),1,rs.getString("pprice"),rs.getString("pprice")});
         }
     }  catch (ClassNotFoundException ex) {
            Logger.getLogger(refreshtable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(refreshtable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
