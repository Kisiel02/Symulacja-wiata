package SymulacjaSwiata;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.inicjatywaRoslin;

public abstract class Roslina extends Organizm {

    protected double szansaRozmnozenia;

    public Roslina(int sila, Point punkt, double szansaRozmnozenia, boolean czySieRozmnozyl, Gra gra, char znak, Color kolor)
    {
        super(sila, inicjatywaRoslin, punkt, czySieRozmnozyl, gra, znak, kolor);
        generator = new Random();
        wiek = 0;
        this.szansaRozmnozenia = szansaRozmnozenia;
    }

    protected void rozmnazanie()
    {
        int losowa = generator.nextInt(100) + 1;
        if (this.wiek >= 0 && this.szansaRozmnozenia * 100 >= losowa) {
            ArrayList<Point> sasiedzi = swiat.wolnePola(this.punkt, 1);
            if (sasiedzi.size() > 0) {
                losowa = generator.nextInt(sasiedzi.size());
                Point nowyPunkt = sasiedzi.get(losowa);
                swiat.getGra().getGeneratorObiektow().generujOrganizm(this.getClass().getSimpleName(), nowyPunkt);
            }

        }
    }

    public void umrzyj(Organizm atakujacy)
    {
        this.zywy = false;
        swiat.getGra().getKomentator().informacjaOZjedzeniu(atakujacy, this);
        swiat.zabij(this);
    }


}
