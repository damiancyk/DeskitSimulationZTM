package _graficznie.wizualizacja;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Klasa WMapa.java - klasa reprezentujaca panel, na ktorym rysowane sa panele
 * busow i przystankow Tutaj rysujemy linie tras oraz tlo, "plan miasta"
 * 
 */
public class WMapa extends JPanel {
	private static final long serialVersionUID = 1L;
	HashMap<Integer, Line2D> line = new HashMap<Integer, Line2D>();
	Ustawienia ustawienia;

	public WMapa(Ustawienia ustawienia) {
		this.setBackground(new Color(100, 100, 100));
		line.put(1, new Line2D.Float(0, 0, 100, 100));
		this.ustawienia = ustawienia;
	}

	/**
	 * Rysowanie linii tras i tla oraz dodatkowych informacji o liczbach
	 * wyswietlanych w symulacji, metoda wywolywana przy kazdym odswiezeniu sie
	 * panelu
	 */
	public void paint(Graphics g) {
		g.setColor(new Color(100, 150, 100));
		g.clearRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;

		Image img1 = Toolkit.getDefaultToolkit().getImage("plan.jpg");

		g2.drawImage(img1, 0, 0, this);
		g2.finalize();

		// 0-1
		g.setColor(new Color(0, 0, 0));
		g.drawLine(40, 10, 170, 90);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b0.get(0) + "", 130, 65);

		// 1-3
		g.setColor(new Color(0, 0, 0));
		g.drawLine(170, 110, 320, 110);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(1) + "", 260, 105);

		// 1-2
		g.setColor(new Color(0, 0, 0));
		g.drawLine(190, 90, 190, 250);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(2) + "", 165, 195);

		// 3-4
		g.setColor(new Color(0, 0, 0));
		g.drawLine(330, 90, 370, 250);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(3) + "", 335, 210);

		// 2-4
		g.setColor(new Color(0, 0, 0));
		g.drawLine(170, 270, 360, 270);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b1.get(2) + "", 280, 265);

		// 2-5
		g.setColor(new Color(0, 0, 0));
		g.drawLine(170, 270, 10, 270);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b1.get(5) + "", 105, 265);

		// 2-8
		g.setColor(new Color(0, 0, 0));
		g.drawLine(190, 250, 140, 410);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(8) + "", 130, 365);

		// 4-6
		g.setColor(new Color(0, 0, 0));
		g.drawLine(380, 290, 340, 410);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(4) + "", 332, 365);

		// 8-6
		g.setColor(new Color(0, 0, 0));
		g.drawLine(120, 430, 320, 430);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b2.get(6) + "", 235, 428);

		// 6-7
		g.setColor(new Color(0, 0, 0));
		g.drawLine(320, 410, 440, 490);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b2.get(7) + "", 395, 460);

		// 4-7
		g.setColor(new Color(0, 0, 0));
		g.drawLine(380, 310, 460, 490);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b2.get(4) + "", 412, 380);

		// 8-9
		g.setColor(new Color(0, 0, 0));
		g.drawLine(120, 410, 225, 520);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(9) + "", 185, 475);

		// 6-9
		g.setColor(new Color(0, 0, 0));
		g.drawLine(330, 460, 265, 520);

		g.setColor(new Color(0, 0, 250));
		g.drawString(ustawienia.b3.get(6) + "", 285, 485);

		g.setColor(Color.white);
		g.drawString("numer przystanku", 120, 18);
		g.drawLine(120, 15, 70, 15);

		g.drawString("natezenie ruchu", 120, 28);
		g.drawLine(120, 25, 70, 25);

		g.drawString("ilosc ludzi", 90, 90);
		g.drawLine(90, 88, 80, 55);

		g.drawString("czy podjechal dany autobus", 5, 120);
		g.drawLine(60, 110, 60, 80);

		g.drawString("dlugosc trasy (m)", 170, 48);
		g.drawLine(170, 46, 140, 52);

		g.drawString("numer busa", 300, 18);
		g.drawLine(365, 15, 400, 15);

		g.drawString("zape³nienie busa", 300, 32);
		g.drawLine(395, 29, 400, 29);

		g.setColor(Color.red);
		g.drawString("dodatkowe busy na liniach", 400, 105);
		g.drawString("(4,5,6,7 to 0,1,2,3)", 400, 115);
	}
}
