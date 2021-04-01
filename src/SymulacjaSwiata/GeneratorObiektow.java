package SymulacjaSwiata;

import SymulacjaSwiata.Rosliny.*;
import SymulacjaSwiata.Zwierzeta.*;

import java.awt.*;

import static SymulacjaSwiata.Konfiguracja.ileTurBezRozmnazania;

public class GeneratorObiektow {
    private Gra gra;

    public GeneratorObiektow(Gra gra)
    {
        this.gra = gra;
    }

    public void generujOrganizm(String klasa, Point punkt)
    {
        Organizm org;
        switch (klasa) {
            case "Trawa":
                org = new Trawa(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Mlecz":
                org = new Mlecz(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Guarana":
                org = new Guarana(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "WilczeJagody":
                org = new WilczeJagody(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "BarszczSosnowskiego":
                org = new BarszczSosnowskiego(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Owca":
                org = new Owca(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Wilk":
                org = new Wilk(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Antylopa":
                org = new Antylopa(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Zolw":
                org = new Zolw(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Lis":
                org = new Lis(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "Czlowiek":
                org = new Czlowiek(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
            case "CyberOwca":
                org = new CyberOwca(punkt, gra);
                org.setWiek(-ileTurBezRozmnazania);
                break;
        }
    }

    public void generujOrganizm(String klasa, Point punkt, boolean czySieRozmnozyl, int sila, int wiek)
    {
        Organizm org;
        switch (klasa) {
            case "Trawa":
                org = new Trawa(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Mlecz":
                org = new Mlecz(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Guarana":
                org = new Guarana(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "WilczeJagody":
                org = new WilczeJagody(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "BarszczSosnowskiego":
                org = new BarszczSosnowskiego(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Owca":
                org = new Owca(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Wilk":
                org = new Wilk(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Antylopa":
                org = new Antylopa(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Zolw":
                org = new Zolw(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "Lis":
                org = new Lis(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
            case "CyberOwca":
                org = new CyberOwca(sila, punkt, czySieRozmnozyl, gra);
                org.setWiek(wiek);
                break;
        }
    }

    public void generujCzlowieka(Point punkt, int sila, int licznik)
    {
        Czlowiek czlowiek = new Czlowiek(sila, punkt, gra, licznik);
    }
}
