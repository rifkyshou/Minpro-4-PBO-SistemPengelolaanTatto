package dao;

import database.DBConnection;
import model.TattooArtist;
import java.sql.*;
import java.util.ArrayList;

public class ArtistDAO {
    public ArrayList<TattooArtist> getAll() {
        ArrayList<TattooArtist> daftar = new ArrayList<>();
        String sql = "SELECT nama, no_hp, spesialisasi FROM tattoo_artist ORDER BY id_artist";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) daftar.add(new TattooArtist(rs.getString("nama"), rs.getString("no_hp"), rs.getString("spesialisasi")));
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data artist: " + e.getMessage());
        }
        return daftar;
    }

    public int getIdByIndex(int index) {
        String sql = "SELECT id_artist FROM tattoo_artist ORDER BY id_artist LIMIT 1 OFFSET ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, index);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getInt(1); }
        } catch (SQLException e) { System.out.println("Gagal mengambil ID artist: " + e.getMessage()); }
        return -1;
    }

    public boolean insert(TattooArtist artist) {
        String sql = "INSERT INTO tattoo_artist (nama, no_hp, spesialisasi) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, artist.getNama());
            ps.setString(2, artist.getNoHp());
            ps.setString(3, artist.getSpesialisasi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan artist: " + e.getMessage());
            return false;
        }
    }
}
