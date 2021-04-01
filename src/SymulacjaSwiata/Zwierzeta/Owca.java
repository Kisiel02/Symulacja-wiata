package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.*;

public class Owca extends Zwierze {

    public Owca(Point punkt, Gra gra)
    {
        super(silaOwcy, inicjatywaOwcy, punkt, false, gra, znakOwcy, kolorOwcy);
    }

    public Owca(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, inicjatywaOwcy, punkt, czySieRozmnozyl, gra, znakOwcy, kolorOwcy);
    }

    protected Owca(int sila, int inicjatywa, Point punkt, boolean czySieRozmnozyl, Gra gra, Color kolor, char znak)
    {
        super(sila, inicjatywa, punkt, czySieRozmnozyl, gra, znak, kolor);
    }

    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        if (atakujacy instanceof Owca) {
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
