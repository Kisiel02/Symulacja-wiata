package SymulacjaSwiata.Rosliny;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Roslina;
import SymulacjaSwiata.Zwierze;

import java.awt.*;

import static SymulacjaSwiata.Konfiguracja.*;

public class WilczeJagody extends Roslina {
    public WilczeJagody(Point punkt, Gra gra)
    {
        super(silaWilczychJagod, punkt, szansaRozmnozeniaWilczychJagod, false, gra, znakWilczychJagod, kolorWilczychJagod);
    }

    public WilczeJagody(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, punkt, szansaRozmnozeniaWilczychJagod, czySieRozmnozyl, gra, znakWilczychJagod, kolorWilczychJagod);
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
    }
}
