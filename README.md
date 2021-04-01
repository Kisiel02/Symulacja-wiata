# Język polski użyty w opisie oraz w kodzie spowodowany jest wymaganiami na uczelni.

### Symulacja świata
 Prosta symulacja świata w Javie (projekt na studia)  
Projekt ten wykonałem w czasie drugiego semestru studiów, czyli w pierwszej części 2020 roku  
Niektóre rozwiązania musiały być napisane

### Krótki opis projektu
Projekt ten jest prostą symulacją świata. Ma ona charakter turowej rozgrywki, gdzie możemy poruszać się strzałkami postacią człowieka posród zwierząt i roślin.

### Dokładny opis
#### Ogólne
* Każde kolorowe pole na planszy oznacza inny organizm. Organizmy przy pojawieniu się na tym samym polu kolidują ze sobą
* W każdej turze zwierzę porusza się w określony sposób, a roślina ma szansę na rozmnożenie się
* Przy kolizji zwierząt tego samego gatunku rozmnażają się one
* Aby dodać nowe zwierzę lub roślinę do gdy, należy nacisnąć dowolne puste pole i wybrać je z listy
* Wszystkie znaki organizów oraz ich kolory, a także nazwa pliku do zapisu, konfigurowalne są w pliku _Konfiguracja.java_
* Przy kolizji wygrywa zwierzę z większą siła, a słabsze ginie
* Przy równej sile atakujący staje na innej pozycji
* O kolejności akcji decyduje _inicjatywa_ (tym wieksza tym szybciej podejmie akcje dany organizm)
* Akcją zwierząt jest ruch, a roślin próba rozmożenia
* 
#### Intefejs
* Aby zapisać grę do pliku, należy wcisnąc _Zapisz_
* Aby wczytać zapisany stan gry, należy wcisnąć _Załaduj_
* Przycisk _Nowa tura_ powoduje zasymulowanie kolejne tury gry
* Przycisk _Moc specjalna_ aktywuje moc specjalna człowieka, ktorą jest poruszanie się o 2 pola naraz przez 5 tur
* Wszystkie wydarzenia z gry umieszczane są w polu tekstowym na dole
#### Poruszanie
Aby poruszyć się postacią człowieka, oznaczonego '**H**' na planszy, należy za pomocą strzałki wybrać kierunek ruchu, a następnie wcisnąć przycisk nowa tura.  
Kierunek ten zostaje również zapamiętany na przyszłe tury, dlatego nie trzeba klikać strzałki przed każdym ruchem.
