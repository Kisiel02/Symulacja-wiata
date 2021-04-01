package SymulacjaSwiata.Interfejs;

import SymulacjaSwiata.Organizm;

import java.util.ArrayList;

public class Komentator {
    private ArrayList<String> komentarz;

    public Komentator()
    {
        komentarz = new ArrayList<String>();
    }

    public void informacjaOSmierci(Organizm atakujacy, Organizm umierajacy)
    {
        komentarz.add(atakujacy.getClass().getSimpleName() + " zabil " + umierajacy.getClass().getSimpleName() + "\n");
    }

    public void informacjaOZjedzeniu(Organizm atakujacy, Organizm umierajacy)
    {
        komentarz.add(atakujacy.getClass().getSimpleName() + " zjadł " + umierajacy.getClass().getSimpleName() + "\n");
    }

    public void wyczyscKomentarz()
    {
        komentarz.clear();
    }

    public ArrayList<String> getKomentarz()
    {
        return komentarz;
    }

    public void dodajWiadomosc(String wiadomosc)
    {
        komentarz.add(wiadomosc);
    }
}
