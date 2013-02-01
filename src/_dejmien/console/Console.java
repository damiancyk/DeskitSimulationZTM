package _dejmien.console;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Klasa Console.java - reprezentuje panel konsoli, ktory mozemy dodac do okna
 * symulacji i wpisywac do niego informacje o wykonywanej czynnosci
 * 
 * @author DeymieN
 * 
 */
public class Console extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea tObszarTekstowy;
	private JScrollPane jScrollPane;
	private JTextField tPoleTekstowe;
	private Color colorText;
	private Color colorBackground;

	/**
	 * Domyslny konstruktor
	 */
	public Console() {
		this.setLayout(null);
		this.setVisible(true);

		this.setBounds(0, 0, 100, 100);

		colorText = new Color(0, 250, 0);
		colorBackground = new Color(0, 0, 0);

		tObszarTekstowy = new JTextArea(8, 2);
		tPoleTekstowe = new JTextField();
		jScrollPane = new JScrollPane(tObszarTekstowy);
		jScrollPane.setAutoscrolls(true);
		jScrollPane.setBounds(0, 0, getWidth(), getHeight());
		jScrollPane.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		tPoleTekstowe.setBounds(0, 0, 200, 200);
		tObszarTekstowy.setBackground(colorBackground);
		tObszarTekstowy.setForeground(colorText);
		tObszarTekstowy.setBorder(BorderFactory
				.createLineBorder(Color.black, 1));
		tObszarTekstowy.setEditable(false);
		tObszarTekstowy.setLineWrap(true);
		tObszarTekstowy.setFont(new Font("Monospaced", Font.ITALIC, 12));
		tObszarTekstowy.setAutoscrolls(true);
		tPoleTekstowe.setBackground(new Color(191, 191, 191));
		tPoleTekstowe.setForeground(new Color(190, 10, 11));
		tPoleTekstowe.setEnabled(false);
		add(jScrollPane);
	}

	/**
	 * Konstruktor z podanymi rozmiarami i polozeniem
	 * 
	 * @param x
	 *            Polozenie w osi x
	 * @param y
	 *            Polozenie w osi y
	 * @param w
	 *            Szerokosc konsoli
	 * @param h
	 *            Wysokosc konsoli
	 */
	public Console(int x, int y, int w, int h) {
		this();
		setBounds(x, y, w, h);
		jScrollPane.setBounds(0, 0, getWidth(), getHeight());
	}

	/**
	 * Konstruktor z rozmiarami konsoli oraz kolorem tla oraz tekstu
	 * 
	 * @param x
	 *            Polozenie w osi x
	 * @param y
	 *            Polozenie w osi y
	 * @param w
	 *            Szerokosc konsoli
	 * @param h
	 *            Wysokosc konsoli
	 * @param colorText
	 *            Kolor tekstu
	 * @param colorBackground
	 *            Kolor tla
	 */
	public Console(int x, int y, int w, int h, Color colorText,
			Color colorBackground) {
		this();
		this.setBounds(x, y, w, h);
		jScrollPane.setBounds(0, 0, getWidth(), getHeight());

		tObszarTekstowy.setBackground(colorBackground);
		tObszarTekstowy.setForeground(colorText);
	}

	/**
	 * Napisanie czegos w konsoli
	 * 
	 * @param text
	 *            Dopisany do konsoli tekst
	 */
	public void write(String text) {
		tObszarTekstowy.append(text);
		jScrollPane.getVerticalScrollBar().setValue(
				jScrollPane.getVerticalScrollBar().getMaximum());

		tObszarTekstowy.setCaretPosition(tObszarTekstowy.getDocument()
				.getLength());
	}
}
