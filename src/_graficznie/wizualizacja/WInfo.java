package _graficznie.wizualizacja;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasa WInfo - panel wyswietlajacy w oknie parametry symulacji
 * 
 */
public class WInfo extends JPanel {
	private static final long serialVersionUID = 1L;
	public boolean bus[] = new boolean[4];

	/**
	 * Konstruktor klasy WInfo
	 * 
	 * @param ustawienia
	 *            Ustawienia symulacji
	 */
	public WInfo(Ustawienia ustawienia) {
		this.setLayout(null);
		pokazDlugosciTras(ustawienia);
		this.setBackground(new Color(100, 100, 120));
	}

	/**
	 * Wyswietla dlugosci tras
	 * 
	 * @param ustawienia
	 *            Ustawienia symulacji
	 */
	public void pokazDlugosciTras(Ustawienia ustawienia) {
		JLabel l;
		Font font = new Font("SansSerif", Font.BOLD, 15);

		l = new JLabel("Bus");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setFont(font);
		l.setBounds(10, 0, 260, 50);
		add(l);

		l = new JLabel("Ilosc");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setFont(font);
		l.setBounds(60, 0, 260, 50);
		add(l);

		l = new JLabel("Expresow");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setFont(font);
		l.setBounds(110, 0, 260, 50);
		add(l);

		l = new JLabel("0");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(20, 20, 260, 50);
		add(l);

		l = new JLabel("1");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(20, 40, 260, 50);
		add(l);

		l = new JLabel("3");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(20, 60, 260, 50);
		add(l);

		l = new JLabel("3");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(20, 80, 260, 50);
		add(l);

		l = new JLabel(ustawienia.iloscBusow[0] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(70, 20, 260, 50);
		add(l);

		l = new JLabel(ustawienia.iloscBusow[1] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(70, 40, 260, 50);
		add(l);

		l = new JLabel(ustawienia.iloscBusow[2] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(70, 60, 260, 50);
		add(l);

		l = new JLabel(ustawienia.iloscBusow[3] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(70, 80, 260, 50);
		add(l);

		l = new JLabel(ustawienia.express[0] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(130, 20, 260, 50);
		add(l);

		l = new JLabel(ustawienia.express[0] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(130, 40, 260, 50);
		add(l);

		l = new JLabel(ustawienia.express[0] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(130, 60, 260, 50);
		add(l);

		l = new JLabel(ustawienia.express[0] + "");
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(130, 80, 260, 50);
		add(l);

		String sNatezenie = "Natezenie ruchu: ";
		switch (ustawienia.poraDnia) {
		case 0:
			sNatezenie += "NOC (nr 0)";
			break;
		case 1:
			sNatezenie += "NORMALNE (nr 1)";
			break;
		case 2:
			sNatezenie += "SZCZYTOWE (nr 2)";
			break;
		default:
			break;
		}

		l = new JLabel(sNatezenie);
		l.setForeground(new Color(0, 0, 0));
		l.setBackground(new Color(250, 0, 0));
		l.setVisible(true);
		l.setBounds(20, 140, 260, 50);
		add(l);

	}

}
