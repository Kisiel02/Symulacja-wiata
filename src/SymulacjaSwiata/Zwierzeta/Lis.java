package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.*;

public class Lis extends Zwierze {

    public Lis(Point punkt, Gra gra)
    {
        super(silaLisa, inicjatywaLisa, punkt, false, gra, znakLisa, kolorLisa);
    }

    public Lis(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, inicjatywaLisa, punkt, czySieRozmnozyl, gra, znakLisa, kolorLisa);
    }

    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        if (atakujacy instanceof Lis) {
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
        ArrayList<Point> slabsiSasiedzi = new ArrayList<>();
        for (Point punkt : sasiedzi) {
            if (swiat.getPlansza()[punkt.y][punkt.x] == null ||
                    swiat.getPlansza()[punkt.y][punkt.x].getSila() <= this.sila)
                slabsiSasiedzi.add(punkt);
        }
        if (slabsiSasiedzi.size() > 0) {
            int losowa = generator.nextInt(slabsiSasiedzi.size());
            Point nowyPunkt = slabsiSasiedzi.get(losowa);
            if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null)
                idzNaPunkt(nowyPunkt);
            else
                swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x].kolizja(this, false);
            if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null && this.czyZywy())
                idzNaPunkt(nowyPunkt);
        }
    }
}
