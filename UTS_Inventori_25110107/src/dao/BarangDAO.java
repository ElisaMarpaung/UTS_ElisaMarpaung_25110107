package dao;

import model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {
    private final Connection conn;

    public BarangDAO() {
        conn = Koneksi.getConnection();
    }

    // Insert Data (Tombol Simpan)
    public boolean insert(Barang b) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getKodeBarang());
            ps.setString(2, b.getNamaBarang());
            ps.setString(3, b.getKategori());
            ps.setInt(4, b.getStok());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Update Data (Tombol Ubah)
    public boolean update(Barang b) {
        String sql = "UPDATE barang SET nama_barang=?, kategori=?, stok=? WHERE kode_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getNamaBarang());
            ps.setString(2, b.getKategori());
            ps.setInt(3, b.getStok());
            ps.setString(4, b.getKodeBarang());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Delete Data (Tombol Hapus)
    public boolean delete(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kode_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeBarang);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Tampil Data ke JTable
    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Barang b = new Barang();
                b.setKodeBarang(rs.getString("kode_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setKategori(rs.getString("kategori"));
                b.setStok(rs.getInt("stok"));
                list.add(b);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}