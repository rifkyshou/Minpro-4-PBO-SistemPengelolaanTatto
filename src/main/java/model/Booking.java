/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.InputValidator;

public class Booking {
    private final int id;
    private final Pelanggan pelanggan;
    private final TattooArtist artist;
    private String namaDesain;
    private double harga;
    private String tanggal;
    private String status;
 
    public Booking(int id, Pelanggan pelanggan, TattooArtist artist, String namaDesain, double harga, String tanggal) {
        this.id = id;
        this.pelanggan = pelanggan;
        this.artist = artist;
        setNamaDesain(namaDesain);
        setHarga(harga);
        setTanggal(tanggal);
        this.status = "Menunggu";
    }
 
    public int getId() { return id; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public TattooArtist getArtist() { return artist; }
    public String getNamaDesain() { return namaDesain; }
    public double getHarga() { return harga; }
    public String getTanggal() { return tanggal; }
    public String getStatus() { return status; }
 
    public void setNamaDesain(String namaDesain) {
        if (InputValidator.isTeksValid(namaDesain)) {
            this.namaDesain = namaDesain.trim();
        }
    }

    public void setHarga(double harga) {
        if (InputValidator.isHargaValid(harga)) {
            this.harga = harga;
        }
    }

    public void setTanggal(String tanggal) {
        if (InputValidator.isTanggalValid(tanggal)) {
            this.tanggal = tanggal.trim();
        }
    }

    public void setStatus(String status) {
        if (InputValidator.isStatusValid(status)) {
            this.status = status.trim();
        }
    }
}
