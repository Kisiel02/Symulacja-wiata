package SymulacjaSwiata.Rosliny;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Roslina;
import SymulacjaSwiata.Zwierze;

import java.awt.*;
import java.util.ArrayList;

import static SymulacjaSwiata.Konfiguracja.*;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(Point punkt, Gra gra)
    {
        super(silaBarszczuSosnowskiego, punkt, szansaRozmnozeniaBarszczuSosnowskiego, false, gra, znakBarszczuSosnowskiego, kolorBarszczuSosnowskiego);
    }

    public BarszczSosnowskiego(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, punkt, szansaRozmnozeniaBarszczuSosnowskiego, czySieRozmnozyl, gra, znakBarszczuSosnowskiego, kolorBarszczuSosnowskiego);
    }

    @Override
    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        atakujacy.umrzyj(this);
        this.umrzyj(atakujacy);
    }

    @Override
    public void akcja()
    {
        rozmnazanie();
        ArrayList<Point> sasiedzi = swiat.sasiedzi(this.punkt, 1);
        for (Point punk : sasiedzi) {
            if (swiat.getPlansza()[punk.y][punk.x] instanceof Zwierze) {
                swiat.getPlansza()[punk.y][punk.x].umrzyj(this);
            }
        }
    }
}
