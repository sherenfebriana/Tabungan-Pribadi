/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS
 */
public class TambahKat {
     @FXML
    private Label namalbl,tambahdatalbl,kategorilbl,profillbl,keluarlbl;
    
    
    @FXML
    private TextField namakat;
    
    @FXML
    private ComboBox pilihcb;
    
    @FXML
    private Button tambahbt;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
    public void tambahkat(ActionEvent ae){
          String kat=namakat.getText();
        String jenis=pilihcb.getValue().toString();
        conn=Konek.getConnect();
//        System.out.println("disini");
        try{
            st=conn.createStatement();
            st.executeUpdate("insert into kategori(id_user,nama_kat,jenis_kat) values((select user_id from user where nama_user='"+this.namalbl.getText()+"'),'"+kat+"','"+jenis+"')");
            JOptionPane.showMessageDialog(null, "BERHASIL INPUT");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                 Parent signin = (Parent) loader.load();
                 Home hm=loader.getController();
                 hm.setnama(this.namalbl.getText());
                 Scene masuk = new Scene(signin);
                 Stage app_stage  = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
                 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
                st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void profil() {
        
    }
    
    public void tambahdata(){
     
    }
    
    public void kategori() {
       
    }
    
    public void keluar() {
        
    }
    
   
}
