/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.InputValidator;

public class TattooArtist extends Orang {
    private String spesialisasi;
 
    public TattooArtist(String nama, String noHp, String spesialisasi) {
        super(nama, noHp);
        setSpesialisasi(spesialisasi);
    }
 
    public String getSpesialisasi() { return spesialisasi; }

    public void setSpesialisasi(String spesialisasi) {
        if (InputValidator.isTeksValid(spesialisasi)) {
            this.spesialisasi = spesialisasi.trim();
        }
    }
 
    @Override
    public String getInfo() {
        return "Nama: " + getNama() + " | Spesialisasi: " + spesialisasi;
    }
}
