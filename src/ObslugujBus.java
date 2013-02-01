import deskit.SimActivity;

/**
 * Klasa ObsugujBus.java jest aktywnoscia busa, czyli sprawia, ze bus "jezdzi"
 * po przystankach, zabiera pasazerow i dowozi do celu
 * 
 */
public class ObslugujBus extends SimActivity {
	private Bus bus;

	public ObslugujBus() {
	}

	/**
	 * Metoda odziedziczona po SimActivity, ktora startuje po odwolaniu sie do
	 * instancji obiektu tej klasy
	 */
	public void action() {
		bus = (Bus) getParentSimObject();

		/*
		 * obsluguj kursy
		 */
		while (true) {
			// podjezdzamy na przystanek
			Przystanek aktualnyPrzystanek = bus.przystankiObslugiwane
					.removeFirst();
			aktualnyPrzystanek.wPrzystanek.bus[bus.nr] = true;

			int iluWysiadlo = 0;
			// pasazerowie wysiadaja
			for (int i = 0; i < bus.pasazerowieWTymBusie.size(); i++) {
				if (bus.pasazerowieWTymBusie.get(i).czyWysiasc(
						aktualnyPrzystanek.nr)) {
					bus.czasPrzejazduPasazera
							.setValue(simTime()
									- bus.pasazerowieWTymBusie.get(i).czasZabraniaZPrzystanku);
					bus.usunPasazera(i);

					iluWysiadlo++;

				}

			}
			waitDuration(iluWysiadlo);

			int iluWsiadlo = 0;
			// pasazerowie wsiadaja z przystanku
			for (int i = 0; i < aktualnyPrzystanek.pasazerowieNaTymPrzystanku
					.size(); i++) {
				if ((bus.czyJedzieNaTenPrzystanek(aktualnyPrzystanek.pasazerowieNaTymPrzystanku
						.get(i).nrPrzystankuDocelowego))
						&& (bus.czySaWolneMiejsca())) {
					Pasazer wsiadajacyPasazer = aktualnyPrzystanek
							.usunPasazera(i);
					wsiadajacyPasazer.czasZabraniaZPrzystanku = simTime();
					bus.dodajPasazera(wsiadajacyPasazer);
					iluWsiadlo++;
				}

			}
			waitDuration(iluWsiadlo);

			// obsluzony przystanek przechodzi na koniec kolejki
			bus.przystankiObslugiwane.addLast(aktualnyPrzystanek);

			aktualnyPrzystanek.wPrzystanek.bus[bus.nr] = false;
			waitDuration(bus.czasPrzejazdu(aktualnyPrzystanek.nr));
			if (isStopped() || isInterrupted())
				break;
		}
	}

}