import java.util.LinkedList;

import _graficznie.wizualizacja.Ustawienia;
import _graficznie.wizualizacja.WBus;

import deskit.SimActivity;
import deskit.SimObject;
import deskit.monitors.MonitoredVar;

/**
 * Bus.java - klasa reprezentujaca busa zawiera ona dane o typie busa, liste
 * pasazerow oraz obslugiwanych przystankow
 * 
 */
public class Bus extends SimObject {
	enum TypBusa {
		/** predkosc 10m/s */
		E_NORMALNY,
		/** predkosc 15m/s, ilosc miejsc + 15 */
		E_EXPRESS
	}

	/**
	 * Lista pasazerow busa
	 */
	public LinkedList<Pasazer> pasazerowieWTymBusie;

	/**
	 * Lista obslugiwanych przystankow, przegladana w rotacyjny sposob
	 */
	public LinkedList<Przystanek> przystankiObslugiwane;

	/**
	 * Unikalny numer busa
	 */
	public int nr;

	/**
	 * Typ busa, mamy dwa do wyboru
	 */
	public TypBusa typ;

	/**
	 * Obsugiwanie pasazerow, czyli zabieranie ich i wysadzanie oraz
	 * przejezdzanie od przystanku do przystanku, aktywnosc reprezentowana jest
	 * przez klase ObslugujBus dziedziczaca od SimActivity
	 */
	public SimActivity obslugujPasazerow;

	/**
	 * Ilosc miejsc w Busie, zalezna od typu busa
	 */
	static public int iloscMiejsc = 30;

	/**
	 * Predkosc busa, zalezna od jego typu
	 */
	private int predkoscMetrowNaSekunde = 10;

	/**
	 * Graficzny panel reprezentujacy busa
	 */
	public WBus wBus;

	/**
	 * Ustawienia symulacji
	 */
	private Ustawienia ustawienia;

	/**
	 * Zmienne monitorowane dla pasazerow wsiadajacych i wysiadajacych
	 */
	public MonitoredVar czasPrzejazduPasazera;
	public MonitoredVar zapelnienieBusa;

	/**
	 * Domyslny konstruktor klasy Bus
	 */
	public Bus() {
		pasazerowieWTymBusie = new LinkedList<Pasazer>();
		przystankiObslugiwane = new LinkedList<Przystanek>();
		obslugujPasazerow = new ObslugujBus();
		czasPrzejazduPasazera = new MonitoredVar(this);
		zapelnienieBusa = new MonitoredVar(this);
	}

	/**
	 * Konstruktor klasy Bus, ktory dostarcza dodatkowe parametry
	 * 
	 * @param nr
	 *            Unikalny numer
	 * @param typ
	 *            Typ, dwa do wyboru
	 * @param wBus
	 *            Graficzne okienko busa
	 * @param ustawienia
	 *            Ustawienia symulacji
	 */
	public Bus(int nr, TypBusa typ, WBus wBus, Ustawienia ustawienia) {
		this();
		this.nr = nr;
		this.typ = typ;
		this.wBus = wBus;
		this.ustawienia = ustawienia;
		ustawTypBusa(typ);
	}

	/**
	 * Dodajemy pasazera do busa
	 * 
	 * @param p
	 *            Pasazer
	 * @return Ilosc pasazerow po dodaniu pasazera
	 */
	public int dodajPasazera(Pasazer p) {
		pasazerowieWTymBusie.add(p);
		// wBus.repaint();
		wBus.liczbaLudzi = liczbaPasazerow();
		zapelnienieBusa.setValue(pasazerowieWTymBusie.size());
		return pasazerowieWTymBusie.size();
	}

	/**
	 * Usuwamy pasazera z busa
	 * 
	 * @param i
	 *            Numer pasazera
	 * @return Usuwany pasazer
	 */
	public Pasazer usunPasazera(int i) {
		Pasazer pasazer = null;
		pasazer = (Pasazer) pasazerowieWTymBusie.remove(i);

		wBus.liczbaLudzi = liczbaPasazerow();
		// wBus.repaint();
		zapelnienieBusa.setValue(pasazerowieWTymBusie.size());

		return pasazer;
	}

	/**
	 * Dodajemy do listy obslugiwanych przystankow nowy przystanek
	 * 
	 * @param prz
	 *            Przystanek
	 * @return
	 */
	public int dodajPrzystanek(Przystanek prz) {
		przystankiObslugiwane.add(prz);
		return przystankiObslugiwane.size();
	}

	/**
	 * Zwraca liczbe pasazerow busa
	 * 
	 * @return Liczba pasazerow
	 */
	public int liczbaPasazerow() {
		return pasazerowieWTymBusie.size();
	}

	/**
	 * Zwraca liczbe obslugiwanych przystankow busa
	 * 
	 * @return Liczba przystankow
	 */
	public int liczbaPrzystankow() {
		return przystankiObslugiwane.size();
	}

	/**
	 * Sprawdza, czy sa wolne miejsca w busie
	 * 
	 * @return Prawda, jesli sa wolne miejsca
	 */
	public boolean czySaWolneMiejsca() {
		if (pasazerowieWTymBusie.size() < iloscMiejsc) {
			return true;
		}
		return false;
	}

	/**
	 * Sprawdza, czy bus jedzie na dany przystanek
	 * 
	 * @param gdzie
	 *            Numer przystanku
	 * @return Tak, jesli jedzie
	 */
	public boolean czyJedzieNaTenPrzystanek(int gdzie) {
		boolean czyJedzie = false;
		for (int i = 0; i < przystankiObslugiwane.size(); i++) {
			if (gdzie == przystankiObslugiwane.get(i).nr) {
				czyJedzie = true;
			}
		}
		return czyJedzie;
	}

	/**
	 * Zwraca czas przejazdu na danym odcinku trasy Czas obliczany jest za
	 * pomoca fizycznego wzoru na predkosc
	 * 
	 * @param aktualnyPrzystanek
	 *            Aktualny przystanek
	 * @return Czas przejazdu
	 */
	public int czasPrzejazdu(int aktualnyPrzystanek) {
		return (ustawienia.dlugoscTrasy(nr, aktualnyPrzystanek) / predkoscMetrowNaSekunde);
	}

	/**
	 * ustawia typ busa i jego parametry
	 * 
	 * @param typ
	 *            TYp busa (normalny albo expres)
	 */
	public void ustawTypBusa(Bus.TypBusa typ) {
		this.typ = typ;
		switch (typ) {
		case E_NORMALNY:
			iloscMiejsc = 30;
			predkoscMetrowNaSekunde = 10;
			wBus.maksymalnaLiczbaLudzi = 30;
			break;
		case E_EXPRESS:
			iloscMiejsc = 45;
			predkoscMetrowNaSekunde = 15;
			wBus.maksymalnaLiczbaLudzi = 45;
			break;

		}
	}

}
