package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Organizm;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.*;

public class Zolw extends Zwierze {

    public Zolw(Point punkt, Gra gra)
    {
        super(silaZolwia, inicjatywaZolwia, punkt, false, gra, znakZolwia, kolorZolwia);
    }

    public Zolw(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, inicjatywaZolwia, punkt, czySieRozmnozyl, gra, znakZolwia, kolorZolwia);
    }

    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        if (atakujacy instanceof Zolw) {
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
        int poruszenie = generator.nextInt(4);
        if (poruszenie == 3) {
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

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy)
    {
        if (atakujacy.getSila() < 5 || this.sila > atakujacy.getSila()) {
            swiat.getGra().getKomentator().dodajWiadomosc(atakujacy.getClass().getSimpleName() +
                    " jest za slaba aby zabic zółwia\n");
            return true;
        } else
            return false;
    }
}
