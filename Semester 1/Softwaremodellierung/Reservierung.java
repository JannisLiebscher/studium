import java.util.*;

public class Reservierung {

	public Kundenkonto Kundenkonto;
	private Fahrzeug Fahrzeug;
	Fahrzeugklasse fahrzeugklasse;
	private Standort R�ckgabestandort;
	private Standort Ausleihstandort;
	private Collection<Sonderwunsch> Sonderwunsch;
	Collection<Schaden> Schaden;
	private Collection<Strafzettel> Strafzettel;
	private date AusleihDatum;
	private date R�ckgabeDatum;
	private string Endpreis;
	private int KilometerStandStart;
	private int KilometerStandEnde;
	private date Zahlungsdatum;
	private static int R�CKF�HRUNGSGEB�HR = 50;
	private int Reservierungsnummer;

	public Kundenkonto getKundenkonto() {
		return this.Kundenkonto;
	}

	public void setKundenkonto(Kundenkonto Kundenkonto) {
		this.Kundenkonto = Kundenkonto;
	}

	public Fahrzeug getFahrzeug() {
		return this.Fahrzeug;
	}

	public void setFahrzeug(Fahrzeug Fahrzeug) {
		this.Fahrzeug = Fahrzeug;
	}

	public Standort getR�ckgabestandort() {
		return this.R�ckgabestandort;
	}

	public void setR�ckgabestandort(Standort R�ckgabestandort) {
		this.R�ckgabestandort = R�ckgabestandort;
	}

	public Standort getAusleihstandort() {
		return this.Ausleihstandort;
	}

	public void setAusleihstandort(Standort Ausleihstandort) {
		this.Ausleihstandort = Ausleihstandort;
	}

	public Collection<Sonderwunsch> getSonderwunsch() {
		return this.Sonderwunsch;
	}

	public void setSonderwunsch(Collection<Sonderwunsch> Sonderwunsch) {
		this.Sonderwunsch = Sonderwunsch;
	}

	public Collection<Strafzettel> getStrafzettel() {
		return this.Strafzettel;
	}

	public void setStrafzettel(Collection<Strafzettel> Strafzettel) {
		this.Strafzettel = Strafzettel;
	}

	public void setFahrzeug() {
		// TODO - implement Reservierung.setFahrzeug
		throw new UnsupportedOperationException();
	}

	public void getGefahreneKilometer() {
		// TODO - implement Reservierung.getGefahreneKilometer
		throw new UnsupportedOperationException();
	}

	public date getAusleihDatum() {
		// TODO - implement Reservierung.getAusleihDatum
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AusleihDatum
	 */
	public void setAusleihDatum(date AusleihDatum) {
		// TODO - implement Reservierung.setAusleihDatum
		throw new UnsupportedOperationException();
	}

	public date getR�ckgabeDatum() {
		// TODO - implement Reservierung.getR�ckgabeDatum
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param R�ckgabeDatum
	 */
	public void setR�ckgabeDatum(date R�ckgabeDatum) {
		// TODO - implement Reservierung.setR�ckgabeDatum
		throw new UnsupportedOperationException();
	}

	public string getEndpreis() {
		// TODO - implement Reservierung.getEndpreis
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Endpreis
	 */
	public void setEndpreis(string Endpreis) {
		// TODO - implement Reservierung.setEndpreis
		throw new UnsupportedOperationException();
	}

	public int getKilometerStandStart() {
		// TODO - implement Reservierung.getKilometerStandStart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param KilometerStandStart
	 */
	public void setKilometerStandStart(int KilometerStandStart) {
		// TODO - implement Reservierung.setKilometerStandStart
		throw new UnsupportedOperationException();
	}

	public int getKilometerStandEnde() {
		// TODO - implement Reservierung.getKilometerStandEnde
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param KilometerStandEnde
	 */
	public void setKilometerStandEnde(int KilometerStandEnde) {
		// TODO - implement Reservierung.setKilometerStandEnde
		throw new UnsupportedOperationException();
	}

	public date getZahlungsdatum() {
		// TODO - implement Reservierung.getZahlungsdatum
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Zahlungsdatum
	 */
	public void setZahlungsdatum(date Zahlungsdatum) {
		// TODO - implement Reservierung.setZahlungsdatum
		throw new UnsupportedOperationException();
	}

}