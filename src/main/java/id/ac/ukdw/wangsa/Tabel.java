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
public class Tabel {

    private String id_tabel;
    private String no;
    private String nama_notes;
    private String kategori;
    private String tanggal;
    private String jumlah;

    public Tabel(String id_tabel, String no, String nama_notes, String kategori, String tanggal, String jumlah) {
        this.id_tabel = id_tabel;
        this.no = no;
        this.nama_notes = nama_notes;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.jumlah = jumlah;

    }
    Tabel() {
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
     * @return the nama_notes
     */
    public String getNama_notes() {
        return nama_notes;
    }

    /**
     * @param nama_notes the nama_notes to set
     */
    public void setNama_notes(String nama_notes) {
        this.nama_notes = nama_notes;
    }

    /**
     * @return the kategori
     */
    public String getKategori() {
        return kategori;
    }

    /**
     * @param kategori the kategori to set
     */
    public void setKategori(String kategori) {
        this.kategori = kategori;
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

}
