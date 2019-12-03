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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS
 */
public class TujuanData implements Initializable{
     @FXML
    private Label namalbl, profillbl, tambahlbl, katlbl, pengaturanlbl, tentanglbl, keluarlbl, fiturlbl, lihatlbl;

    @FXML
    private TextField carifield;

    @FXML
    private Button caribtn, kembalibt;

    @FXML
    private TableColumn<TabelTujuan, String> nomortbl, namatbl, jumlahtbl, tanggaltbl;

    @FXML
    private TableView<TabelTujuan> datatbl;
    
    String nama_keuangan;

    Connection conn;
    Statement st;
    ResultSet rs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomortbl.setCellValueFactory(new PropertyValueFactory("no"));
        namatbl.setCellValueFactory(new PropertyValueFactory("nama"));
        jumlahtbl.setCellValueFactory(new PropertyValueFactory("jumlah"));
        tanggaltbl.setCellValueFactory(new PropertyValueFactory("tanggal"));
        
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
                    final TabelTujuan uang = datatbl.getSelectionModel().getSelectedItem();
                    int index = datatbl.getSelectionModel().getSelectedIndex();
                    CRUD.hapusTujuan(uang.getId_tabel());
                    datatbl.getItems().remove(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        cxMenuItemEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final TabelTujuan tbl = datatbl.getSelectionModel().getSelectedItem();
               // openFormUtang(false, tbl);
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
            rs = st.executeQuery("select * from tujuan where id_user=(select user_id from user where nama_user = '" + this.namalbl.getText() + "')");
            int i = 1;
           // System.out.println(rs.getString("nama"));
            ObservableList<TabelTujuan> isi = FXCollections.observableArrayList();
            while (rs.next()) {
                isi.add(new TabelTujuan(
                        rs.getString("id_tujuan"),
                        String.valueOf(i++),
                        rs.getString("nama_tujuan"),
                        rs.getString("jumlah_uang"),
                        rs.getString("tanggal")
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

    public void profil() {

    }

    public void tambahdata() {

    }

    public void fitur() {

    }

    public void tentang() {

    }

    public void kategori() {

    }

    public void cari() {
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            //  System.out.println("select * from data_makan_minum inner join kalori on data_makan_minum.id_kalori=kalori.id_kalori where id_user=(select user_id from user where nama='"+this.namalbl.getText()+"') and nama_makan_minum like '%"+this.fieldcari.getText()+"%'");
            rs = st.executeQuery("select * from tujuan where id_user=(select user_id from user where nama_user='" + this.namalbl.getText() + "') and nama_tujuan like '%" + this.carifield.getText() + "%'");
            int i = 1;
//            System.out.println(rs.getString("nama_makan_minum"));
            ObservableList<TabelTujuan> isi = FXCollections.observableArrayList(); //nampung data dari tabel

            while (rs.next()) {
                isi.add(new TabelTujuan(
                        rs.getString("id_tujuan"),
                        String.valueOf(i++),
                        rs.getString("nama_tujuan"),
                        rs.getString("jumlah_uang"),
                        rs.getString("tanggal")
                ));
            }
            this.datatbl.setItems(isi);
        } catch (Exception e) {
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

}
