import java.util.LinkedList;
import java.util.Random;

import deskit.SimActivity;

/**
 * Klasa GenerujPasazerow.java generuje co jakis czas pasazera na danym
 * przystanku
 * 
 */
public class GenerujPasazerow extends SimActivity {
	private Przystanek przystanek;

	public GenerujPasazerow() {
	}

	public void action() {
		przystanek = (Przystanek) getParentSimObject();

		/**
		 * Generowanie pasazerow w nieskonczonej petli
		 */
		while (true) {
			int przystanekDocelowy = 0;
			LinkedList<Integer> pp = new LinkedList<Integer>();
			/**
			 * wybranie dla pasazera mozliwego przystanku docelowego
			 */
			switch (przystanek.nr) {
			case 0:
				pp.add(1);
				pp.add(2);
				pp.add(8);
				pp.add(9);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 1:
				pp.add(0);
				pp.add(2);
				pp.add(3);
				pp.add(4);
				pp.add(6);
				pp.add(9);
				pp.add(8);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 2:
				pp.add(0);
				pp.add(1);
				pp.add(3);
				pp.add(4);
				pp.add(5);
				pp.add(6);
				pp.add(7);
				pp.add(8);
				pp.add(9);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 3:
				pp.add(1);
				pp.add(2);
				pp.add(4);
				pp.add(6);
				pp.add(8);
				pp.add(9);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 4:
				pp.add(1);
				pp.add(2);
				pp.add(3);
				pp.add(6);
				pp.add(8);
				pp.add(9);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 5:
				pp.add(2);
				pp.add(4);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 6:
				pp.add(1);
				pp.add(2);
				pp.add(3);
				pp.add(4);
				pp.add(7);
				pp.add(8);
				pp.add(9);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 7:
				pp.add(2);
				pp.add(4);
				pp.add(6);
				pp.add(8);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 8:
				pp.add(0);
				pp.add(1);
				pp.add(2);
				pp.add(3);
				pp.add(4);
				pp.add(6);
				pp.add(7);
				pp.add(9);
				przystanekDocelowy = losowyCel(pp);
				break;
			case 9:
				pp.add(0);
				pp.add(1);
				pp.add(2);
				pp.add(3);
				pp.add(4);
				pp.add(6);
				pp.add(8);
				przystanekDocelowy = losowyCel(pp);
				break;
			default:
				break;
			}

			/**
			 * Dodanie pasazera do danego przystanku
			 */
			przystanek
					.dodajPasazera(new Pasazer(przystanekDocelowy, simTime()));
			przystanek.wPrzystanek.liczbaLudzi = przystanek.pasazerowieNaTymPrzystanku
					.size();

			// przystanek.wPrzystanek.repaint();

			/**
			 * Co jaki czas dodajemy nowego pasazera, zalezne od natezenia ruchu
			 * i pory dnia
			 */
			waitDuration(100 / (przystanek.natezenieRuchu * przystanek.poraDnia));
			if (isStopped() || isInterrupted())
				break;
		}
	}

	/**
	 * Losowy cel dla pasazera
	 * 
	 * @param pp
	 *            Przystanek
	 * @return Numer docelowego przystanku
	 */
	private int losowyCel(LinkedList<Integer> pp) {
		Random randomGenerator = new Random();
		return pp.get(randomGenerator.nextInt(pp.size()));
	}

}