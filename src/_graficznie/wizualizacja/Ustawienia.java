package _graficznie.wizualizacja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Klasa Ustawienia.java skupia kluczowe ustawienia symulacji w jednym miejscu,
 * ladujac je z pliku zewnetrznego, Jesli zaladowanie sie nie powiedzie, ustawia
 * domyslne arametry
 * 
 */
public class Ustawienia {
	/**
	 * Czas odswiezania poszczegolnych okienek busow i przystankow w graficznym
	 * oknie
	 */
	public static final int CZAS_ODSWIEZANIA = 300;

	public int natezenieRuchu[] = new int[10];

	/**
	 * Dlugosci tras dla danych busow Pierwszy parametr to numer trasy, drugi to
	 * jej dlugosc wczytana z pliku
	 */
	public HashMap<Integer, Integer> b0 = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> b1 = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> b2 = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> b3 = new HashMap<Integer, Integer>();

	/**
	 * Ilosc busow na kazda trase
	 */
	public int iloscBusow[] = new int[4];

	/**
	 * Ilosc expresow na kazda trase
	 */
	public int express[] = new int[4];

	/**
	 * Pora dnia symulacji
	 */
	public int poraDnia;

	public Ustawienia() throws IOException {
		// stale dla ustawien, w razie zlego odczytu z pliku
		for (int i = 0; i < 4; i++) {
			iloscBusow[i] = 1;
			express[i] = 0;
		}
		poraDnia = 1;

		natezenieRuchu[0] = 1;
		natezenieRuchu[1] = 2;
		natezenieRuchu[2] = 4;
		natezenieRuchu[3] = 1;
		natezenieRuchu[4] = 3;
		natezenieRuchu[5] = 1;
		natezenieRuchu[6] = 2;
		natezenieRuchu[7] = 1;
		natezenieRuchu[8] = 3;
		natezenieRuchu[9] = 2;

		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader("parametry.txt"));

		String line = null;
		int nrLinii = 0;
		while ((line = bufferedReader.readLine()) != null) {

			// Kazda linia w pliku z ustawieniami odpowiada za inne ustawienia
			switch (nrLinii) {
			case 0:
				b0.put(0, Integer.parseInt(line.substring(0, 3)));
				break;
			case 1:
				b0.put(1, Integer.parseInt(line.substring(0, 3)));
				break;
			case 2:
				b0.put(2, Integer.parseInt(line.substring(0, 3)));
				break;
			case 3:
				b0.put(8, Integer.parseInt(line.substring(0, 3)));
				break;
			case 4:
				b0.put(9, Integer.parseInt(line.substring(0, 3)));
				break;
			case 5:
				b1.put(5, Integer.parseInt(line.substring(0, 3)));
				break;
			case 6:
				b1.put(2, Integer.parseInt(line.substring(0, 3)));
				break;
			case 7:
				b1.put(4, Integer.parseInt(line.substring(0, 3)));
				break;
			case 8:
				b2.put(2, Integer.parseInt(line.substring(0, 3)));
				break;
			case 9:
				b2.put(4, Integer.parseInt(line.substring(0, 3)));
				break;
			case 10:
				b2.put(7, Integer.parseInt(line.substring(0, 3)));
				break;
			case 11:
				b2.put(6, Integer.parseInt(line.substring(0, 3)));
				break;
			case 12:
				b2.put(8, Integer.parseInt(line.substring(0, 3)));
				break;
			case 13:
				b3.put(1, Integer.parseInt(line.substring(0, 3)));
				break;
			case 14:
				b3.put(3, Integer.parseInt(line.substring(0, 3)));
				break;
			case 15:
				b3.put(4, Integer.parseInt(line.substring(0, 3)));
				break;
			case 16:
				b3.put(6, Integer.parseInt(line.substring(0, 3)));
				break;
			case 17:
				b3.put(9, Integer.parseInt(line.substring(0, 3)));
				break;
			case 18:
				b3.put(8, Integer.parseInt(line.substring(0, 3)));
				break;
			case 19:
				b3.put(2, Integer.parseInt(line.substring(0, 3)));
				break;
			case 20:
				iloscBusow[0] = Integer.parseInt(line.substring(0, 1));
				break;
			case 21:
				iloscBusow[1] = Integer.parseInt(line.substring(0, 1));
				break;
			case 22:
				iloscBusow[2] = Integer.parseInt(line.substring(0, 1));
				break;
			case 23:
				iloscBusow[3] = Integer.parseInt(line.substring(0, 1));
				break;
			case 24:
				express[0] = Integer.parseInt(line.substring(0, 1));
				break;
			case 25:
				express[1] = Integer.parseInt(line.substring(0, 1));
				break;
			case 26:
				express[2] = Integer.parseInt(line.substring(0, 1));
				break;
			case 27:
				express[3] = Integer.parseInt(line.substring(0, 1));
				break;
			case 28:
				poraDnia = Integer.parseInt(line.substring(0, 1));
				break;
			default:
				break;
			}

			nrLinii++;
		}
	}

	/**
	 * Przypisanie "na sztywno" dlugosci tras
	 */
	public void przypiszParametryKodem() {
		b0.put(0, 500);
		b0.put(1, 250);
		b0.put(2, 300);
		b0.put(8, 300);
		b0.put(9, 300);

		b1.put(5, 500);
		b1.put(2, 350);
		b1.put(4, 300);

		b2.put(2, 350);
		b2.put(4, 350);
		b2.put(7, 300);
		b2.put(6, 400);
		b2.put(8, 300);

		b3.put(1, 300);
		b3.put(3, 300);
		b3.put(4, 300);
		b3.put(6, 200);
		b3.put(9, 300);
		b3.put(8, 300);
		b3.put(2, 250);
	}

	/**
	 * Zwraca dlugosc danej trasy
	 * 
	 * @param nrBusa
	 *            Konkretny bus
	 * @param aktualnyPrzystanek
	 *            Gdzie dany bus sie znajduje
	 * @return Dlugosc nastepnego odcinka trasy
	 */
	public int dlugoscTrasy(int nrBusa, int aktualnyPrzystanek) {
		int dlugoscTrasy = 300;
		switch (nrBusa) {
		case 0:
			dlugoscTrasy = b0.get(aktualnyPrzystanek);
			break;
		case 1:
			dlugoscTrasy = b1.get(aktualnyPrzystanek);
			break;
		case 2:
			dlugoscTrasy = b2.get(aktualnyPrzystanek);
			break;
		case 3:
			dlugoscTrasy = b3.get(aktualnyPrzystanek);
			break;
		default:
			break;
		}

		return dlugoscTrasy;
	}

}
