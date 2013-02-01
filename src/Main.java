/**

przystanki: 0,1,2,3,4,5,6,7,8,9
bus: 
0: 0-1-2-9-0
1: 2-3-8-4-1
2: 1-3-5-7-1
3: 0-3-6-2-0
4: 5-7-9-4-5

 */

/**
 co w takiej wizualizacji ma zostaæ uchwycone?
 czy przystanki moga tworzyc token ring?
 jak spowolnic symulacje? 
 je¿eli na danym przystanku jest za du¿o ludzi, to trzeba dodaæ kolejnego busa czy jakos inaczej zmieniac symulacje?
 czy to mo¿e byæ tekstowo, np nad lini¹ napis typu: busy jad¹ce t¹ lini¹:1,2,3,..
 */

import java.io.IOException;
import java.util.Date;

import _graficznie.wizualizacja.Monitor;
import _graficznie.wizualizacja.Ustawienia;
import _graficznie.wizualizacja.WOkno;

import deskit.SimActivity;
import deskit.SimManager;

/**
 * Glowna klasa symulacji, tutaj tworzymy niezbedne obiekty, ladujemy
 * ustawienia, tworzymy graficzna reprezentacje symulacji
 * 
 */
public class Main {
	/**
	 * Metoda, od ktorej zaczynamy wykonywanie kodu
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Ustawienia ustawienia = null;

		/**
		 * Proba stworzenia obiektu ustawien, co wize sie z zaladowaniem danych
		 * z pliku
		 */
		try {
			ustawienia = new Ustawienia();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * Stworzenie obiektu skupiajacego zmienne monitorowane
		 */
		Monitor monitor = new Monitor();

		/**
		 * stworzenie graficznego okna symulacji
		 */
		WOkno okno = new WOkno(monitor, ustawienia);
		okno.setVisible(true);

		/**
		 * Tworzymy przystanki
		 */
		Przystanek przystanek[] = new Przystanek[10];
		for (int i = 0; i < 10; i++)
			przystanek[i] = new Przystanek(i, okno.przystanek.get(i),
					ustawienia);

		Bus bus[] = new Bus[8];
		for (int i = 0; i < 8; i++)
			bus[i] = new Bus(i, Bus.TypBusa.E_NORMALNY, okno.wBus[i],
					ustawienia);

		for (int i = 0; i < 4; i++) {
			if (ustawienia.express[i] == 1) {
				bus[i].ustawTypBusa(Bus.TypBusa.E_EXPRESS);
			} else if (ustawienia.express[i] == 2) {
				bus[i].ustawTypBusa(Bus.TypBusa.E_EXPRESS);
				bus[i + 4].ustawTypBusa(Bus.TypBusa.E_EXPRESS);
			}
		}

		/**
		 * Przypisanie zmiennych monitorowanych
		 */
		for (int i = 0; i < 4; i++) {
			monitor.czasPrzejazduPasazera[i] = bus[i].czasPrzejazduPasazera;
			monitor.zapelnienieBusa[i] = bus[i].zapelnienieBusa;
		}

		for (int i = 0; i < 10; i++) {
			monitor.zapelnieniePrzystankow[i] = przystanek[i].zapelnieniePrzystanku;
			monitor.czasOczekiwaniaPasazeraNaBusa[i] = przystanek[i].czasOczekiwaniaPasazeraNaBusa;
		}

		przystanek[0].odwiedzajaceBusy.add(bus[0]);

		przystanek[1].odwiedzajaceBusy.add(bus[0]);
		przystanek[1].odwiedzajaceBusy.add(bus[3]);

		przystanek[2].odwiedzajaceBusy.add(bus[0]);
		przystanek[2].odwiedzajaceBusy.add(bus[1]);
		przystanek[2].odwiedzajaceBusy.add(bus[2]);
		przystanek[2].odwiedzajaceBusy.add(bus[3]);

		przystanek[3].odwiedzajaceBusy.add(bus[3]);

		przystanek[4].odwiedzajaceBusy.add(bus[1]);
		przystanek[4].odwiedzajaceBusy.add(bus[2]);
		przystanek[4].odwiedzajaceBusy.add(bus[3]);

		przystanek[5].odwiedzajaceBusy.add(bus[1]);

		przystanek[6].odwiedzajaceBusy.add(bus[2]);
		przystanek[6].odwiedzajaceBusy.add(bus[3]);

		przystanek[7].odwiedzajaceBusy.add(bus[2]);

		przystanek[8].odwiedzajaceBusy.add(bus[0]);
		przystanek[8].odwiedzajaceBusy.add(bus[2]);
		przystanek[8].odwiedzajaceBusy.add(bus[3]);

		przystanek[9].odwiedzajaceBusy.add(bus[0]);
		przystanek[9].odwiedzajaceBusy.add(bus[3]);

		for (int i = 0; i < 10; i++) {
			przystanek[i].natezenieRuchu = ustawienia.natezenieRuchu[i];
		}

		bus[0].przystankiObslugiwane.add(przystanek[0]);
		bus[0].przystankiObslugiwane.add(przystanek[1]);
		bus[0].przystankiObslugiwane.add(przystanek[2]);
		bus[0].przystankiObslugiwane.add(przystanek[8]);
		bus[0].przystankiObslugiwane.add(przystanek[9]);

		bus[4].przystankiObslugiwane.add(przystanek[0]);
		bus[4].przystankiObslugiwane.add(przystanek[1]);
		bus[4].przystankiObslugiwane.add(przystanek[2]);
		bus[4].przystankiObslugiwane.add(przystanek[8]);
		bus[4].przystankiObslugiwane.add(przystanek[9]);

		bus[1].przystankiObslugiwane.add(przystanek[2]);
		bus[1].przystankiObslugiwane.add(przystanek[4]);
		bus[1].przystankiObslugiwane.add(przystanek[5]);

		bus[5].przystankiObslugiwane.add(przystanek[2]);
		bus[5].przystankiObslugiwane.add(przystanek[4]);
		bus[5].przystankiObslugiwane.add(przystanek[5]);

		bus[2].przystankiObslugiwane.add(przystanek[2]);
		bus[2].przystankiObslugiwane.add(przystanek[4]);
		bus[2].przystankiObslugiwane.add(przystanek[7]);
		bus[2].przystankiObslugiwane.add(przystanek[6]);
		bus[2].przystankiObslugiwane.add(przystanek[8]);

		bus[6].przystankiObslugiwane.add(przystanek[2]);
		bus[6].przystankiObslugiwane.add(przystanek[4]);
		bus[6].przystankiObslugiwane.add(przystanek[7]);
		bus[6].przystankiObslugiwane.add(przystanek[6]);
		bus[6].przystankiObslugiwane.add(przystanek[8]);

		bus[3].przystankiObslugiwane.add(przystanek[1]);
		bus[3].przystankiObslugiwane.add(przystanek[3]);
		bus[3].przystankiObslugiwane.add(przystanek[4]);
		bus[3].przystankiObslugiwane.add(przystanek[6]);
		bus[3].przystankiObslugiwane.add(przystanek[9]);
		bus[3].przystankiObslugiwane.add(przystanek[8]);
		bus[3].przystankiObslugiwane.add(przystanek[2]);

		bus[7].przystankiObslugiwane.add(przystanek[1]);
		bus[7].przystankiObslugiwane.add(przystanek[3]);
		bus[7].przystankiObslugiwane.add(przystanek[4]);
		bus[7].przystankiObslugiwane.add(przystanek[6]);
		bus[7].przystankiObslugiwane.add(przystanek[9]);
		bus[7].przystankiObslugiwane.add(przystanek[8]);
		bus[7].przystankiObslugiwane.add(przystanek[2]);

		for (int i = 0; i < 10; i++)
			SimActivity.callActivity(przystanek[i],
					przystanek[i].generujPasazerow);
		for (int i = 0; i < 4; i++)
			SimActivity.callActivity(bus[i], bus[i].obslugujPasazerow);

		for (int i = 0; i < 4; i++) {
			if (ustawienia.iloscBusow[i] == 2)
				SimActivity.callActivity(bus[i + 4],
						bus[i + 4].obslugujPasazerow);
		}

		/**
		 * Warunek koñca symulacji
		 */
		SimManager.getSimManager().setStopTime(86400);

		/**
		 * Badanie czasu trwania eksperymentu - pocz¹tek
		 */
		long czst = new Date().getTime();

		/**
		 * Uruchomienie symulacji za poœrednictwem metody "start" z
		 * "Application"
		 **/
		SimManager.getSimManager().startSimulation();

		/**
		 * Badanie czasu trwania eksperymentu - koniec
		 */
		czst = new Date().getTime() - czst;

		/**
		 * Wyniki
		 */
		System.out.println("Czas trwania eksperymentu : " + czst);
	}
}
