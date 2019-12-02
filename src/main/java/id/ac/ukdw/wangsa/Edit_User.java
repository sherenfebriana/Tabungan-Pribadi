/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS
 */
public class Edit_User {
     @FXML
    private TextField namabaru,emailbaru;
    
    @FXML
    private Label namalbl,tambahlbl,keluarlbl,profillbl,katlbl,fiturlbl;

    
    @FXML
    private PasswordField passbaru; 
    
    Connection conn;
    Statement st;
    ResultSet rs;
    @FXML
    private Button simpanbtn;
    private String nama;
    
    public void getNama(){
        this.nama = nama;
        namalbl.setText("SELECT nama_user from user WHERE email = '"+nama+"'");
        
    }
    
    public void simpanbutton(ActionEvent a) {
               conn = Konek.getConnect();
       //   int ok = JOptionPane.showConfirmDialog(null, "Apakah yakin?", "Confirm", JOptionPane.YES_NO_OPTION);
          System.out.println("tes");
        try{
            String sql = "UPDATE user set nama_user=?,email=?,password=? where nama_user =? ";
            System.out.println(sql);
            PreparedStatement st = conn.prepareStatement(sql);
           // if(ok==1){
               
                   st.setString(1, this.namabaru.getText());
                   st.setString(2, this.emailbaru.getText());
                   st.setString(3, this.passbaru.getText());
                   st.setString(4, this.namalbl.getText());
                   st.executeUpdate();
                   
                   JOptionPane.showMessageDialog(null, "Update data sukses");
                   System.out.println("ssss");
            //}
        }catch(Exception e){
            
                   JOptionPane.showMessageDialog(null, "update gagal");
               
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
    
    public void profil(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/profil.fxml"));
            Parent signin = (Parent) loader.load();
            Profil hm = loader.getController();

            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("Select * from user where nama_user='" + this.namalbl.getText() + "'");
            System.out.println("sampe sini");

           // hm.setnamalengkap(this.namalbl.getText());
            hm.setemail(rs.getString("email"));
            hm.setjeniskel(rs.getString("Jenis_kelamin"));
            hm.setnamalengkap(this.namalbl.getText());
            hm.settanggal(rs.getString("tgl_lahir"));
            hm.setnama(rs.getString("nama_user"));
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.profillbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
    public void setnama(String nama){
        this.namalbl.setText(nama);
    }
}

