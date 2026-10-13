/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Booking;
import model.Orang;
import model.TattooArtist;
import java.util.ArrayList;
 
public class BookingView {

    private static final String GARIS = "========================================================================";
    private static final String GARIS_TIPIS = "------------------------------------------------------------------------";

    public static void tampilkanHeader(String judul) {
        String isi = judul;
        // rata tengah dalam lebar 70 karakter
        while (isi.length() < 69) {
            isi = " " + isi + " ";
        }
        if (isi.length() < 70) isi = isi + " ";
        System.out.println();
        System.out.println(GARIS);
        System.out.println("|" + isi.substring(0, 70) + "|");
        System.out.println(GARIS);
    }
 
    public static void tampilkanMenuUtama() {
        tampilkanHeader("SISTEM PENGELOLAAN STUDIO TATTOO");
        System.out.println(" 1. Tambah Artist");
        System.out.println(" 2. Tambah Booking");
        System.out.println(" 3. Lihat Semua Booking");
        System.out.println(" 4. Update Booking");
        System.out.println(" 5. Ubah Status Booking");
        System.out.println(" 6. Hapus Booking");
        System.out.println(" 7. Cari Booking berdasarkan ID");
        System.out.println(" 0. Keluar");
        System.out.println(GARIS);
    }

    public static void tampilkanDaftarArtist(ArrayList<TattooArtist> daftar) {
        System.out.println("\nDaftar Tattoo Artist:");
        System.out.println(GARIS_TIPIS);
        for (int i = 0; i < daftar.size(); i++) {
            Orang artist = daftar.get(i);
            System.out.println(" " + (i + 1) + ". " + artist.getInfo());
        }
        System.out.println(GARIS_TIPIS);
    }

    public static void tampilkanDetailBooking(Booking b) {
        Orang pelanggan = b.getPelanggan();
        Orang artist = b.getArtist();

        System.out.println(" ID Booking : " + b.getId());
        System.out.println(" Pelanggan  : " + pelanggan.getInfo());
        System.out.println(" Artist     : " + artist.getInfo());
        System.out.println(" Desain     : " + b.getNamaDesain());
        System.out.println(" Harga      : Rp" + (long) b.getHarga());
        System.out.println(" Tanggal    : " + b.getTanggal());
        System.out.println(" Status     : " + b.getStatus());
    }

    public static void tampilkanIsiDaftarBooking(ArrayList<Booking> daftar) {
        for (int i = 0; i < daftar.size(); i++) {
            tampilkanDetailBooking(daftar.get(i));
            if (i < daftar.size() - 1) {
                System.out.println(GARIS_TIPIS);
            } else {
                System.out.println(GARIS);
            }
        }
    }
 
    public static void tampilkanDaftarBooking(ArrayList<Booking> daftar) {
        tampilkanHeader("DAFTAR BOOKING");
        if (daftar.isEmpty()) {
            System.out.println(" (Belum ada data)");
            System.out.println(GARIS);
            return;
        }
        tampilkanIsiDaftarBooking(daftar);
    }

    public static void tampilkanGarisTipis() {
        System.out.println(GARIS_TIPIS);
    }
 
    public static void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}

 

