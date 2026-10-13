/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.InputValidator;

public abstract class Orang {
    private String nama;
    private String noHp;

    public Orang(String nama, String noHp) {
        setNama(nama);
        setNoHp(noHp);
    }

    public String getNama() { return nama; }
    public String getNoHp() { return noHp; }

    public final void setNama(String nama) {
        if (InputValidator.isTeksValid(nama)) {
            this.nama = nama.trim();
        }
    }

    public final void setNoHp(String noHp) {
        if (InputValidator.isTeksValid(noHp)) {
            this.noHp = noHp.trim();
        }
    }

    public abstract String getInfo();
}
