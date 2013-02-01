import java.util.LinkedList;

import _graficznie.wizualizacja.Ustawienia;
import _graficznie.wizualizacja.WPrzystanek;

import deskit.SimActivity;
import deskit.SimObject;
import deskit.monitors.MonitoredVar;

/**
 * Klasa Przystanek.java - reprezentuje przystanek w symulacji, posiada dane o
 * natezeniu pasazerow, ktorzy z niego korzystaja, liste pasazerow stojacych na
 * nim oraz zmienne minitorowane dotyczace ludzi korzystajacych z danego
 * przystanku
 * 
 */
public class Przystanek extends SimObject {
	/**
	 * Unikalny numer przystanku
	 */
	public int nr;

	/**
	 * Lista pasazerow znajdujacych sie na danym przystanku
	 */
	public LinkedList<Pasazer> pasazerowieNaTymPrzystanku;

	/**
	 * Lista busow, ktore odwiedzaja dany przystanek
	 */
	public LinkedList<Bus> odwiedzajaceBusy;

	/**
	 * Aktywnosc dla obiektu przystanku, ktora generuje pasazerow
	 */
	public SimActivity generujPasazerow;

	/**
	 * Grafizny panel reprezentujacy dany przystanek
	 */
	WPrzystanek wPrzystanek;

	/**
	 * Natezenie ruchu, unikatowe dla kazdego przystanku
	 */
	public float natezenieRuchu = 1;

	/**
	 * Pora dnia, ktora dodatkowo zmienia natezenie na przystankach
	 */
	public float poraDnia = 1;

	/**
	 * Monitorowanie pasazerow "przewijajacych sie" przez dany przystanek
	 */
	public MonitoredVar zapelnieniePrzystanku;
	public MonitoredVar czasOczekiwaniaPasazeraNaBusa;

	/**
	 * Konstruktor klasy Przystanek
	 * 
	 * @param nr
	 *            Unikalny numer
	 * @param wPrzystanek
	 *            Graficzny panel przystnku
	 * @param ustawienia
	 *            Ustawienia symulacji
	 */
	public Przystanek(int nr, WPrzystanek wPrzystanek, Ustawienia ustawienia) {
		this.nr = nr;
		this.wPrzystanek = wPrzystanek;

		pasazerowieNaTymPrzystanku = new LinkedList<Pasazer>();
		odwiedzajaceBusy = new LinkedList<Bus>();
		generujPasazerow = new GenerujPasazerow();
		zapelnieniePrzystanku = new MonitoredVar(this);
		czasOczekiwaniaPasazeraNaBusa = new MonitoredVar(this);

		switch (ustawienia.poraDnia) {
		case 0:
			poraDnia = .5f;
			break;
		case 1:
			poraDnia = 1.f;
			break;
		case 2:
			poraDnia = 1.8f;
			break;
		default:
			break;
		}
	}

	/**
	 * Dodanei pasazera do przystanku
	 * 
	 * @param p
	 *            Obiekt pasazera
	 * @return Liczbe pasazerow po dodaniu kolejnego
	 */
	public int dodajPasazera(Pasazer p) {
		pasazerowieNaTymPrzystanku.add(p);
		zapelnieniePrzystanku.setValue(liczbaPasazerow());
		return pasazerowieNaTymPrzystanku.size();
	}

	/**
	 * Usuniecie konkretnego pasazera z przystanku
	 * 
	 * @param i
	 *            Numer usuwanego pasazera
	 * @return Obiekt usuwanego pasazera
	 */
	public Pasazer usunPasazera(int i) {
		Pasazer pasazer = null;
		pasazer = (Pasazer) pasazerowieNaTymPrzystanku.remove(i);
		zapelnieniePrzystanku.setValue(liczbaPasazerow());
		czasOczekiwaniaPasazeraNaBusa.setValue(generujPasazerow.simTime()
				- pasazer.getCzasPojawieniaSieNaPrzystanku());
		return pasazer;
	}

	/**
	 * Informuje o liczbie pasazerow na danym przystanku
	 * 
	 * @return Liczba pasazerow
	 */
	public int liczbaPasazerow() {
		return pasazerowieNaTymPrzystanku.size();
	}

}