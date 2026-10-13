package controller;

import dao.BookingDAO;
import interfaces.Crud;
import model.Booking;
import model.Pelanggan;
import model.TattooArtist;
import util.InputValidator;
import view.BookingView;
import java.util.ArrayList;

public class BookingController implements Crud {
    private final BookingDAO bookingDAO = new BookingDAO();
    private final ArtistController artistController = new ArtistController();

    public void jalankan() {
        int pilihan;
        do {
            BookingView.tampilkanMenuUtama();
            pilihan = InputValidator.bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1: inputArtist(); break;
                case 2: inputBooking(); break;
                case 3: BookingView.tampilkanDaftarBooking(getAll()); break;
                case 4: inputUpdateBooking(); break;
                case 5: inputUbahStatus(); break;
                case 6: inputHapusBooking(); break;
                case 7: cariBooking(); break;
                case 0: BookingView.tampilkanPesan("Terima kasih, program selesai."); break;
                default: BookingView.tampilkanPesan("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }

    private void cariBooking() {
        BookingView.tampilkanHeader("CARI BOOKING BERDASARKAN ID");
        int id = InputValidator.bacaInt("Masukkan ID Booking: ");
        Booking b = cariById(id);
        if (b == null) {
            BookingView.tampilkanPesan("Booking dengan ID tersebut tidak ditemukan.");
        } else {
            BookingView.tampilkanDetailBooking(b);
        }
    }

    private void inputArtist() {
        BookingView.tampilkanHeader("INPUT ARTIST BARU");
        String nama = InputValidator.bacaString("Nama Tattoo Artist: ");
        String hp = InputValidator.bacaString("No HP Artist: ");
        String spesialisasi = InputValidator.bacaString("Spesialisasi Artist: ", "Realis / Tribal / Minimalis");
        if (artistController.tambahArtist(nama, hp, spesialisasi) != null)
            BookingView.tampilkanPesan("Artist berhasil ditambahkan.");
        else BookingView.tampilkanPesan("Artist gagal ditambahkan.");
    }

    private void inputBooking() {
        BookingView.tampilkanHeader("INPUT BOOKING BARU");
        String nama = InputValidator.bacaString("Nama Pelanggan: ");
        String hp = InputValidator.bacaString("No HP Pelanggan: ");
        String alamat = InputValidator.bacaString("Alamat Pelanggan: ");
        ArrayList<TattooArtist> artists = artistController.getAll();
        if (artists.isEmpty()) { BookingView.tampilkanPesan("Belum ada artist. Tambahkan artist terlebih dahulu."); return; }
        BookingView.tampilkanDaftarArtist(artists);
        int pilihan = InputValidator.bacaInt("Pilih nomor artist: ", 1, artists.size());
        String desain = InputValidator.bacaString("Nama Desain/Layanan: ");
        double harga = InputValidator.bacaHarga("Harga: ");
        String tanggal = InputValidator.bacaTanggal("Tanggal Booking (dd-mm-yyyy): ", "20-10-2026");
        Pelanggan p = new Pelanggan(nama, hp, alamat);
        int idArtist = artistController.getIdByIndex(pilihan - 1);
        boolean ok = idArtist > 0 && bookingDAO.insert(p, idArtist, desain, harga, tanggal);
        BookingView.tampilkanPesan(ok ? "Booking berhasil ditambahkan." : "Booking gagal ditambahkan.");
    }

    private void inputUpdateBooking() {
        BookingView.tampilkanHeader("UPDATE BOOKING");
        if (!tampilkanDataAtauKosong()) return;
        int id = InputValidator.bacaInt("ID Booking yang diupdate: ");
        Booking b = cariById(id);
        if (b == null) { BookingView.tampilkanPesan("ID tidak ditemukan."); return; }
        BookingView.tampilkanDetailBooking(b);
        String desain = InputValidator.bacaStringOpsional("Nama Desain baru", b.getNamaDesain());
        double harga = InputValidator.bacaHargaOpsional("Harga baru", b.getHarga());
        String tanggal = InputValidator.bacaTanggalOpsional("Tanggal baru (dd-mm-yyyy)", b.getTanggal());
        BookingView.tampilkanPesan(bookingDAO.update(id, desain, harga, tanggal) ? "Data booking diperbarui." : "Gagal memperbarui booking.");
    }

    private void inputUbahStatus() {
        BookingView.tampilkanHeader("UBAH STATUS BOOKING");
        if (!tampilkanDataAtauKosong()) return;
        int id = InputValidator.bacaInt("ID Booking: ");
        String status = InputValidator.bacaStatus("Status baru (Menunggu/Dikerjakan/Selesai): ");
        BookingView.tampilkanPesan(bookingDAO.updateStatus(id, status) ? "Status diperbarui." : "ID tidak ditemukan.");
    }

    private void inputHapusBooking() {
        BookingView.tampilkanHeader("HAPUS BOOKING");
        if (!tampilkanDataAtauKosong()) return;
        int id = InputValidator.bacaInt("ID Booking yang dihapus: ");
        BookingView.tampilkanPesan(hapus(id) ? "Data dihapus." : "ID tidak ditemukan.");
    }

    private boolean tampilkanDataAtauKosong() {
        ArrayList<Booking> daftar = getAll();
        if (daftar.isEmpty()) { BookingView.tampilkanPesan("(Belum ada data)"); return false; }
        BookingView.tampilkanIsiDaftarBooking(daftar);
        return true;
    }

    @Override public ArrayList<Booking> getAll() { return bookingDAO.getAll(); }
    @Override public Booking cariById(int id) { return bookingDAO.findById(id); }
    @Override public boolean hapus(int id) { return bookingDAO.delete(id); }
}
