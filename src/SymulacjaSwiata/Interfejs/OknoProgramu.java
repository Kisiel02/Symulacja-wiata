package SymulacjaSwiata.Interfejs;

import SymulacjaSwiata.Gra;
import SymulacjaSwiata.Swiat;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

import static SymulacjaSwiata.Konfiguracja.wielkoscKomentarza;

public class OknoProgramu extends JFrame {
    private Swiat swiat;
    private JButton przyciski[][];
    private JButton nowaTura;
    private JButton mocSpecjalna;
    private JButton Zapisz;
    private JButton Zaladuj;
    private JPanel pola;
    private JPanel panelNaKomentarz;
    private JTextArea komentarzPole;
    private Gra gra;

    public OknoProgramu(int kolumny, int rzedy, Swiat swiat, Gra gra)
    {
        super("Jakub Kisiel 180327");
        this.swiat = swiat;
        this.gra = gra;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        zainicjujKomponenty(rzedy, kolumny);
        ustawLayout(layout);
        rysujPlansze();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(gra);
        this.setVisible(true);
        this.setResizable(false);
        this.requestFocusInWindow();
        this.setFocusable(true);
        Dimension rozmiarEkranu = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) rozmiarEkranu.getWidth() / 2 - this.getSize().width / 2, 0);
        this.pack();
    }

    public void odswiez()
    {
        rysujPlansze();
        revalidate();
        repaint();
    }

    public void wyczyscKomentarz()
    {
        komentarzPole.setText("");
    }

    public void aktualizujKomentarz(ArrayList<String> komentarze)
    {
        for (String s : komentarze) {
            komentarzPole.append(s);
        }
        komentarzPole.revalidate();
    }

    public JButton getNowaTura()
    {
        return nowaTura;
    }

    public JButton getMocSpecjalna()
    {
        return mocSpecjalna;
    }

    public JButton getZapisz()
    {
        return Zapisz;
    }

    public JButton getZaladuj()
    {
        return Zaladuj;
    }

    public JPanel getPanel()
    {
        return pola;
    }

    public void odswiezKomponenty(int rzedy, int kolumny)
    {
        this.swiat = gra.getSwiat();
        pola = new JPanel();
        pola.setLayout(new GridLayout(rzedy, kolumny));
        przyciski = null;
        przyciski = new JButton[rzedy][kolumny];

        ustawPlansze();
    }

    private void zainicjujKomponenty(int rzedy, int kolumny)
    {
        pola = new JPanel();
        pola.setLayout(new GridLayout(rzedy, kolumny));
        przyciski = new JButton[rzedy][kolumny];
        ustawPlansze();
        setKomentarzPole();
        nowaTura = new JButton("Nowa tura");
        mocSpecjalna = new JButton("Moc specjalna");
        Zapisz = new JButton("Zapisz");
        Zaladuj = new JButton("Załaduj");
        nowaTura.addActionListener(swiat.getGra());
        mocSpecjalna.addActionListener(swiat.getGra());
        Zapisz.addActionListener(swiat.getGra());
        Zaladuj.addActionListener(swiat.getGra());
    }

    private void ustawLayout(GroupLayout layout)
    {
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup().addComponent(pola).
                        addGroup(layout.createSequentialGroup().addComponent(nowaTura)
                                .addComponent(mocSpecjalna)
                                .addComponent(Zapisz)
                                .addComponent(Zaladuj)
                        )
                        .addComponent(panelNaKomentarz)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(pola)
                        .addGroup(layout.createParallelGroup().addComponent(nowaTura)
                                .addComponent(mocSpecjalna)
                                .addComponent(Zapisz)
                                .addComponent(Zaladuj))
                        .addComponent(panelNaKomentarz)
        );

    }

    private void ustawPlansze()
    {
        for (int i = 0; i < swiat.getY(); i++) {
            for (int j = 0; j < swiat.getX(); j++) {
                przyciski[i][j] = new Pole("-", new Point(j, i), swiat);
                pola.add(przyciski[i][j]);
            }
        }
    }

    private void rysujPlansze()
    {
        for (int i = 0; i < swiat.getY(); i++) {
            for (int j = 0; j < swiat.getX(); j++) {
                if (swiat.getPlansza()[i][j] != null) {
                    przyciski[i][j].setText(Character.toString(swiat.getPlansza()[i][j].rysuj()));
                    przyciski[i][j].setBackground(swiat.getPlansza()[i][j].getKolor());
                } else {
                    przyciski[i][j].setText("-");
                    przyciski[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void setKomentarzPole()
    {
        komentarzPole = new JTextArea();
        komentarzPole.setEditable(false);
        panelNaKomentarz = new JPanel();
        panelNaKomentarz.setBorder(new TitledBorder(new EtchedBorder(), "Komentarz"));
        panelNaKomentarz.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(komentarzPole);
        panelNaKomentarz.add(scroll, BorderLayout.CENTER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension rozmiar = panelNaKomentarz.getSize();
        panelNaKomentarz.setMaximumSize(new Dimension(10000, wielkoscKomentarza));
        panelNaKomentarz.setMinimumSize(new Dimension(0, wielkoscKomentarza));
    }


}
