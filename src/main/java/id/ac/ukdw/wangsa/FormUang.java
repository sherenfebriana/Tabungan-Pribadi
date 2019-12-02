/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author WINDOWS
 */
public class FormUang implements Initializable{
    private Tabel uang;
    
    @FXML
    private TextField namabaru, jumlahbaru;
    
    @FXML
    private ComboBox katbaru;
    
    @FXML
    private DatePicker tanggalbaru;
    
     public void setTabel(Tabel uangOld) throws ParseException{
        this.uang = uangOld;
        namabaru.setText(uang.getNama_notes());
        katbaru.setValue(uang.getKategori().toString());
        jumlahbaru.setText(uang.getJumlah());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date tanggalbaru = format.parse(uangOld.getTanggal());
        this.tanggalbaru.setValue(tanggalbaru.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
     
     public Tabel getTabel(){
        this.uang.setNama_notes(namabaru.getText());
        this.uang.setKategori(katbaru.getValue().toString());
        this.uang.setJumlah(jumlahbaru.getText());
        this.uang.setTanggal(tanggalbaru.getValue().toString());
        return this.uang;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTabel(TabelHutang uang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTabelHutang(TabelHutang uang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
