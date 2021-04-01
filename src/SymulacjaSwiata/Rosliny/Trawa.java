package SymulacjaSwiata.Rosliny;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Roslina;
import SymulacjaSwiata.Zwierze;

import java.awt.*;

import static SymulacjaSwiata.Konfiguracja.*;

public class Trawa extends Roslina {
    public Trawa(Point punkt, Gra gra)
    {
        super(silaTrawy, punkt, szansaRozmnozeniaTrawy, false, gra, znakTrawy, kolorTrawy);
    }

    public Trawa(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, punkt, szansaRozmnozeniaTrawy, czySieRozmnozyl, gra, znakTrawy, kolorTrawy);
    }

    @Override
    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        this.umrzyj(atakujacy);
    }

    @Override
    public void akcja()
    {
        rozmnazanie();
    }


}
