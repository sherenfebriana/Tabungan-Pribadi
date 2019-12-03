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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class Home implements Initializable {

    @FXML
    private Label namalbl, profillbl, tambahlbl, katlbl, pengaturanlbl, tentanglbl, keluarlbl, totalsaldo, fiturlbl;

    @FXML
    private TextField carifield;

    @FXML
    private Button caribtn, tambahbtn, simpanbt;

    @FXML
    private TableColumn<Tabel, String> nomortbl, namatbl, kategoritbl, tanggaltbl, jumlahtbl, jenistbl;

    @FXML
    private TableView<Tabel> datatbl;

    private Alert edit_data;

    String nama_keuangan;

    Connection conn;
    Statement st;
    ResultSet rs;

    public void initialize(URL location, ResourceBundle resources) {
        nomortbl.setCellValueFactory(new PropertyValueFactory("no"));
        namatbl.setCellValueFactory(new PropertyValueFactory("nama_notes"));
        kategoritbl.setCellValueFactory(new PropertyValueFactory("kategori"));
        jenistbl.setCellValueFactory(new PropertyValueFactory("jenis_kat"));
        tanggaltbl.setCellValueFactory(new PropertyValueFactory("tanggal"));
        jumlahtbl.setCellValueFactory(new PropertyValueFactory("jumlah"));

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
                    final Tabel uang = datatbl.getSelectionModel().getSelectedItem();
                    int index = datatbl.getSelectionModel().getSelectedIndex();
                    CRUD.hapus(uang.getId_tabel());
                    datatbl.getItems().remove(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cxMenuItemEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Tabel tbl = datatbl.getSelectionModel().getSelectedItem();
                openFormUang(false, tbl);
            }
        });

    }
    /**
     *
     * @param aksi
     */
    private String nama;
    private String jumlah;

    public void getNama(String nama) {
        this.nama = nama;
        namalbl.setText("select nama_user from user where email = '" + nama + "'");
    }

    public void getJumlah() {
        conn = Konek.getConnect();
        int tamp = 0;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * from data_keuangan where id_user=(select user_id from user where nama_user = '" + this.namalbl.getText() + "')");
            while (rs.next()) {
                if (rs.getString("jenis_kat").equals("PEMASUKKAN")) {
                    tamp = tamp + Integer.parseInt(rs.getString("jumlah_uang"));
                }
                if (rs.getString("jenis_kat").equals("PENGELUARAN")) {
                    tamp = tamp - Integer.parseInt(rs.getString("jumlah_uang"));
                }
            }
            totalsaldo.setText("Rp. " + String.valueOf(tamp));
            rs.close();
        } catch (Exception e) {
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

    public void isitabel() {
        conn = Konek.getConnect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from data_keuangan where id_user=(select user_id from user where nama_user = '" + this.namalbl.getText() + "')");
            int i = 1;
            System.out.println(rs.getString("nama_note"));
            ObservableList<Tabel> isi = FXCollections.observableArrayList();
            while (rs.next()) {
                isi.add(new Tabel(
                        rs.getString("id_keuangan"),
                        String.valueOf(i++),
                        rs.getString("nama_note"),
                        rs.getString("kategori"),
                        rs.getString("jenis_kat"),
                        rs.getString("tanggal"),
                        rs.getString("jumlah_uang")
                ));

            }

            rs.close();
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

//    public void masuk(ActionEvent aksi){
//        conn = Konek.getConnect();
//        String email = namalbl.getText();
//        try {
//            st = conn.createStatement();
//            rs = st.executeQuery("SELECT nama_user from user where email = '"+email+"'");
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            try{
//                rs.close();
//                st.close();
//                conn.close();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
    public void setnama(String nama) {
        this.namalbl.setText(nama);
        isitabel();
        getJumlah();
    }

    private void openFormUang(boolean update, final Tabel uang) {
        try {
            int index = 0;
            if (!update) {
                index = datatbl.getSelectionModel().getSelectedIndex();
            }
            if (edit_data == null) {
                edit_data = new Alert(INFORMATION);
                edit_data.setTitle("Update datamu");
                edit_data.setHeaderText("Form Edit Data");
                edit_data.initModality(Modality.WINDOW_MODAL);
                ButtonType simpanbtn = new ButtonType("simpan");
                edit_data.getButtonTypes().setAll(simpanbtn);

                FormUang keuangan = null;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit_data.fxml"));
                try {
                    Node konten = loader.load();
                    DialogPane pane = edit_data.getDialogPane();
                    pane.setContent(konten);
                    keuangan = loader.getController();
                    keuangan.setTabel(uang);
                    edit_data.showAndWait();
                } catch (IOException e) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
                }
                Optional<ButtonType> rs = edit_data.showAndWait();
                if (rs.get() == simpanbtn) {
                    if (keuangan != null) {
                        if (update) {
                            Tabel uangUpdate = keuangan.getTabel();
                            try {
                                CRUD.tambahUang(uangUpdate);
                                datatbl.getItems().add(uangUpdate);
                            } catch (SQLException | ClassNotFoundException e) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } else {
                            String uangOld = uang.getNama_notes();
                            Tabel uangUpdate = keuangan.getTabel();
                            try {
                                ObservableList<Tabel> listTabel = datatbl.getItems();
                                CRUD.update(uangOld, uangUpdate);
                                listTabel.set(index, uangUpdate);
                            } catch (Exception e) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    }
                    edit_data = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profil() {
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

    public void kategori() {
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

    public void tambahdata() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/input_data.fxml"));
            Parent signin = (Parent) loader.load();
            InputData hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.tambahlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tambahdatabtn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tambah_data.fxml"));
            Parent signin = (Parent) loader.load();
            TambahData hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.tambahbtn.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fitur() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fitur.fxml"));
            Parent signin = (Parent) loader.load();
            Fitur hm = loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.fiturlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tentang() {

    }

    public void cari() {
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            //  System.out.println("select * from data_makan_minum inner join kalori on data_makan_minum.id_kalori=kalori.id_kalori where id_user=(select user_id from user where nama='"+this.namalbl.getText()+"') and nama_makan_minum like '%"+this.fieldcari.getText()+"%'");
            rs = st.executeQuery("select * from data_keuangan where id_user=(select user_id from user where nama_user='" + this.namalbl.getText() + "') and nama_note like '%" + this.carifield.getText() + "%'");
            int i = 1;
//            System.out.println(rs.getString("nama_makan_minum"));
            ObservableList<Tabel> isi = FXCollections.observableArrayList(); //nampung data dari tabel

            while (rs.next()) {
                isi.add(new Tabel(
                        rs.getString("id_keuangan"),
                        String.valueOf(i++),
                        rs.getString("nama_note"),
                        rs.getString("kategori"),
                        rs.getString("jenis_kat"),
                        rs.getString("tanggal"),
                        rs.getString("jumlah_uang")
                ));
            }
            this.datatbl.setItems(isi);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
