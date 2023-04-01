// Histogramm.java
package aufgabe2bonus2;

import java.util.Scanner;

/**
 * Histogramm liest ganze Zahlen zwischen 1 und 6 ein und
 * gibt deren H&auml;ufigkeitsverteilung als Histogramm aus.
 * @author Jannsi Liebscher
 * @version 07.11.2020
 */
public final class Zuschneiden {
    private Zuschneiden() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[]args) {
        final int interval = 6;
        final int dollar = 5;
        final int maxZeichen = 50;
        int[]zaehler = new int[interval];
        //---------------------------------------------------- Zahlen einlesen
        System.out.println("Ganze Zahlen zwischen 1 und 6 eingeben "
             + "(Ende mit Strg-Z):");
        while (EINGABE.hasNext()) {
            int number = EINGABE.nextInt();
        //-------------------------Pruefen und Zaehlen der Eingabe
            if (number >= 1 && number <= interval) {
                ++zaehler[number - 1];
            } else {
                System.out.printf("Falsche Eingabe %d wird ignoriert%n", number);
            }
        }
        //---------------------------------------Startwert innere for-Schleife
        int min = zaehler[0];
        for (int i = 0; i < zaehler.length; i++) {
            if (zaehler[i] < min) {
                min = zaehler[i];
            }
        }
        System.out.printf("Kleinster Zaehler: %d%n", min);
        //----------------------------------------------- größten Zähler finden
        int max = 0;
        for (int i = 0; i < zaehler.length; i++) {
            if (zaehler[i] > max) {
                max = zaehler[i];
            }
        }
        System.out.printf("Groesster Zaehler: %d%n", max);
        //----------------------------------------------- n berechnen
        max = max - min;
        System.out.printf("Max. Zeichen nach Startwertanpassung:%d%n", max);
        int n = max / maxZeichen + 1;
        System.out.printf("Skalierung: %d:1%n", n);
        //------------------------------------------------ Histogramm ausgeben
        int ges = 1;
        for (int i = 0; i < zaehler.length; ++i) {
            for (int j = zaehler[i] - min; j > 0; j = j - n) {
                if (ges != dollar) {
                    System.out.printf("%d", i + 1);
                    ++ges;
                } else {
                    System.out.print("$");
                    ges = 1;
                }

            }
            System.out.printf("(%d)%n", zaehler[i]);
            ges = 1;
        }
    }
}
