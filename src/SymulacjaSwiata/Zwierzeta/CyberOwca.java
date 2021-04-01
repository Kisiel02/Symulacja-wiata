package SymulacjaSwiata.Zwierzeta;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Organizm;
import SymulacjaSwiata.Rosliny.BarszczSosnowskiego;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static SymulacjaSwiata.Konfiguracja.*;

public class CyberOwca extends Owca {
    private ArrayList<BarszczSosnowskiego> barszcze;

    public CyberOwca(Point punkt, Gra gra)
    {
        super(silaCyberOwcy, inicjatywaCyberOwcy, punkt, false, gra, kolorCyberOwcy, znakCyberOwcy);
        barszcze = new ArrayList<>();
    }

    public CyberOwca(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, inicjatywaCyberOwcy, punkt, false, gra, kolorCyberOwcy, znakCyberOwcy);
        barszcze = new ArrayList<>();
    }

    @Override
    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        super.kolizja(atakujacy, odbity);
    }

    @Override
    public void akcja()
    {
        pobierzBarszcze();
        if (barszcze.size() <= 0)
            super.akcja();
        else {
            Point docelowy = znajdzNajblizszyBarszcz();
            Point nowyPunkt;
            if (docelowy.x != this.punkt.x) {
                if (docelowy.x > this.punkt.x)
                    nowyPunkt = new Point(this.punkt.x + 1, this.punkt.y);
                else
                    nowyPunkt = new Point(this.punkt.x - 1, this.punkt.y);
            } else {
                if (docelowy.y > this.punkt.y)
                    nowyPunkt = new Point(this.punkt.x, this.punkt.y + 1);
                else
                    nowyPunkt = new Point(this.punkt.x, this.punkt.y - 1);
            }
            if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null)
                idzNaPunkt(nowyPunkt);
            else
                swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x].kolizja(this, false);
            if (swiat.getPlansza()[nowyPunkt.y][nowyPunkt.x] == null && this.czyZywy())
                idzNaPunkt(nowyPunkt);
        }
    }

    @Override
    public void umrzyj(Organizm atakujacy)
    {
        if (!(atakujacy instanceof BarszczSosnowskiego))
            super.umrzyj(atakujacy);
    }

    private void pobierzBarszcze()
    {
        ArrayList<Organizm> organizmy = swiat.getOrganizmy();
        barszcze.clear();
        for (Organizm org : organizmy) {
            if (org instanceof BarszczSosnowskiego && org.czyZywy()) {
                barszcze.add((BarszczSosnowskiego) org);
            }
        }
    }

    private double policzOdleglosc(Point a, Point b)
    {
        double X = Math.abs(a.x - b.x);
        double Y = Math.abs(a.y - b.y);
        return Math.sqrt(X * X + Y * Y);
    }

    private Point znajdzNajblizszyBarszcz()
    {
        ArrayList<Double> odleglosci = new ArrayList<>();
        for (BarszczSosnowskiego barszcz : barszcze) {
            odleglosci.add(policzOdleglosc(barszcz.getPunkt(), this.punkt));
        }
        double min = Collections.min(odleglosci);
        for (int i = 0; i < barszcze.size(); i++) {
            if (odleglosci.get(i) == min)
                return barszcze.get(i).getPunkt();
        }
        return this.punkt;
    }


}
