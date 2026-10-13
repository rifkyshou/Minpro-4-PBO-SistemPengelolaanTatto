package dao;

import database.DBConnection;
import model.Booking;
import model.Pelanggan;
import model.TattooArtist;
import java.sql.*;
import java.util.ArrayList;

public class BookingDAO {
    private static final String SELECT_ALL = "SELECT b.id_booking, b.nama_pelanggan, b.hp_pelanggan, b.alamat_pelanggan, " +
        "a.nama AS nama_artist, a.no_hp AS hp_artist, a.spesialisasi, b.nama_desain, b.harga, b.tanggal, b.status " +
        "FROM booking b JOIN tattoo_artist a ON b.id_artist = a.id_artist ORDER BY b.id_booking";

    private Booking map(ResultSet rs) throws SQLException {
        Pelanggan p = new Pelanggan(rs.getString("nama_pelanggan"), rs.getString("hp_pelanggan"), rs.getString("alamat_pelanggan"));
        TattooArtist a = new TattooArtist(rs.getString("nama_artist"), rs.getString("hp_artist"), rs.getString("spesialisasi"));
        Booking b = new Booking(rs.getInt("id_booking"), p, a, rs.getString("nama_desain"),
            rs.getDouble("harga"), rs.getString("tanggal"));
        b.setStatus(rs.getString("status"));
        return b;
    }

    public ArrayList<Booking> getAll() {
        ArrayList<Booking> daftar = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) daftar.add(map(rs));
        } catch (SQLException e) {
            System.out.println("Gagal membaca booking: " + e.getMessage());
        }
        return daftar;
    }

    public Booking findById(int id) {
        String sql = SELECT_ALL.replace(" ORDER BY b.id_booking", " WHERE b.id_booking = ?");
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari booking: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(Pelanggan p, int idArtist, String desain, double harga, String tanggal) {
        String sql = "INSERT INTO booking (nama_pelanggan, hp_pelanggan, alamat_pelanggan, id_artist, nama_desain, harga, tanggal, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'Menunggu')";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNama()); ps.setString(2, p.getNoHp()); ps.setString(3, p.getAlamat());
            ps.setInt(4, idArtist); ps.setString(5, desain); ps.setDouble(6, harga); ps.setString(7, tanggal);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan booking: " + e.getMessage());
            return false;
        }
    }

    public boolean update(int id, String desain, double harga, String tanggal) {
        String sql = "UPDATE booking SET nama_desain=?, harga=?, tanggal=? WHERE id_booking=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, desain); ps.setDouble(2, harga); ps.setString(3, tanggal); ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui booking: " + e.getMessage()); return false;
        }
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE booking SET status=? WHERE id_booking=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status); ps.setInt(2, id); return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal mengubah status: " + e.getMessage()); return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM booking WHERE id_booking=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id); return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal menghapus booking: " + e.getMessage()); return false;
        }
    }
}
