// Notenstatistik.java
package aufgabe3;

import java.util.Locale;
import java.util.Scanner;

/**
 * erstellt eine Notenstatistik.
 * <p>
 * Das Programm erwartet Pr&uuml;fungsnoten im Format
 * <code>Ganze,Zehntel</code> oder <code>Ganze.Zehntel</code>,
 * wobei <code>Ganze</code> und <code>Zehntel</code> nur aus
 * je einer Dezimalziffer bestehen d&uuml;rfen.
 * Andere Eingaben werden wegen Formatfehler ignoriert.
 * </p>
 * <p>
 * Das Programm gibt die folgende Statistik aus:
 * </p>
 * <ul>
 * <li>die Anzahl der ber&uuml;cksichtigten Noten</li>
 * <li>die Anzahl der Bestandenen</li>
 * <li>die beste Note</li>
 * <li>die schlechteste Note</li>
 * <li>den Durchschnitt der Bestandenen</li>
 * <li>die Durchfallquote in Prozent</li>
 * </ul>
 * <p>
 * Es werden in der Statistik nur die nach HTWG-Pr&uuml;fungsordnung
 * zul&auml;ssigen Noten (1,0 1,3 1,7 2,0 2,3 2,7 3,0 3,3 3,7 4,0 5,0)
 * ber&uuml;cksichtigt.
 * Andere Eingaben werden wegen falscher Vorkommastelle oder
 * falscher Nachkommastelle ignoriert.
 * Alle Noten bis 4,0 gelten als bestanden, nur die 5,0 als durchgefallen.
 * </p>
 *
 * @author Jannis Liebscher
 * @version 30.11.2020
 */
public final class Notenstatistik {
    private Notenstatistik() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        Locale.setDefault(Locale.GERMANY);

        //--------------------------------------------------- Noten einlesen
        System.out.println("Noten im Format Ganze,Zehntel "
             + "oder Ganze.Zehntel eingeben (Ende mit Strg-D):");

        int anzahlNoten = 0;
        int anzahlBestanden = 0;
        int anzahlDurchgefallen = 0;
        double summeBestanden = 0.0;
        double besteNote = 5.0;
        double schlechtesteNote = 1.0;

        schleife:
        while (EINGABE.hasNext()) {
            String note = EINGABE.next();

            //---------------------------------------------- Eingabe pruefen


            double zahl;

            if (note.length() == 3
                && (note.charAt(1) == ',' || note.charAt(1) == '.')
                && Character.isDigit(note.charAt(0))
                && Character.isDigit(note.charAt(2))) {

                double nachKomma;
                int vorKomma;
                int ersteZiffer = Character.getNumericValue(note.charAt(0));
                int zweiteZiffer = Character.getNumericValue(note.charAt(2));

                switch (ersteZiffer) {
                case 1:
                case 2:
                case 3:
                    if (zweiteZiffer == 0 || zweiteZiffer == 3
                        || zweiteZiffer == 7) {
                        nachKomma = zweiteZiffer * 0.1;
                        vorKomma = ersteZiffer;
                    } else {
                        System.out.printf("Note %s wird wegen"
                             + " Nachkommastelle %s ignoriert!%n",
                            note, note.charAt(2));
                        continue schleife;
                    }
                    break;

                case 4:
                case 5:
                    if (zweiteZiffer == 0) {
                        nachKomma = zweiteZiffer * 0.1;
                        vorKomma = ersteZiffer;
                    } else {
                        System.out.printf("Note %s wird wegen "
                             + "Nachkommastelle %s ignoriert!%n",
                            note, note.charAt(2));
                        continue schleife;
                    }
                    break;
                case 0:
                case 6:
                case 7:
                case 8:
                case 9:
                    System.out.printf("Note %s wird wegen Vorkommastelle"
                         + " %s ignoriert!%n", note, note.charAt(0));
                    continue schleife;

                default:
                    System.out.printf("Note %s wird wegen Formatfehler"
                         + " ignoriert!", note);
                    continue schleife;
                } //switcht
                zahl = vorKomma + nachKomma;
            } else {
                System.out.printf("Note %s wird wegen Formatfehler"
                     + " ignoriert!%n", note);
                continue schleife;
            } //if

            //------------------------------------------------ Note erfassen
            ++anzahlNoten;
            //-------------------------------- Anzahl bestanden/durchgefallen
            if (zahl == 5.0) {
                ++anzahlDurchgefallen;
            } else {
                ++anzahlBestanden;
                summeBestanden = summeBestanden + zahl;
            }
            //------------------------------------ Beste/schlechteste Note
            if (zahl < besteNote) {
                besteNote = zahl;
                if (anzahlBestanden == 0) {
                    schlechtesteNote = zahl;
                }
            } else if (zahl > schlechtesteNote) {
                schlechtesteNote = zahl;
            }

        } // while

        //------------------------------------------ Notenstatistik ausgeben
        double schnittBestanden = summeBestanden / anzahlBestanden;
        double durchfallQuote = (double) anzahlDurchgefallen
            / (double) anzahlNoten * 100;
        System.out.printf("%nAnzahl beruecksichtigter Noten: %d%n",
            anzahlNoten);
        System.out.printf("Anzahl Bestandene: %d%n", anzahlBestanden);
        if (anzahlNoten > 0) {
            System.out.printf("Beste Note: %.1f%n", besteNote);
            System.out.printf("Schlechteste Note: %.1f%n", schlechtesteNote);
            System.out.printf("Durchschnitt Bestandene: %.1f%n",
                schnittBestanden);
            System.out.printf("Durchfallquote: %.1f%%%n", durchfallQuote);
        }
    } // main
}
