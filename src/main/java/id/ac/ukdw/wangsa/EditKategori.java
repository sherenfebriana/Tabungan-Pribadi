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
public class EditKategori {

    @FXML
    private Label namalbl, profillbl, kategorilbl, tambahdatalbl, keluarlbl;

    @FXML
    private ComboBox pilihjeniscb;
    @FXML
    private ComboBox pilihnamacb;

    @FXML
    private TextField katbaru;

    @FXML
    private Button simpanbt;

    Connection conn;
    Statement st;
    ResultSet rs;

    public void ganti() {
        pilihnamacb.getItems().clear();
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("select nama_kat from kategori where jenis_kat='" + this.pilihjeniscb.getValue().toString() + "'");
            while (rs.next()) {
                pilihnamacb.getItems().add(rs.getString("nama_kat"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                rs.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void simpanbutton(ActionEvent ae) {
        conn = Konek.getConnect();

        try {
            st = conn.createStatement();
            st.executeUpdate("update kategori set nama_kat='" + this.katbaru.getText() + "' where jenis_kat='" + this.pilihjeniscb.getValue().toString() + "' and nama_kat='" + this.pilihnamacb.getValue().toString() + "'");
            JOptionPane.showMessageDialog(null, "berhasil update");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent signin = (Parent) loader.load();
            Home hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                rs.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
      public void profil(){
        
    }
    
    public void tambahdata(){
        
    }
    
     public void keluar(){
        
    }
     
         public void kategori(){
        
    }
}
