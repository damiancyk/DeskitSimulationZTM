import java.util.LinkedList;
import java.util.Random;

import deskit.SimObject;

/**
 * Klasa Pasazer.java jest klasa reprezentujaca pojedyncza osobe stojaca na
 * przystanku i wsiadajaca do danego busa Przy tworzeniu takiego pasazera
 * okreslany jest cel, gdzie ma on jechac
 * 
 */
public class Pasazer extends SimObject {
	/**
	 * Pobierany metoda simTime() czas smulacyjny, w ktorym pasazer pojawia sie
	 * na przystanku
	 */
	double czasPojawieniaSieNaPrzstanku;
	
	public double czasZabraniaZPrzystanku;

	/**
	 * numer przystanku docelowego pasazera
	 */
	public int nrPrzystankuDocelowego;

	/**
	 * Konstruktor klasy Pasazer
	 * 
	 * @param cel
	 *            Docelowy przystanek
	 * @param Czas
	 *            Czas pojawienia sie pasazera
	 */
	public Pasazer(int cel, double Czas) {
		this.nrPrzystankuDocelowego = cel;
		czasPojawieniaSieNaPrzstanku = Czas;
	}

	/**
	 * Informacja o czasie pojawienia sie na przystanku
	 * 
	 * @return Czas
	 */
	public double getCzasPojawieniaSieNaPrzystanku() {
		return czasPojawieniaSieNaPrzstanku;
	}

	/**
	 * Czy pasazer powinien na tym przystanku wysiasc
	 * 
	 * @param przystanek
	 *            Numer przystnku, na ktorym sie teraz pasazer znajduje
	 * @return Tak, jesli wysiasc
	 */
	public boolean czyWysiasc(int przystanek) {
		if (przystanek == nrPrzystankuDocelowego)
			return true;
		return false;
	};

	/**
	 * Wybierz przystanek, na ktory chcesz jechac
	 * 
	 * @param aktualnyPrzystanek
	 *            Przystanekm na ktorym pasazer sie pojawil
	 * @return Numer docelowego, wylosowanego przystanku
	 */
	public int wybierzPrzystanek(Przystanek aktualnyPrzystanek) {
		int wylosowanyPrzystanek = 0;
		LinkedList<Integer> przystankiMozliwe = new LinkedList<Integer>();

		Random randomGenerator = new Random();

		for (int b = 0; b < aktualnyPrzystanek.odwiedzajaceBusy.size(); b++) {
			for (int i = 0; i < aktualnyPrzystanek.odwiedzajaceBusy.get(b).przystankiObslugiwane
					.size(); i++) {
				przystankiMozliwe.add(aktualnyPrzystanek.odwiedzajaceBusy
						.get(b).przystankiObslugiwane.get(i).nr);
			}
		}

		wylosowanyPrzystanek = przystankiMozliwe.get(randomGenerator
				.nextInt(przystankiMozliwe.size()));

		return wylosowanyPrzystanek;
	}

}