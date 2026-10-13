/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import util.InputValidator;

public class Pelanggan extends Orang {
    private String alamat;
 
    public Pelanggan(String nama, String noHp, String alamat) {
        super(nama, noHp);
        setAlamat(alamat);
    }
 
    public String getAlamat() { return alamat; }

    public void setAlamat(String alamat) {
        if (InputValidator.isTeksValid(alamat)) {
            this.alamat = alamat.trim();
        }
    }
 
    @Override
    public String getInfo() {
        return "Nama: " + getNama() + " | HP: " + getNoHp() + " | Alamat: " + alamat;
    }
}