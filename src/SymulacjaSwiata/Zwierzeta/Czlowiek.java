package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Zwierze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static SymulacjaSwiata.Konfiguracja.*;


public class Czlowiek extends Zwierze {

    private boolean czyAktywnaMoc = false;
    private int licznikMocy = -czasTrwaniaMocySpecjalnej;

    public Czlowiek(Point punkt, Gra gra)
    {
        super(silaCzlowieka, inicjatywaCzlowieka, punkt, false, gra, znakCzlowieka, kolorCzlowieka);
    }

    public Czlowiek(int sila, Point punkt, Gra gra, int licznikMocy)
    {
        super(sila, inicjatywaCzlowieka, punkt, false, gra, znakCzlowieka, kolorCzlowieka);
        this.licznikMocy = licznikMocy;
    }

    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        if (atakujacy instanceof Czlowiek) {
            rozmnazanie(this, atakujacy);
            this.czySieRozmnozyl = true;
            atakujacy.setCzySieRozmnozyl(true);
        } else {
            if (!czyOdbilAtak(atakujacy)) {
                this.umrzyj(atakujacy);
            } else if (!odbity)
                atakujacy.kolizja(this, true);
        }
    }

    public void aktywujMocSpecjalna()
    {
        swiat.getGra().getOkno().wyczyscKomentarz();
        if (licznikMocy <= -czasTrwaniaMocySpecjalnej) {
            czyAktywnaMoc = true;
            swiat.getGra().getKomentator().dodajWiadomosc("Aktywowano moc spcjalną!\n");
            licznikMocy = czasTrwaniaMocySpecjalnej;
        } else if (licznikMocy > 0)
            swiat.getGra().getKomentator().dodajWiadomosc(
                    "Moc specjalna aktywna jescze przez " + licznikMocy + " tur\n");
        else
            swiat.getGra().getKomentator().dodajWiadomosc(
                    "Moc specjalna aktywna za " + Integer.toString(czasTrwaniaMocySpecjalnej + licznikMocy) + " tur\n");

        swiat.getGra().getOkno().aktualizujKomentarz(swiat.getGra().getKomentator().getKomentarz());
    }

    public void akcja()
    {
        sprawdzMocSpecjalna();
        int odleglosc = 0;
        if (czyAktywnaMoc)
            odleglosc = 2;
        else
            odleglosc = 1;
        Point nowyPunkt = kodNaPunkt(swiat.getGra().getKodRuchu(), odleglosc);
        ArrayList<Point> sasiedzi = swiat.sasiedzi(this.punkt, odleglosc);
        if (sprawdzCzySasiad(sasiedzi, nowyPunkt)) {
            if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null)
                idzNaPunkt(nowyPunkt);
            else
                swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x].kolizja(this, false);
            if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null
                    && this.czyZywy())
                idzNaPunkt(nowyPunkt);
            licznikMocy--;
        } else {
            JOptionPane.showMessageDialog(swiat.getGra().getOkno(),
                    "Zmień kierunek ruchu!", "Zły ruch",
                    JOptionPane.ERROR_MESSAGE);
            akcja();
        }
    }

    public void zapisz(FileWriter zapis)
    {
        try {
            zapis.write(this.getClass().getSimpleName() + " " +
                    this.punkt.x + " " + this.punkt.y + " " + sila + " " +
                    +licznikMocy + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Point kodNaPunkt(int kierunek, int odległosc)
    {
        odległosc--;
        Point punkt = new Point();
        switch (kierunek) {
            case KeyEvent.VK_DOWN: {
                punkt.x = this.punkt.x;
                punkt.y = this.punkt.y + 1 + odległosc;
                break;
            }
            case KeyEvent.VK_UP: {
                punkt.x = this.punkt.x;
                punkt.y = this.punkt.y - 1 - odległosc;
                break;
            }
            case KeyEvent.VK_LEFT: {
                punkt.x = this.punkt.x - 1 - odległosc;
                punkt.y = this.punkt.y;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                punkt.x = this.punkt.x + 1 + odległosc;
                punkt.y = this.punkt.y;
                break;
            }
        }
        return punkt;
    }

    private boolean sprawdzCzySasiad(ArrayList<Point> sasiedzi, Point punkt)
    {
        for (Point sasiad : sasiedzi) {
            if (punkt.equals(sasiad)) {
                return true;
            }
        }
        return false;
    }

    private void sprawdzMocSpecjalna()
    {
        if (licznikMocy > 2)
            czyAktywnaMoc = true;
        else if (licznikMocy > 0) {
            int losowa = generator.nextInt(2);
            if (losowa == 0)
                czyAktywnaMoc = true;
            else
                czyAktywnaMoc = false;
        } else
            czyAktywnaMoc = false;
    }

}
