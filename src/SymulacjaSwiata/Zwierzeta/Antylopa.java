package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.*;

public class Antylopa extends Zwierze {

    public Antylopa(Point punkt, Gra gra)
    {
        super(silaAntylopy, inicjatywaAntylopy, punkt, false, gra, znakAntylopy, kolorAntylopy);
    }

    public Antylopa(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, inicjatywaAntylopy, punkt, czySieRozmnozyl, gra, znakAntylopy, kolorAntylopy);
    }

    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        if (atakujacy instanceof Antylopa) {
            rozmnazanie(this, atakujacy);
            this.czySieRozmnozyl = true;
            atakujacy.setCzySieRozmnozyl(true);
        } else {
            if (!czyOdbilAtak(atakujacy)) {
                Random generator = new Random();
                if (generator.nextInt(2) == 1) {
                    ArrayList<Point> pola = swiat.wolnePola(this.punkt, 2);
                    if (pola.size() > 0) {
                        int random = generator.nextInt(pola.size());
                        idzNaPunkt(pola.get(random));
                        swiat.getGra().getKomentator().dodajWiadomosc("Antylopa ucieka od " + atakujacy.getClass().getSimpleName() + "\n");
                    } else
                        this.umrzyj(atakujacy);
                } else
                    this.umrzyj(atakujacy);

            } else if (!odbity)
                atakujacy.kolizja(this, true);
        }
    }

    public void akcja()
    {
        Random generator = new Random();
        ArrayList<Point> sasiedzi = this.swiat.sasiedzi(this.punkt, 2);
        int losowa = generator.nextInt(sasiedzi.size());
        Point nowyPunkt = sasiedzi.get(losowa);
        if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null)
            idzNaPunkt(nowyPunkt);
        else
            swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x].kolizja(this, false);
        if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null
                && this.czyZywy())
            idzNaPunkt(nowyPunkt);
    }


}

