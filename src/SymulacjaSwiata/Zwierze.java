package SymulacjaSwiata;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Zwierze extends Organizm {
    public Zwierze(int sila, int inicjatywa, Point punkt, boolean czySieRozmnozyl, Gra gra, char znak, Color kolor)
    {
        super(sila, inicjatywa, punkt, czySieRozmnozyl, gra, znak, kolor);
        generator = new Random();
        wiek = 0;
    }

    public void umrzyj(Organizm atakujacy)
    {
        this.zywy = false;
        swiat.getGra().getKomentator().informacjaOSmierci(atakujacy, this);
        swiat.zabij(this);
    }

    protected boolean czyOdbilAtak(Organizm atakujacy)
    {
        if (this.sila > atakujacy.sila)
            return true;
        else
            return false;
    }


    protected void idzNaPunkt(Point punkt)
    {
        Point stary = this.punkt;
        this.punkt = punkt;
        swiat.zaktualizuj(this, stary);
    }

    protected void rozmnazanie(Organizm a, Organizm b)
    {
        if (!a.czySieRozmnozyl && !b.czySieRozmnozyl && a.wiek >= 0 && b.wiek >= 0) {
            ArrayList<Point> pola1 = swiat.wolnePola(a.punkt, 1);
            ArrayList<Point> pola2 = swiat.wolnePola(b.punkt, 1);
            a.setCzySieRozmnozyl(true);
            b.setCzySieRozmnozyl(true);
            for (int i = 0; i < pola2.size(); i++)
                pola1.add(pola2.get(i));
            if (pola1.size() > 0) {
                int losowa = generator.nextInt(pola1.size());
                Point punkt = pola1.get(losowa);
                swiat.getGra().getGeneratorObiektow().generujOrganizm(a.getClass().getSimpleName(), punkt);

            }
        }
    }

}

