/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import id.ac.ukdw.Koneksi.Konek;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS
 */
public class HutangData implements Initializable {

    @FXML
    private Label namalbl, profillbl, tambahlbl, katlbl, pengaturanlbl, tentanglbl, keluarlbl, fiturlbl, lihatlbl;

    @FXML
    private TextField carifield;
    
    @FXML
    private ImageView wangsa_img;

    @FXML
    private Button caribtn, kembalibt;

    @FXML
    private TableColumn<TabelHutang, String> nomortbl, namatbl, desktbl, jumlahtbl, tanggaltbl, tglbayartbl;

    @FXML
    private TableView<TabelHutang> datatbl;

    private Alert edit_data;      //bikin edit_data_hutang.fxml

    String nama_keuangan;

    Connection conn;
    Statement st;
    ResultSet rs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomortbl.setCellValueFactory(new PropertyValueFactory("no"));
        namatbl.setCellValueFactory(new PropertyValueFactory("nama"));
        desktbl.setCellValueFactory(new PropertyValueFactory("deskripsi"));
        jumlahtbl.setCellValueFactory(new PropertyValueFactory("jumlah"));
        tanggaltbl.setCellValueFactory(new PropertyValueFactory("tanggal"));
        tglbayartbl.setCellValueFactory(new PropertyValueFactory("tanggal_bayar"));

        final ContextMenu cxMenu = new ContextMenu();
        MenuItem cxMenuItemEdit = new MenuItem("Ubah Data");
        cxMenu.getItems().add(cxMenuItemEdit);
        MenuItem cxMenuItemDelete = new MenuItem("Hapus Data");
        cxMenu.getItems().add(cxMenuItemDelete);
        datatbl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cxMenu.show(datatbl, t.getScreenX(), t.getScreenY());
                }
                if (t.getButton() == MouseButton.PRIMARY) {
                    if (cxMenu.isShowing()) {
                        cxMenu.hide();
                    }
                }
            }
        });

        datatbl.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evt) {
                if (evt.getCode().equals(KeyCode.ESCAPE)) {
                    cxMenu.hide();
                }
            }
        });

        cxMenuItemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = Konek.getConnect();
                try {
                    final TabelHutang uang = datatbl.getSelectionModel().getSelectedItem();
                    int index = datatbl.getSelectionModel().getSelectedIndex();
                    CRUD.hapusUtang(uang.getId_tabel());
                    datatbl.getItems().remove(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cxMenuItemEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final TabelHutang tbl = datatbl.getSelectionModel().getSelectedItem();
                openFormUtang(false, tbl);
            }
        });
    }

    private String nama;
    private String jumlah;

    public void getNama(String nama) {
        this.nama = nama;
        namalbl.setText("select nama_user from user where email = '" + nama + "'");
    }

    public void isitabel() {
        conn = Konek.getConnect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from hutang where id_user=(select user_id from user where nama_user = '" + this.namalbl.getText() + "')");
            int i = 1;
            System.out.println(rs.getString("nama"));
            ObservableList<TabelHutang> isi = FXCollections.observableArrayList();
            while (rs.next()) {
                isi.add(new TabelHutang(
                        rs.getString("id_hutang"),
                        String.valueOf(i++),
                        rs.getString("nama"),
                        rs.getString("deskripsi"),
                        rs.getString("jumlah_uang"),
                        rs.getString("tanggal"),
                        rs.getString("tanggal_bayar")
                ));

            }
            this.datatbl.setItems(isi);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public void masuk(ActionEvent aksi) {
        conn = Konek.getConnect();
        String email = namalbl.getText();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT nama_user from user where email = '" + email + "'");

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

    public void setnama(String nama) {
        this.namalbl.setText(nama);
        System.out.println(this.namalbl.getText());
        isitabel();
    }

    private void openFormUtang(boolean update, final TabelHutang uang) {

    }

    

    public void cari() {

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
    
    public void tentang(){
        
    }


    public void mouseinKeluar() {
        this.keluarlbl.setCursor(Cursor.HAND);
    }

}
