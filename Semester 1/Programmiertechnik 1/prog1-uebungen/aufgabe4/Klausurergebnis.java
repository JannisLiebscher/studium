// Klausurergebnis.java
package aufgabe4;
//import aufgabe4.schweiz.Noten;
import java.util.Locale;
import java.util.Scanner;

/**
 * Klausurergebnis erstellt eine Notenstatistik.
 * <p>
 * Das Programm liest Noten als Strings ein und bestimmt die beste und
 * die schlechteste Note, den Durchschnitt der Bestandenen sowie
 * die Durchfallquote in Prozent.
 * Das Programm ber&uuml;cksichtigt dabei nur die laut Noten.istZulaessig
 * erlaubten Noten. Andere Noten werden unter Ausgabe einer Warnung ignoriert.
 * Welche Noten besser und schlechter sind, welche als bestanden oder
 * durchgefallen gelten und wie die String-Darstellung der Noten aussieht,
 * wird mit Methoden der Klasse Noten bestimmt.
 * </p>
 * Das Programm gibt als Klausurergebnis folgende Werte aus:
 * <ul>
 * <li>die Anzahl der ber&uuml;cksichtigten Noten</li>
 * <li>die Anzahl der Bestandenen</li>
 * <li>die beste Note</li>
 * <li>die schlechteste Note</li>
 * <li>den Durchschnitt der Bestandenen</li>
 * <li>die Durchfallquote</li>
 * </ul>
 *
 * @author Jannis Liebscher
 * @version 10.12.2020
 */
public final class Klausurergebnis {
    private Klausurergebnis() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        Locale.setDefault(Locale.GERMAN);

        int anzahlNoten = 0;
        int anzahlBestanden = 0;
        int anzahlDurchgefallen = 0;
        double summeBestanden = 0.0;
        double besteNote = Noten.SCHLECHTESTE;
        double schlechtesteNote = Noten.BESTE;

        //--------------------------------------------------- Noten einlesen
        System.out.println("Noten im Format Ganze,Zehntel "
             + "oder Ganze.Zehntel eingeben (Ende mit Strg-D):");
        while (EINGABE.hasNext()) {
            String note = EINGABE.next();

            //---------------------------------------------- Eingabe pruefen

            if (!Noten.istZulaessig(note)) {
                System.out.printf("Unzulaessige Note %s wird ignoriert!%n",
                    note);
                continue;
            } else {
                //------------------------------------------------ Note erfassen
                double noteDouble = Noten.toDouble(note);
                ++anzahlNoten;
                if (Noten.istBestanden(noteDouble)) {
                    ++anzahlBestanden;
                    summeBestanden = summeBestanden + noteDouble;
                } else {
                    ++anzahlDurchgefallen;
                }
                besteNote = Noten.bessere(noteDouble, besteNote);
                schlechtesteNote = Noten.schlechtere(noteDouble,
                    schlechtesteNote);
            }
        } // while

        //------------------------------------------ Notenstatistik ausgeben

        final int inProzent = 100;
        double schnittBestanden = summeBestanden / anzahlBestanden;
        double durchfallQuote = (double) anzahlDurchgefallen
            / (double) anzahlNoten * inProzent;
        System.out.printf("%nAnzahl beruecksichtigter Noten: %d%n",
            anzahlNoten);
        System.out.printf("Anzahl Bestandene: %d%n", anzahlBestanden);
        if (anzahlNoten > 0) {
            System.out.printf("Beste Note: %s%n",
                        Noten.toString(besteNote));
            System.out.printf("Schlechteste Note: %s%n",
                Noten.toString(schlechtesteNote));
            System.out.printf("Durchschnitt Bestandene: %s%n",
                Noten.toString(schnittBestanden));
            System.out.printf("Durchfallquote: %.1f%%%n",
                durchfallQuote);
        }

    } // main
}
