package _graficznie.wizualizacja;

import deskit.SimObject;
import deskit.monitors.MonitoredVar;

/**
 * Klasa Monitor.java - skupia wszystkie zmienne monitorowane stworzone w
 * symulacji
 * 
 */
public class Monitor extends SimObject {
	public MonitoredVar czasPrzejazduPasazera[] = new MonitoredVar[4];
	public MonitoredVar zapelnienieBusa[] = new MonitoredVar[4];//
	public MonitoredVar czasOczekiwaniaPasazeraNaBusa[] = new MonitoredVar[10];
	public MonitoredVar zapelnieniePrzystankow[] = new MonitoredVar[10];

	public MonitoredVar zapelnieniePrzystankowSrednie;

	/**
	 * Konstruktor klasy Monitor
	 */
	public Monitor() {
		zapelnieniePrzystankowSrednie = new MonitoredVar(this);
	}
}
