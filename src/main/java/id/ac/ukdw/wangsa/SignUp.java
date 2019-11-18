/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import id.ac.ukdw.Koneksi.Konek;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author WINDOWS
 */
public class SignUp implements Initializable{
    @FXML
    private RadioButton pilih_lakilaki, pilih_perempuan;
    
    @FXML
    private Label loginlbl;
    
    @FXML
    private TextField namatxt, emailtxt;
    
    @FXML
    private PasswordField passtxt;
    
    @FXML
    private DatePicker tgl_lahir;
    
    @FXML
    private Button daftarbtn;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
      @Override
    public void initialize(URL location, ResourceBundle resources) {
 
    }
    
    public void selectLaki(){
        pilih_perempuan.setSelected(false);
        pilih_lakilaki.setSelected(true);
    }
    
    public void selectPerempuan(){
        pilih_lakilaki.setSelected(false);
    }
    
    public void regisbtn(ActionEvent ae){
        String nama = namatxt.getText();
        String email = emailtxt.getText();
        String password = passtxt.getText();
        String date = tgl_lahir.getValue().toString();
        String gender;
        if(pilih_perempuan.isSelected()){
            gender = "Perempuan";
        }else{
            gender = "Laki-laki";
        }
        
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            String sql = "insert into user(nama_user, password, tgl_lahir, jenis_kelamin, email) values('"+nama+"', '"+password+"', '"+date+"', '"+gender+"', '"+email+"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Berhasil Input");
            Parent signin = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                Scene masuk = new Scene(signin);
                Stage app_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                app_stage.close();
                app_stage.setScene(masuk);
                app_stage.show();
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Input!");
        }
  
   
    
    }
    
     public void login(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent signin = (Parent) loader.load();
            SignIn hm=loader.getController();
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.loginlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
     }
}
  
    
    


