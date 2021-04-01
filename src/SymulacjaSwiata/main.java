package SymulacjaSwiata;
import SymulacjaSwiata.Zwierzeta.*;

import java.awt.*;
import java.util.Random;

import static SymulacjaSwiata.Konfiguracja.*;

public class main {
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                Gra gra = new Gra(rozmiarX, rozmiarY);

                Random generator = new Random();
                int x = generator.nextInt(rozmiarX);
                int y = generator.nextInt(rozmiarY);
                Czlowiek czlowiek = new Czlowiek(new Point(x, y), gra);
                for (int i = 0; i < rozmiarY;  i++)
                    for (int j = 0; j < rozmiarX;  j++)
                    {
                        if(!(i == y && j == x)) //nie na miejscu człowieka
                        {
                            int losowa = generator.nextInt(10);

                            if(losowa == 0)
                            {
                                Point punkt = new Point(j,i);
                                String klasa = wybor[generator.nextInt(wybor.length)];
                                gra.getGeneratorObiektow().generujOrganizm(klasa,punkt);
                                System.out.println(wybor.length);
                            }
                        }
                    }

                gra.odswiezSwiat();
            }
        });
    }
}
