/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;
import java.util.ArrayList;
import model.Booking;

/**
 *
 */
public interface Crud {
    ArrayList<Booking> getAll();
    Booking cariById(int id);
    boolean hapus(int id);
}