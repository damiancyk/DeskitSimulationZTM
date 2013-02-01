package _graficznie.wizualizacja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Klasa Przystanek.java - panel reprezentujacy przystanek w oknie symulacji
 * 
 */
public class WPrzystanek extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	public int liczbaLudzi = 0;
	public int nr = 0;
	private WOkno okno;

	public boolean bus[] = new boolean[8];

	public WPrzystanek(int nr, WOkno okno) {
		this.okno = okno;
		this.nr = nr;
		for (int i = 0; i < 8; i++)
			bus[i] = false;
		this.setLayout(null);
	}

	/**
	 * Glowna metoda rysujaca, wykonywana przy kazdym odswiezaniu sie okna
	 * Rysowanie tla przystanku i wywolanie innych metod rysujacych
	 * 
	 * @param g
	 *            Modul graficzny
	 */
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;

		Image img1 = Toolkit.getDefaultToolkit().getImage("przystanek.gif");

		g2.drawImage(img1, 0, 0, this);
		g2.finalize();

		Font font = new Font("SansSerif", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("" + okno.ustawienia.natezenieRuchu[nr], 16, 22);

		rysujLudzi(g);
		rysujNumeryBusow(g);
	}

	/**
	 * Wyswietlanie ilosci ludzi na danym przystanku
	 * 
	 * @param g
	 *            Modul graficzny
	 */
	private void rysujLudzi(Graphics g) {
		Font font = new Font("SansSerif", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(new Color(0, 0, 0));
		g.drawString("p " + nr, 10, 11);
		String s = "";
		s += liczbaLudzi;
		font = new Font("SansSerif", Font.BOLD, 23);
		g.setFont(font);
		g.setColor(new Color(250, 250, 0));
		g.drawString(s, 12, 48);
	}

	/**
	 * Wyswietlanie numeru busa
	 * 
	 * @param g
	 *            Modul graficzny
	 */
	private void rysujNumeryBusow(Graphics g) {
		Font font = new Font("SansSerif", Font.BOLD, 20);
		g.setFont(font);
		Color cGray = new Color(100, 100, 100);
		Color cRed = new Color(250, 0, 0);
		g.setColor(cGray);

		switch (this.nr) {
		case 0:
			if (bus[0])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 0, 13, 67);
			break;
		case 1:
			if (bus[0])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 0, 5, 67);
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 22, 67);
			break;
		case 2:
			if (bus[0])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 0, 0, 67);
			if (bus[1])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 1, 10, 67);
			if (bus[2])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 2, 20, 67);
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 30, 67);
			break;
		case 3:
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 13, 67);
			break;
		case 4:
			if (bus[1])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 1, 5, 67);
			if (bus[2])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 2, 15, 67);
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 25, 67);
			break;
		case 5:
			if (bus[1])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 1, 13, 67);
			break;
		case 6:
			if (bus[2])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 2, 5, 67);
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 22, 67);
			break;
		case 7:
			if (bus[2])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 2, 13, 67);
			break;
		case 8:
			if (bus[0])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 0, 5, 67);
			if (bus[2])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 2, 15, 67);
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 25, 67);
			break;
		case 9:
			if (bus[0])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 0, 5, 67);
			if (bus[3])
				g.setColor(cRed);
			else
				g.setColor(cGray);
			g.drawString("" + 3, 22, 67);
			break;
		default:
			break;
		}
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
