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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS
 */
public class TambahData {
    @FXML
    private Label profillbl, namalbl, keluarlbl, tambahdatalbl, kategorilbl, fiturlbl, tambahlbl;
    
    @FXML
    private ComboBox pilihcb, pilihnamacb, pilihnotecb;
    
    @FXML
    private TextField uang;
    
    @FXML
    private Button tambahdatabtn;
    
    @FXML
    private DatePicker tglinput;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
     public void pilihKat(){
        pilihnamacb.getItems().clear();
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("select distinct nama_kat from tambah_note where jenis_kat='"+this.pilihcb.getValue().toString()+"'");
            while(rs.next()){
                pilihnamacb.getItems().add(rs.getString("nama_kat"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
      public void pilihNama(){
       pilihnotecb.getItems().clear();
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("select distinct nama_note from tambah_note where nama_kat='"+this.pilihnamacb.getValue().toString()+"'");
            while(rs.next()){
                pilihnotecb.getItems().add(rs.getString("nama_note"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
       public void tambahdatabtn(ActionEvent ae){
        String money = uang.getText();
        String kat = pilihnamacb.getValue().toString();
        String namanotes = pilihnotecb.getValue().toString();
        String tanggal = tglinput.getValue().toString();
        String jenis_kat = pilihcb.getValue().toString();
        conn = Konek.getConnect();
        try {
            st = conn.createStatement();
           // System.out.println("insert into data_makan_minum (id_user, nama_makan_minum, kategori, tanggal, id_kalori,porsi) values ((select user_id from user where nama = '"+this.namalbl.getText()+"'), '"+namamakan+"', '"+kat+"', '"+tanggal+"',(select id_kalori from kalori where nama_makanan='"+this.pilihmakancb.getValue().toString()+"'),'"+por+"' )");
            st.executeUpdate("insert into data_keuangan (id_user, nama_note, kategori, jenis_kat, tanggal, jumlah_uang) values ((select user_id from user where nama_user = '"+this.namalbl.getText()+"'), '"+namanotes+"', '"+kat+"', '"+jenis_kat+"','"+tanggal+"', '"+money+"' )");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("berhasil uhuy!!!!");
            alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                Parent signin = (Parent) loader.load();
                Home hm = loader.getController();
                hm.setnama(this.namalbl.getText());
                Scene masuk =new Scene(signin);
                Stage app_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                app_stage.close();
                app_stage.setScene(masuk);
                app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
       
         public void setnama(String nama){
        this.namalbl.setText(nama);
    }
      
          public void home(){
//        try{
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
//            Parent signin = (Parent) loader.load();
//            Home hm=loader.getController();
//            hm.setnama(this.namalbl.getText());
//            Scene masuk = new Scene(signin);
//            Stage app_stage  = (Stage) this.logo_makankuy.getScene().getWindow();
//            app_stage.close();
//            app_stage.setScene(masuk);
//            app_stage.show();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
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
              
    public void kategori(){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kategori.fxml"));
            Parent signin = (Parent) loader.load();
            Kategori hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.kategorilbl.getScene().getWindow();
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
     
     
}
