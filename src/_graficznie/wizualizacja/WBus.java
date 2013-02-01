package _graficznie.wizualizacja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Klasa WBus.java - panel reprezentujacy busa w okienku symulacji Dostaje on
 * niezbedne informacje o busie i wyswietla je w odpowiedni sposob Implementuje
 * interfejs Runnable, przez co moze sie odswiezac co konkretna wartosc czasu
 * 
 */
public class WBus extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	public int liczbaLudzi = 0;
	public int maksymalnaLiczbaLudzi = 30;
	public int nr = 0;
	public boolean bus[] = new boolean[4];

	/**
	 * Konstruktor klasy WBus
	 * 
	 * @param nr
	 *            Numer busa
	 */
	public WBus(int nr) {
		this.nr = nr;
		for (int i = 0; i < 4; i++)
			bus[i] = false;
		this.setLayout(null);
	}

	/**
	 * Metoda ta odpowiada za rysowanie na panelu busa (tlo)
	 * 
	 * @param g
	 *            Modul graficzny pakietu swing
	 */
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;

		Image img1 = Toolkit.getDefaultToolkit().getImage("bus.jpg");

		g2.drawImage(img1, 0, 0, this);
		g2.finalize();

		rysujLudzi(g);
	}

	/**
	 * Wyswietlanie na panelu ilosci ludzi na przystankach
	 * 
	 * @param g
	 *            Modul graficzny
	 */
	private void rysujLudzi(Graphics g) {
		Font font = new Font("SansSerif", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(new Color(0, 0, 0));
		g.drawString("b " + nr, 0, 11);
		String s = "";
		s += liczbaLudzi;
		font = new Font("SansSerif", Font.BOLD, 18);
		g.setFont(font);
		g.setColor(new Color(250, 150, 0));
		g.drawString(s + " / " + maksymalnaLiczbaLudzi, 8, 25);
	}

	/**
	 * Wykonywane odswiezanie panela co okreslony czas (w watku)
	 */
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(Ustawienia.CZAS_ODSWIEZANIA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
