package _graficznie.wizualizacja;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import _dejmien.console.Console;

import deskit.monitors.Diagram;

/**
 * Klasa WOkno - dziedziczaca po JFrame klasa reprezentujaca okno glowne
 * symulacji Tutaj tworzymy wszystkie panele oraz komonenty niezbedne do obslugi
 * symulacji
 * 
 */
public class WOkno extends JFrame {
	private static final long serialVersionUID = 1L;
	WMapa wMapa;
	public LinkedList<WPrzystanek> przystanek = new LinkedList<WPrzystanek>();
	public WBus wBus[] = new WBus[8];
	WInfo wInfo;

	JButton buttonShowDiagramCPP;
	JButton buttonShowDiagramZB;
	JButton buttonShowDiagramZP;
	JButton buttonShowDiagramCOPN;
	JButton buttonExit;
	private Console console;
	Monitor monitor;
	public Ustawienia ustawienia;

	public WOkno(final Monitor monitor, Ustawienia ustawienia) {
		this.ustawienia = ustawienia;
		setTitle("==SYMULACJA ZTM==");
		int WIDTH = 800, HEIGHT = 600;
		setSize(WIDTH, HEIGHT);
		getContentPane().setBackground(new Color(50, 50, 50));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.monitor = monitor;

		console = new Console(550, 430, 250, 142);
		console.write(">>SYMULATOR ZTM\n");
		console.write(">>wczytano parametry z pliku \n parametry.txt\n");
		console.write(">>rozpoczeto symulacje..\n");
		add(console);

		buttonShowDiagramCPP = new JButton("Czasy przejazdu pasazerow");
		buttonShowDiagramCPP.setBounds(550, 400, 250, 30);
		buttonShowDiagramCPP.setBackground(new Color(100, 190, 100));
		buttonShowDiagramCPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diagram d;
				for (int i = 0; i < 4; i++) {
					d = new Diagram("zaleznoscczasowa",
							"Czas przejazdu pasazerow busa nr " + i);
					d.add(monitor.czasPrzejazduPasazera[i], java.awt.Color.RED);
					d.show();
				}
			}
		});
		add(buttonShowDiagramCPP);

		buttonShowDiagramZB = new JButton("Zapelnienie Busow");
		buttonShowDiagramZB.setBounds(550, 365, 250, 30);
		buttonShowDiagramZB.setBackground(new Color(100, 190, 100));
		buttonShowDiagramZB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 4; i++) {
					Diagram d1 = new Diagram("zaleznoscczasowa",
							"Zapelnienie busa nr: " + i);
					d1.add(monitor.zapelnienieBusa[i], java.awt.Color.GREEN);
					d1.show();
				}
			}
		});
		add(buttonShowDiagramZB);

		buttonShowDiagramZP = new JButton("Zapelnienie Przystankow");
		buttonShowDiagramZP.setBounds(550, 295, 250, 30);
		buttonShowDiagramZP.setBackground(new Color(100, 190, 100));
		buttonShowDiagramZP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diagram d;
				for (int i = 0; i < 10; i++) {
					d = new Diagram("zaleznoscczasowa",
							"Zapelnienie przystanku nr: " + i);
					d.add(monitor.zapelnieniePrzystankow[i],
							java.awt.Color.YELLOW);
					d.show();
				}
			}
		});
		add(buttonShowDiagramZP);

		buttonShowDiagramCOPN = new JButton(
				"Czas oczekiwania pasazerow na busa");
		buttonShowDiagramCOPN.setBounds(550, 330, 250, 30);
		buttonShowDiagramCOPN.setBackground(new Color(100, 190, 100));
		buttonShowDiagramCOPN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++) {
					Diagram d1 = new Diagram("zaleznoscczasowa",
							"czasy oczekiwania na przystanku nr: " + i);
					d1.add(monitor.czasOczekiwaniaPasazeraNaBusa[i],
							java.awt.Color.RED);
					d1.show();
				}
			}
		});
		add(buttonShowDiagramCOPN);

		buttonExit = new JButton("WYJSCIE");
		buttonExit.setBounds(550, 0, 250, 30);
		buttonExit.setBackground(new Color(100, 100, 150));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(buttonExit);

		// mapa
		wMapa = new WMapa(ustawienia);
		wMapa.setBounds(0, 0, 550, 600);
		wMapa.setBackground(new Color(100, 200, 200));
		add(wMapa);

		// przystanki
		for (int i = 0; i < 10; i++) {
			przystanek.add(new WPrzystanek(i, this));
			przystanek.get(i).setVisible(true);
			przystanek.get(i).setBackground(new Color(100, 0, 0));
			add(przystanek.get(i));
			new Thread(przystanek.get(i)).start();
		}

		przystanek.get(0).setBounds(40, 10, 40, 70);
		przystanek.get(1).setBounds(170, 90, 40, 70);
		przystanek.get(2).setBounds(170, 250, 40, 70);
		przystanek.get(3).setBounds(320, 90, 40, 70);
		przystanek.get(4).setBounds(360, 250, 40, 70);
		przystanek.get(5).setBounds(10, 250, 40, 70);
		przystanek.get(6).setBounds(320, 410, 40, 70);
		przystanek.get(7).setBounds(440, 470, 40, 70);
		przystanek.get(8).setBounds(120, 410, 40, 70);
		przystanek.get(9).setBounds(225, 480, 40, 70);

		// busy
		for (int i = 0; i < 8; i++) {
			wBus[i] = new WBus(i);
			wBus[i].setVisible(true);
			wBus[i].setBackground(new Color(100, 0, 0));
			add(wBus[i]);
			new Thread(wBus[i]).start();
		}

		wBus[0].setBounds(400, 10, 64, 30);
		wBus[1].setBounds(470, 10, 64, 30);
		wBus[2].setBounds(400, 45, 64, 30);
		wBus[3].setBounds(470, 45, 64, 30);
		
		wBus[4].setBounds(400, 120, 64, 30);
		wBus[5].setBounds(470, 120, 64, 30);
		wBus[6].setBounds(400, 155, 64, 30);
		wBus[7].setBounds(470, 155, 64, 30);

		wInfo = new WInfo(ustawienia);
		wInfo.setVisible(true);
		wInfo.setBounds(555, 35, 235, 200);
		add(wInfo);

	}
}
