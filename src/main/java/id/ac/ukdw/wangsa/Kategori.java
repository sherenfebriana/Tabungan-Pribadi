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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS
 */
public class Kategori {

    @FXML
    private Label tambahkatlbl, editkatlbl, hapuskatlbl, profillbl, tambahlbl, keluarlbl, katlbl, namalbl, fiturlbl;
    
    @FXML
    private ImageView wangsa_img;
    
    Connection conn;
    Statement st;
    ResultSet rs;

    public void tambahkat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tambahkategori.fxml"));
            Parent signin = (Parent) loader.load();
            TambahKat hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.tambahkatlbl.getScene().getWindow();
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
    
    public void home(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent signin = (Parent) loader.load();
            Home hm=loader.getController();
            hm.setnama(this.namalbl.getText());
//            hm.setKal(this.totalkalori.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.wangsa_img.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
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
    
    public void editkat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editkategori.fxml"));
            Parent signin = (Parent) loader.load();
            EditKategori hm = loader.getController();
             hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.editkatlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hapuskat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hapuskategori.fxml"));
            Parent signin = (Parent) loader.load();
            HapusKategori hm = loader.getController();
              hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.hapuskatlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
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

    public void mouseinKeluar() {
        this.keluarlbl.setCursor(Cursor.HAND);
    }

    public void setnama(String nama) {
        this.namalbl.setText(nama);
    }

}
