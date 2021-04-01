package SymulacjaSwiata;

import SymulacjaSwiata.Interfejs.Komentator;
import SymulacjaSwiata.Interfejs.OknoProgramu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import static SymulacjaSwiata.Konfiguracja.plikZapisu;
import static java.awt.event.KeyEvent.*;

public class Gra implements KeyEventDispatcher, ActionListener {
    private Swiat swiat;
    private OknoProgramu okno;
    private Komentator komentator;
    private GeneratorObiektow generatorObiektow;
    private int kodRuchu = VK_RIGHT;

    public Gra(int x, int y)
    {
        swiat = new Swiat(x, y, this);
        okno = new OknoProgramu(x, y, swiat, this);
        generatorObiektow = new GeneratorObiektow(this);
        komentator = new Komentator();
    }

    public GeneratorObiektow getGeneratorObiektow()
    {
        return generatorObiektow;
    }

    public Komentator getKomentator()
    {
        return komentator;
    }

    public Swiat getSwiat()
    {
        return swiat;
    }

    public void setSwiat(Swiat swiat)
    {
        this.swiat = swiat;
    }

    public OknoProgramu getOkno()
    {
        return okno;
    }

    public void setOkno(OknoProgramu okno)
    {
        this.okno = okno;
    }

    public void odswiezSwiat()
    {
        okno.odswiez();
    }

    public void nowaTura()
    {
        okno.wyczyscKomentarz();
        komentator.wyczyscKomentarz();
        Collections.sort(swiat.getOrganizmy());
        for (int i = 0; i < swiat.getOrganizmy().size(); i++) {
            if (swiat.getOrganizmy().get(i).czyZywy()) {
                swiat.getOrganizmy().get(i).akcja();
                odswiezSwiat();
                okno.requestFocusInWindow();
            }
        }
        usunMartwe();
        resetujRozmnazanie();
        swiat.zwiekszWiek();
        okno.aktualizujKomentarz(komentator.getKomentarz());
    }

    public int getKodRuchu()
    {
        return kodRuchu;
    }

    public void setKodRuchu(int kodRuchu)
    {
        this.kodRuchu = kodRuchu;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e)
    {
        int kierunek = e.getKeyCode();
        if ((kierunek == VK_DOWN || kierunek == VK_UP || kierunek == VK_LEFT || kierunek == VK_RIGHT) && kierunek != kodRuchu) {
            swiat.getGra().setKodRuchu(kierunek);
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(okno.getMocSpecjalna()))
            swiat.getCzlowiek().aktywujMocSpecjalna();
        else if (e.getSource().equals(okno.getNowaTura()))
            nowaTura();
        else if (e.getSource().equals(okno.getZapisz())) {
            zapisz();
        } else if (e.getSource().equals(okno.getZaladuj())) {
            wczytaj();
        }
    }

    public void zapisz()
    {
        FileWriter zapis = null;
        try {
            zapis = new FileWriter(plikZapisu);
            swiat.zapisz(zapis);
            for (Organizm organizm : swiat.getOrganizmy()) {
                organizm.zapisz(zapis);
            }
            komentator.dodajWiadomosc("Poprawnie zapisano świat\n");
            okno.wyczyscKomentarz();
            okno.aktualizujKomentarz(komentator.getKomentarz());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (zapis != null) {
                try {
                    zapis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void wczytaj()
    {
        swiat.getOrganizmy().clear();
        this.swiat = null;
        BufferedReader odczyt = null;
        try {
            odczyt = new BufferedReader(new FileReader(plikZapisu));
            String input = odczyt.readLine();
            int x = Integer.parseInt(input.split(" ")[0]);
            int y = Integer.parseInt(input.split(" ")[1]);
            this.swiat = new Swiat(x, y, this);
            okno.dispose();
            okno = null;
            this.okno = new OknoProgramu(x, y, swiat, this);
            while (input != null) {
                input = odczyt.readLine();
                if (input != null) {
                    String[] daneOrganizmu = input.split(" ");
                    String klasa = daneOrganizmu[0];
                    Point punkt = new Point(Integer.parseInt(daneOrganizmu[1]),
                            Integer.parseInt(daneOrganizmu[2]));
                    int sila = Integer.parseInt(daneOrganizmu[3]);
                    if (klasa.equals("Czlowiek")) {
                        int licznik = Integer.parseInt(daneOrganizmu[4]);
                        generatorObiektow.generujCzlowieka(punkt, sila, licznik);
                    } else {
                        int wiek = Integer.parseInt(daneOrganizmu[4]);
                        boolean czySieRozmnozyl = Boolean.parseBoolean(daneOrganizmu[5]);
                        generatorObiektow.generujOrganizm(daneOrganizmu[0], punkt);
                    }

                    okno.revalidate();
                    okno.repaint();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        komentator.wyczyscKomentarz();
    }


    private void usunMartwe()
    {
        Iterator<Organizm> iterator = swiat.getOrganizmy().iterator();
        while (iterator.hasNext()) {
            Organizm org = iterator.next();
            if (!org.czyZywy()) {
                iterator.remove();
            }
        }
    }

    private void resetujRozmnazanie()
    {
        for (int i = 0; i < swiat.getOrganizmy().size(); i++) {
            if (swiat.getOrganizmy().get(i) instanceof Zwierze && swiat.getOrganizmy().get(i).czySieRozmnozyl) {
                swiat.getOrganizmy().get(i).setCzySieRozmnozyl(false);
            }
        }
    }

}
