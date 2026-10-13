/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Scanner;
 
public final class InputValidator {
    private static final Scanner sc = new Scanner(System.in);

    public static boolean isTeksValid(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isHargaValid(double harga) {
        return harga > 0;
    }

    public static boolean isStatusValid(String s) {
        if (s == null) return false;
        s = s.trim();
        return s.equals("Menunggu") || s.equals("Dikerjakan") || s.equals("Selesai");
    }

    private static boolean semuaAngka(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isTanggalValid(String s) {
        if (s == null) return false;
        s = s.trim();
        if (s.length() != 10 || s.charAt(2) != '-' || s.charAt(5) != '-') return false;

        String hh = s.substring(0, 2);
        String bb = s.substring(3, 5);
        String tttt = s.substring(6, 10);
        if (!semuaAngka(hh) || !semuaAngka(bb) || !semuaAngka(tttt)) return false;

        int hari = Integer.parseInt(hh);
        int bulan = Integer.parseInt(bb);
        int tahun = Integer.parseInt(tttt);
        if (tahun < 1 || bulan < 1 || bulan > 12 || hari < 1) return false;

        int maks = 31;
        if (bulan == 4 || bulan == 6 || bulan == 9 || bulan == 11) {
            maks = 30;
        } else if (bulan == 2) {
            boolean kabisat = (tahun % 4 == 0 && tahun % 100 != 0) || tahun % 400 == 0;
            if (kabisat) {
                maks = 29;
            } else {
                maks = 28;
            }
        }
        return hari <= maks;
    }
 
    public static String bacaString(String pesan) {
        return bacaString(pesan, null);
    }
 
    public static String bacaString(String pesan, String contoh) {
        while (true) {
            if (contoh != null) System.out.println("contoh: " + contoh);
            System.out.print(pesan);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input tidak boleh kosong!");
        }
    }

    public static String bacaTanggal(String pesan, String contoh) {
        while (true) {
            if (contoh != null) System.out.println("contoh: " + contoh);
            System.out.print(pesan);
            String s = sc.nextLine().trim();
            if (isTanggalValid(s)) return s;
            System.out.println("Tanggal tidak valid! Gunakan format dd-mm-yyyy dan tanggal yang benar.");
        }
    }

    public static String bacaStatus(String pesan) {
        while (true) {
            System.out.print(pesan);
            String s = sc.nextLine().trim();
            if (isStatusValid(s)) return s;
            System.out.println("Status harus Menunggu / Dikerjakan / Selesai!");
        }
    }

    public static int bacaInt(String pesan) {
        return bacaInt(pesan, null);
    }
 
    public static int bacaInt(String pesan, String contoh) {
        while (true) {
            if (contoh != null) System.out.println("contoh: " + contoh);
            System.out.print(pesan);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
    }

    public static int bacaInt(String pesan, int min, int max) {
        while (true) {
            int n = bacaInt(pesan);
            if (n >= min && n <= max) return n;
            System.out.println("Pilihan harus antara " + min + " dan " + max + "!");
        }
    }

    public static double bacaDouble(String pesan) {
        return bacaDouble(pesan, null);
    }
 
    public static double bacaDouble(String pesan, String contoh) {
        while (true) {
            if (contoh != null) System.out.println("contoh: " + contoh);
            System.out.print(pesan);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static double bacaHarga(String pesan) {
        while (true) {
            double harga = bacaDouble(pesan);
            if (isHargaValid(harga)) return harga;
            System.out.println("Harga harus lebih dari 0!");
        }
    }

    public static String bacaStringOpsional(String pesan, String lama) {
        System.out.print(pesan + " [" + lama + "] (Enter = tidak diubah): ");
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return lama;
        return s;
    }

    public static double bacaHargaOpsional(String pesan, double lama) {
        while (true) {
            System.out.print(pesan + " [" + (long) lama + "] (Enter = tidak diubah): ");
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return lama;
            try {
                double harga = Double.parseDouble(s);
                if (isHargaValid(harga)) return harga;
                System.out.println("Harga harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static String bacaTanggalOpsional(String pesan, String lama) {
        while (true) {
            System.out.print(pesan + " [" + lama + "] (Enter = tidak diubah): ");
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return lama;
            if (isTanggalValid(s)) return s;
            System.out.println("Tanggal tidak valid! Gunakan format dd-mm-yyyy dan tanggal yang benar.");
        }
    }
}