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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS
 */
public class Profil {
    @FXML 
    private Label  namalengkaplbl, emaillbl, tgllbl, profillbl, pemasukanlbl, pengeluaranlbl, tentanglbl, keluarlbl, jkllbl,namalbl,katlbl, tambahlbl,fiturlbl;
    
    @FXML
    private Button hapusbtn, edit_user;
    
    @FXML
    private ImageView wangsa_img;
    
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
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/edit_user.fxml"));
            Parent signin = (Parent) loader.load();
            Edit_User hm=loader.getController();
            System.out.println("sampe edituser");
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.edit_user.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void kategori(){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kategori.fxml"));
            Parent signin = (Parent) loader.load();
            Kategori hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.katlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void home(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent signin = (Parent) loader.load();
            Home hm=loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.wangsa_img.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void tambahdata(){
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/input_data.fxml"));
            Parent signin = (Parent) loader.load();
            InputData hm=loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.tambahlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
       
    public void setnamalengkap(String nama){
        this.namalengkaplbl.setText(nama);
    }
    
    public void fitur(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fitur.fxml"));
            Parent signin = (Parent) loader.load();
            Fitur hm=loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.fiturlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void keluar() {
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
    
    private String nama;
    
     public void getNama(String nama){
        this.nama = nama;
        namalbl.setText("select nama_user from user where email = '"+nama+"'");
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

    public void setnama(String nama) {
        this.namalbl.setText(nama);
    }
  
}

