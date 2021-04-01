package SymulacjaSwiata;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public abstract class Organizm implements Comparable<Organizm>, ZachowaniaOrganizmu, Serializable, Zapis {
    protected int wiek;
    protected int sila;
    protected int inicjatywa;
    protected Color kolor;
    protected Point punkt;
    protected boolean czySieRozmnozyl;
    protected boolean zywy;
    protected Swiat swiat;
    protected Random generator;

    @Override
    public int compareTo(Organizm o)
    {
        if (this.inicjatywa > o.inicjatywa)
            return -1;
        else if (this.inicjatywa < o.inicjatywa)
            return 1;
        else
            return 0;
    }

    public Organizm(int sila, int inicjatywa, Point punkt, boolean czySieRozmnozyl, Gra gra, char znak, Color kolor)
    {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.punkt = punkt;
        this.czySieRozmnozyl = czySieRozmnozyl;
        this.swiat = gra.getSwiat();
        this.znak = znak;
        this.zywy = true;
        this.kolor = kolor;
        this.wiek = 0;
        swiat.dodajOrganizm(this);
    }


    @Override
    public void zapisz(FileWriter zapis)
    {
        try {
            zapis.write(this.getClass().getSimpleName() + " " +
                    this.punkt.x + " " + this.punkt.y + " " + sila + " " +
                    wiek + " " + czySieRozmnozyl + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void kolizja(Zwierze atakujacy, boolean odbity);

    public int getWiek()
    {
        return wiek;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName();
    }

    public void zwiekszWiek()
    {
        wiek++;
    }

    public void setWiek(int wiek)
    {
        this.wiek = wiek;
    }

    public char rysuj()
    {
        return znak;
    }

    public int getSila()
    {
        return sila;
    }

    public void setSila(int sila)
    {
        this.sila = sila;
    }

    public int getInicjatywa()
    {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa)
    {
        this.inicjatywa = inicjatywa;
    }

    public Point getPunkt()
    {
        return punkt;
    }

    public void setPunkt(Point punkt)
    {
        this.punkt = punkt;
    }

    public int getY()
    {
        return punkt.y;
    }

    public int getX()
    {
        return punkt.x;
    }

    public boolean isCzySieRozmnozyl()
    {
        return czySieRozmnozyl;
    }

    public void setCzySieRozmnozyl(boolean czySieRozmnozyl)
    {
        this.czySieRozmnozyl = czySieRozmnozyl;
    }

    public String nazwa()
    {
        return this.getClass().getSimpleName();
    }

    public Color getKolor()
    {
        return kolor;
    }

    public boolean czyZywy()
    {
        return zywy;
    }

    public abstract void umrzyj(Organizm atakujacy);

    private char znak;
}
