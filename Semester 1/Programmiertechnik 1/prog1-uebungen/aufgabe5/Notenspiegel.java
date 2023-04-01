// Notenspiegel.java
package aufgabe5;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Notenspiegel liest die Namen von F&auml;chern mit den zugeh&ouml;rigen Noten
 * in eine verkettete Liste ein und gibt dann einen Notenspiegel aus.
 * @author Jannis Liebscher
 * @version 20.01.2020
 */
public final class Notenspiegel {
    private Notenspiegel() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        FachNotenListe liste = new FachNotenListe(); // leere Liste

        //--------------------------------------------- Notenspiegel einlesen
        System.err.printf(
            "Faecher mit Noten zwischen %d und %d eingeben "
             + "(Ende mit Strg-D):%n",
            Note.BESTE.intValue(), Note.SCHLECHTESTE.intValue());
        while (EINGABE.hasNext()) {
            try {
                //------------------------------------ Fach und Note einlesen
                String fach = EINGABE.next();
                Note n;
                if (EINGABE.hasNextInt()) {
                    int note = EINGABE.nextInt();
                    n = Note.valueOf(note);
                } else {
                    String note = EINGABE.next();
                    n = Note.valueOf(note);
                }

                //--------------------- neue Fachnote in Notenliste eintragen
                FachNote f = new FachNote(fach, n);
                liste.insert(f);

            } catch (IllegalArgumentException x) {
                System.err.printf("Eingabefehler: %s%n", x.getMessage());
                continue;
            } catch (NoSuchElementException x) {
                System.err.println("Fach ohne Note ignoriert!");
                break;
            }
        }
        //--------------------------------------------- Notenspiegel ausgeben
        final int abstand = 5;
        int laengstesFach = 0;
        FachNotenListe.Iterator i = liste.new Iterator();
        while (i.hasNext()) {
            FachNote fach = i.next();
            if (laengstesFach < fach.fach.length()) {
                laengstesFach = fach.fach.length();
            }
        }

        laengstesFach += abstand;
        i = liste.new Iterator();
        System.out.println("NOTENSPIEGEL");
        while (i.hasNext()) {
            FachNote fach = i.next();
            System.out.printf("%-" + laengstesFach + "s %s",
                fach.fach, fach.note);

            if (Note.istBestanden((fach.note).intValue())) {
                if (fach.note.equals(Note.BESTE)) {
                    System.out.println("    mit Bestnote bestanden");
                } else {
                    System.out.println("    bestanden");
                }
            } else {
                System.out.println("    nicht bestanden");
            }

        }
    } // main
}
