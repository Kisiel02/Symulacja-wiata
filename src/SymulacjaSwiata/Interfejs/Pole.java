package SymulacjaSwiata.Interfejs;

import SymulacjaSwiata.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static SymulacjaSwiata.Konfiguracja.*;
public class Pole extends JButton implements ActionListener {
    final private Point punkt;
    final private Swiat swiat;

    public Pole(String text, Point punkt, Swiat swiat)
    {
        super(text);
        this.setMinimumSize(new Dimension(30, 30));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBackground(Color.WHITE);
        this.punkt = punkt;
        this.swiat = swiat;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (swiat.getPlansza()[punkt.y][punkt.x] == null) {
            String klasa = (String) JOptionPane.showInputDialog(null, "Wybierz organizm do stowrzenia z listy:",
                    "Tworzenie organizmu", JOptionPane.QUESTION_MESSAGE, null, wybor, wybor[6]);
            if (klasa != null)
                swiat.getGra().getGeneratorObiektow().generujOrganizm(klasa, punkt);
        } else
            JOptionPane.showMessageDialog(swiat.getGra().getOkno(),
                    "Wybierz puste pole!", "Zły wybór pola",
                    JOptionPane.WARNING_MESSAGE);
    }
}
