/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

/**
 *
 * @author WINDOWS
 */
public class TabelTujuan {

    private String id_tabel;
    private String no;
    private String nama;
    private String jumlah;
    private String tanggal;

    public TabelTujuan(String id_tabel, String no, String nama, String tanggal, String jumlah) {
        this.id_tabel = id_tabel;
        this.no = no;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jumlah = jumlah;

    }

    TabelTujuan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the id_tabel
     */
    public String getId_tabel() {
        return id_tabel;
    }

    /**
     * @param id_tabel the id_tabel to set
     */
    public void setId_tabel(String id_tabel) {
        this.id_tabel = id_tabel;
    }

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the jumlah
     */
    public String getJumlah() {
        return jumlah;
    }

    /**
     * @param jumlah the jumlah to set
     */
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    /**
     * @return the tanggal
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * @param tanggal the tanggal to set
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

   
}
