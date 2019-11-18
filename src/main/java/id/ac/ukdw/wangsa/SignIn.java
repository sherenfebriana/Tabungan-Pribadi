/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import id.ac.ukdw.Koneksi.Konek;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS
 */
public class SignIn {
    @FXML
    private Label register_label;
    
    @FXML
    private Button loginbtn;
    
    @FXML
    private TextField emailtxt, passtxt;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
    public void login(ActionEvent ae) throws IOException, ClassNotFoundException, SQLException{
       conn = Konek.getConnect();
       String email = emailtxt.getText();
       String password = passtxt.getText();
       
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from user where email = '"+email+"'");
            
            if(rs.next()){
                if(password.equals(rs.getString("password"))){
                    JOptionPane.showMessageDialog(null, "Selamat Datang, "+rs.getString("nama_user"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                    Parent signin = (Parent) loader.load();
                    Home hm = loader.getController();
                    hm.setnama(rs.getString("nama_user"));
                    Scene masuk = new Scene(signin);
                    Stage app_stage = (Stage)((Node) ae.getSource()).getScene().getWindow();
                    app_stage.close();
                    app_stage.setScene(masuk);
                    app_stage.show();
                }else{
                    JOptionPane.showMessageDialog(null, "Email atau Password Salah!!!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Email atau Password Salah!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
            rs.close();
            st.close();
            conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void register(){
        Stage stage= (Stage) register_label.getScene().getWindow();
        stage.close();
        
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/regis.fxml"));
            Parent root = (Parent) loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
