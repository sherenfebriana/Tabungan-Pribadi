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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS
 */
public class Profil {
    @FXML 
    private Label  namalengkaplbl, emaillbl, tgllbl, profillbl, tambahdatalbl, kategorilbl, pengaturanlbl, tentanglbl, keluarlbl, jkllbl;
    
    @FXML
    private Button hapusbtn, edit_user;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
       public void hapusbutton(ActionEvent ae){
         conn = Konek.getConnect();
         System.out.println("sampe hapus");
        
        
        try{
            st = conn.createStatement();
            st.executeUpdate("delete from user where nama_user='"+this.namalengkaplbl.getText()+"'");
            JOptionPane.showMessageDialog(null, "berhasil hapus");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
                 Parent signin = (Parent) loader.load();
                 Scene masuk = new Scene(signin);
                 Stage app_stage  = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
        }catch(Exception e){
            
        }finally{
            try{
                conn.close();
                rs.close();
                st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
       
    public void edituser(){
        
    }
       
    public void setnamalengkap(String nama){
        this.namalengkaplbl.setText(nama);
    }

    public void setemail(String nama){
        this.emaillbl.setText(nama);
    }
    
    public void settanggal(String nama){
        this.tgllbl.setText(nama);
    }

    public void setjeniskel(String nama){
        this.jkllbl.setText(nama);
    }
    
    public void tambahdata(){
        
    }
    
    public void kategori(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kategori.fxml"));
            Parent signin = (Parent) loader.load();
            Kategori hm = loader.getController();
            hm.setnama(this.namalengkaplbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.kategorilbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void keluar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent signin = (Parent) loader.load();
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.keluarlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
}
