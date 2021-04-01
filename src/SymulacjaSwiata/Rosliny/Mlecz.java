package SymulacjaSwiata.Rosliny;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Roslina;
import SymulacjaSwiata.Zwierze;

import java.awt.*;

import static SymulacjaSwiata.Konfiguracja.*;

public class Mlecz extends Roslina {
    public Mlecz(Point punkt, Gra gra)
    {
        super(silaMlecza, punkt, szansaRozmnozeniaMlecza, false, gra, znakMlecza, kolorMlecza);
    }

    public Mlecz(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, punkt, szansaRozmnozeniaMlecza, czySieRozmnozyl, gra, znakMlecza, kolorMlecza);
    }

    @Override
    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        this.umrzyj(atakujacy);
    }

    @Override
    public void akcja()
    {
        for (int i = 0; i < iloscProbRozmnozenMlecza; i++) {
            rozmnazanie();
        }
    }
}
