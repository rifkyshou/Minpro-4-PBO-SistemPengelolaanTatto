package controller;

import dao.ArtistDAO;
import model.TattooArtist;
import java.util.ArrayList;

public class ArtistController {
    private final ArtistDAO artistDAO = new ArtistDAO();

    public TattooArtist tambahArtist(String nama, String noHp, String spesialisasi) {
        TattooArtist artist = new TattooArtist(nama, noHp, spesialisasi);
        return artistDAO.insert(artist) ? artist : null;
    }

    public ArrayList<TattooArtist> getAll() {
        return artistDAO.getAll();
    }

    public int getIdByIndex(int index) {
        return artistDAO.getIdByIndex(index);
    }
}
