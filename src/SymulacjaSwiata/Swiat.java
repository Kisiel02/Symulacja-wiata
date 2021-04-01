package SymulacjaSwiata;

import SymulacjaSwiata.Zwierzeta.Czlowiek;

import java.awt.*;
import java.io.FileWriter;
import java.util.ArrayList;

public class Swiat {

    private int x;
    private int y;
    private Gra gra;

    private Organizm[][] plansza;

    public ArrayList<Organizm> getOrganizmy()
    {
        return organizmy;
    }

    private ArrayList<Organizm> organizmy = new ArrayList<>();

    public Swiat(int x, int y, Gra gra)
    {
        this.x = x;
        this.y = y;
        plansza = new Organizm[y][x];
        wyczysc();
        this.gra = gra;
    }

    public Gra getGra()
    {
        return gra;
    }

    public void zabij(Organizm org)
    {
        plansza[org.getY()][org.getX()] = null;
    }

    public void dodajOrganizm(Organizm organizm)
    {
        organizmy.add(organizm);
        plansza[organizm.getY()][organizm.getX()] = organizm;
        gra.odswiezSwiat();
    }


    public Boolean czyZajete(Point punkt)
    {
        if (this.plansza[punkt.y][punkt.x] != null)
            return true;
        else
            return false;
    }

    public ArrayList<Point> sasiedzi(Point punkt, int odleglosc)
    {
        odleglosc = odleglosc - 1;
        ArrayList<Point> sasiedzi = new ArrayList<>();
        if (punkt.getX() > 0 + odleglosc)
            sasiedzi.add(new Point(punkt.x - 1 - odleglosc, punkt.y));
        if (punkt.getX() < x - 1 - odleglosc)
            sasiedzi.add(new Point(punkt.x + 1 + odleglosc, punkt.y));
        if (punkt.getY() > 0 + odleglosc)
            sasiedzi.add(new Point(punkt.x, punkt.y - 1 - odleglosc));
        if (punkt.getY() < y - 1 - odleglosc)
            sasiedzi.add(new Point(punkt.x, punkt.y + 1 + odleglosc));
        return sasiedzi;
    }

    public ArrayList<Point> wolnePola(Point punkt, int odleglosc)
    {
        ArrayList<Point> pola = sasiedzi(punkt, odleglosc);
        ArrayList<Point> pola2 = new ArrayList<>();
        for (int i = 0; i < pola.size(); i++)
            if (plansza[pola.get(i).y][pola.get(i).x] == null)
                pola2.add(pola.get(i));
        return pola2;
    }

    public void zaktualizuj(Organizm org, Point stary)
    {
        plansza[stary.y][stary.x] = null;
        plansza[org.getY()][org.getX()] = org;
    }

    public Czlowiek getCzlowiek()
    {
        for (Organizm org : organizmy) {
            if (org instanceof Czlowiek)
                return (Czlowiek) org;

        }
        return null;
    }

    public Organizm[][] getPlansza()
    {
        return plansza;
    }

    public void zwiekszWiek()
    {
        for (int i = 0; i < organizmy.size(); i++) {
            organizmy.get(i).zwiekszWiek();
        }
    }

    public void zapisz(FileWriter zapis)
    {
        try {
            zapis.write(x + " " + y + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean sprawdzCzyWolne(Point punkt)
    {
        return plansza[punkt.y][punkt.x] == null;
    }

    public void wyczysc()
    {
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++)
                plansza[i][j] = null;
    }


}
