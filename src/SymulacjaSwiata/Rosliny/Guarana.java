package SymulacjaSwiata.Rosliny;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Roslina;
import SymulacjaSwiata.Zwierze;

import java.awt.*;

import static SymulacjaSwiata.Konfiguracja.*;

public class Guarana extends Roslina {
    public Guarana(Point punkt, Gra gra)
    {
        super(silaGuarany, punkt, szansaRozmnozeniaGuarany, false, gra, znakGuarany, kolorGuarany);
    }

    public Guarana(int sila, Point punkt, boolean czySieRozmnozyl, Gra gra)
    {
        super(sila, punkt, szansaRozmnozeniaGuarany, czySieRozmnozyl, gra, znakGuarany, kolorGuarany);
    }

    @Override
    public void kolizja(Zwierze atakujacy, boolean odbity)
    {
        atakujacy.setSila(atakujacy.getSila() + 3);
        this.umrzyj(atakujacy);
    }

    @Override
    public void akcja()
    {
        rozmnazanie();
    }
}
