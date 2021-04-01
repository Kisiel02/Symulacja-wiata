package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.*;

public class Wilk extends Zwierze {

    public Wilk(Point punkt, Gra gra)
    {
        super(silaWilka, inicjatywaWilka, punkt, false, gra, znakWilka, kolorWilka);
    }

    public Wilk(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, inicjatywaWilka, punkt, czySieRozmnozyl, gra, znakWilka, kolorWilka);
    }

    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        if (atakujacy instanceof Wilk) {
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

    public void akcja()
    {
        Random generator = new Random();
        ArrayList<Point> sasiedzi = this.swiat.sasiedzi(this.punkt, 1);
        int losowa = generator.nextInt(sasiedzi.size());
        Point nowyPunkt = sasiedzi.get(losowa);
        if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null)
            idzNaPunkt(nowyPunkt);
        else
            swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x].kolizja(this, false);
        if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null && this.czyZywy())
            idzNaPunkt(nowyPunkt);
    }
}
