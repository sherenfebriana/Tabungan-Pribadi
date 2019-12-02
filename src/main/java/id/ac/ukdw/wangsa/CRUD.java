/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.wangsa;

import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author WINDOWS
 */
public class CRUD {

    Connection conn;

    public Tabel pilih(String nama_note) {
        conn = Konek.getConnect();
        String selectStmt = "select * from data_keuangan";
        try {
            ResultSet rs = conn.createStatement().executeQuery(selectStmt);
            Tabel uang = getUangFromResultSet(rs);
            return uang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    public void hapusData(String nama_makan_minum)throws SQLException, ClassNotFoundException{
//        conn = Konek.getConnect();
//        String updateStmt = "delete from data_makan_minum where nama_makan_minum = '"+nama_makan_minum+"'";
//        try {
//            conn.createStatement().executeUpdate(updateStmt);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void hapus(String id_keuangan) throws SQLException, ClassNotFoundException {
        //  conn = Konek.getConnect();
        String sql = "delete from data_keuangan where id_keuangan='" + id_keuangan + "'";
        try {
            Konek.getConnect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void hapusUtang(String id_hutang) throws SQLException, ClassNotFoundException {
        //  conn = Konek.getConnect();
        String sql = "delete from hutang where id_hutang='" + id_hutang + "'";
        try {
            Konek.getConnect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        }
    }
    
      public static void hapusTujuan(String id_tujuan) throws SQLException, ClassNotFoundException {
        //  conn = Konek.getConnect();
        String sql = "delete from tujuan where id_tujuan='" + id_tujuan + "'";
        try {
            Konek.getConnect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void update(String id_keuangan, Tabel uang) throws SQLException, ClassNotFoundException {
        String sql = "update data_keuangan set nama_note = '" + uang.getNama_notes() + "'," + "kategori = '" + uang.getKategori().toString() + "'," + "tanggal = '" + uang.getTanggal().toString() + "'," + "jumlah_uang = '" + uang.getJumlah() + "'where id_keuangan='" + uang.getId_tabel() + "'";
        System.out.println(sql);
        try {
            Konek.getConnect().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void tambahUang(Tabel uang) throws SQLException, ClassNotFoundException {
        String sql = "insert into data_keuangan(nama_note, kategori, tanggal, jumlah_uang) values ('" + uang.getNama_notes() + "', " + "'" + uang.getKategori().toString() + "', " + "'" + uang.getTanggal().toString() + "', " + "'" + uang.getJumlah() + "')";
        System.out.println(sql);
        try {
            Konek.getConnect().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    private Tabel getUangFromResultSet(ResultSet rs) throws SQLException {
        Tabel uang = null;
        if (rs.next()) {
            uang = new Tabel();
            uang.setNama_notes("nama_note");
            uang.setKategori("kategori");
            uang.setJumlah("jumlah_uang");
            uang.setTanggal("tanggal");

        }
        return uang;
    }
}
